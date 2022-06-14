package com.example.catalog.api.v1;

import com.example.catalog.dao.entity.Catalog;
import com.example.catalog.dao.entity.Disease;
import com.example.catalog.dao.repository.CatalogRepository;
import com.example.catalog.dao.repository.DiseaseRepository;
import com.example.grpc.catalog.CatalogServiceGrpc;
import com.example.grpc.catalog.DiseaseResponse;
import com.example.grpc.catalog.GetDiseaseByIdRequest;
import com.example.grpc.catalog.GetDiseaseByIdResponse;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceV1 extends CatalogServiceGrpc.CatalogServiceImplBase {

  private final CatalogRepository catalogRepository;
  private final DiseaseRepository diseaseRepository;

  public Catalog createCatalog(Catalog catalog) {
    return catalogRepository.save(catalog);
  }

  public Disease createDisease(Disease disease) {
    Catalog catalog = catalogRepository.findById(disease.getCatalogId())
        .orElseThrow(() -> new RuntimeException(""));
    disease.setCatalog(catalog);
    return diseaseRepository.save(disease);
  }

  @Override
  public void getDisease(GetDiseaseByIdRequest request,
      StreamObserver<GetDiseaseByIdResponse> responseObserver) {
    try {
      List<Long> ids = Arrays.stream(request.getIds().split(",")).map(Long::parseLong).collect(
          Collectors.toList());
      List<Disease> results = diseaseRepository.getDiseaseByIds(ids);

      Iterable<DiseaseResponse> diseases = results.stream().map(this.mapToDiseaseResponse())
          .collect(
              Collectors.toList());

      GetDiseaseByIdResponse response = GetDiseaseByIdResponse.newBuilder()
          .addAllDisease(diseases)
          .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

  private Function<Disease, DiseaseResponse> mapToDiseaseResponse() {
    return e -> DiseaseResponse.newBuilder()
        .setCode(e.getCode())
        .setName(e.getName())
        .setImageUrl(e.getImageUrl())
        .build();
  }
}
