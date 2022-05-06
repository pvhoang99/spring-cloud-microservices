package com.example.orderservice.dao.repository;

import com.example.orderservice.dao.entity.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {

}
