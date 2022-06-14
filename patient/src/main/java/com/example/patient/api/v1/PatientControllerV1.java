package com.example.patient.api.v1;

import com.example.patient.dao.entity.RecordsHealthy;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PatientControllerV1 {

  private final PatientServiceV1 patientServiceV1;

  @RequestMapping(value = "/records-healthy/create", method = RequestMethod.POST, name = "postCreatePatient")
  public ResponseEntity<RecordsHealthy> postCreateRecordsHealthy(@RequestBody RecordsHealthy patient) {
    return ResponseEntity.ok(patientServiceV1.postCreatRecordHealthy(patient));
  }

}
