package com.example.patient.dao.repository;

import com.example.patient.dao.entity.Patient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, ObjectId> {

}
