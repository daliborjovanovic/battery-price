package com.example.batteryprice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document("batteryprice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    @NonNull
    private double price;
    private String batteryName;
}
