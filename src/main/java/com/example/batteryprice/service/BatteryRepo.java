package com.example.batteryprice.service;

import com.example.batteryprice.model.Battery;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class BatteryRepo{

    @Autowired
    MongoTemplate mongoTemplate;


    public Battery find(String name) {
        return  mongoTemplate.findOne(Query.query(Criteria.where("batteryName").is(name)), Battery.class);
    }
}
