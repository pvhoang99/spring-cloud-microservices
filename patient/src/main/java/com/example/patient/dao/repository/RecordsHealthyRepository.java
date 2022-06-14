package com.example.patient.dao.repository;

import com.example.patient.dao.entity.RecordsHealthy;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsHealthyRepository extends MongoRepository<RecordsHealthy, ObjectId> {

}
