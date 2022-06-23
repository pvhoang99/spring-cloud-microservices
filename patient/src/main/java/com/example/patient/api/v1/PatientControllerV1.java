package com.example.patient.api.v1;

import com.example.patient.dao.entity.RecordsHealthy;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<RecordsHealthy> postCreateRecordsHealthy(
      @RequestBody RecordsHealthy patient) {
    return ResponseEntity.ok(patientServiceV1.postCreatRecordHealthy(patient));
  }

  @RequestMapping(value = "/records-healthy/{id}", method = RequestMethod.GET, name = "getRecordsHealthyById")
  public ResponseEntity<RecordsHealthy> getRecordsHealthyById(@PathVariable("id") String id) {
    return ResponseEntity.ok(patientServiceV1.getRecordsHealthyById(id));
  }

  @RequestMapping(value = "/records-healthy/all", method = RequestMethod.GET, name = "getAllRecordsHealthy")
  public ResponseEntity<?> getAllRecordsHealthy() {
    return Optional.ofNullable(patientServiceV1.getAllRecordsHealthy())
        .map(rs -> new ResponseEntity<>(rs, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
  }


  @RequestMapping(value = "/records-healthy/change-to-nft/{id}", method = RequestMethod.PUT)
  public boolean putChangeRecordsHealthyToNft(@PathVariable("id") String id) {
    return patientServiceV1.putChangeToNFT(id);
  }
}
