package com.example.patient.api.v1;

import com.example.grpc.catalog.CatalogServiceGrpc.CatalogServiceBlockingStub;
import com.example.grpc.catalog.DiseaseResponse;
import com.example.grpc.catalog.GetDiseaseByIdRequest;
import com.example.grpc.catalog.GetDiseaseByIdResponse;
import com.example.patient.dao.entity.Disease;
import com.example.patient.dao.entity.Patient;
import com.example.patient.dao.repository.PatientRepository;
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

  private final PatientRepository patientRepository;

  @GrpcClient("catalog-service")
  private CatalogServiceBlockingStub catalogServiceBlockingStub;


  public Patient postCreatPatient(Patient patient) {

    List<Disease> diseases = null;
    if (StringUtils.isNotEmpty(patient.getDiseaseIds())) {
      diseases = this.getDiseaseByIds(patient.getDiseaseIds());
    }
    patient.setDiseases(diseases);
    return patientRepository.save(patient);
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
