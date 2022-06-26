package com.example.patient.api.v1;

import com.example.common.utils.Helper;
import com.example.grpc.catalog.CatalogServiceGrpc;
import com.example.grpc.catalog.DiseaseResponse;
import com.example.grpc.catalog.GetDiseaseByIdRequest;
import com.example.grpc.catalog.GetDiseaseByIdResponse;
import com.example.patient.dao.entity.RecordsHealthy;
import com.example.patient.dao.entity.RecordsHealthy.Disease;
import com.example.patient.dao.repository.RecordsHealthyRepository;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class PatientServiceV1 {

  private final RecordsHealthyRepository recordsHealthyRepository;

  @GrpcClient("catalog-service")
  private CatalogServiceGrpc.CatalogServiceBlockingStub catalogServiceBlockingStub;

  public RecordsHealthy postCreatRecordHealthy(RecordsHealthy recordsHealthy) {

    List<Disease> diseases = null;
    if (StringUtils.isNotEmpty(recordsHealthy.getDiseaseIds())) {
      diseases = this.getDiseaseByIds(recordsHealthy.getDiseaseIds());
    }
    recordsHealthy.setDiseases(diseases);
    return recordsHealthyRepository.save(recordsHealthy);
  }

  public List<Disease> getDiseaseByIds(String ids) {
    GetDiseaseByIdRequest request = GetDiseaseByIdRequest.newBuilder()
        .setIds(ids).build();

    GetDiseaseByIdResponse response = catalogServiceBlockingStub.getDisease(request);

    return response.getDiseaseList().stream().map(mapDiseaseResponseToDisease())
        .collect(Collectors.toList());

  }

  private Function<DiseaseResponse, Disease> mapDiseaseResponseToDisease() {
    return e -> Disease.builder()
        .code(e.getCode())
        .name(e.getName())
        .imageUrl(e.getImageUrl())
        .build();
  }

  public RecordsHealthy getRecordsHealthyById(String id) {
    return recordsHealthyRepository.findById(new ObjectId(id)).orElse(null);
  }

  public List<RecordsHealthy> getAllRecordsHealthy() {
    return recordsHealthyRepository.findAll();
  }

  public boolean putChangeToNFT(String id) {

    RecordsHealthy recordsHealthy = recordsHealthyRepository.findById(new ObjectId(id))
        .orElse(null);
    if (recordsHealthy != null) {
      recordsHealthy.setIsNFT(true);
      recordsHealthyRepository.save(recordsHealthy);
    }
    return recordsHealthy != null;
  }

  public RecordsHealthy putUpdateRecordsHealthy(RecordsHealthy recordsHealthy) {

    if (ObjectUtils.isEmpty(recordsHealthy.getObjectId())) {
      throw new RuntimeException();
    }

    RecordsHealthy healthy = recordsHealthyRepository.findById(recordsHealthy.getObjectId())
        .orElse(null);
    assert healthy != null;
    Helper.copyNonNullProperties(recordsHealthy, healthy);

    return recordsHealthyRepository.save(healthy);
  }

}
