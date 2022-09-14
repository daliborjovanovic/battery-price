package com.example.batteryprice.repository;

import com.example.batteryprice.model.Battery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BatteriesPriceRepository extends MongoRepository<Battery, Long> {
}
