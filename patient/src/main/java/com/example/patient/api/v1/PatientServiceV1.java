package com.example.patient.api.v1;

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
import org.springframework.stereotype.Service;

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

}
