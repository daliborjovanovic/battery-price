package com.example.batteryprice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteriesInRangeDto {

    private List<String> batteries;
    private double totalCapacity;
    private double avgCapacity;
}
