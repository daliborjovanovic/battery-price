package com.example.batteryprice.repository;

import com.example.batteryprice.model.User;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
}
