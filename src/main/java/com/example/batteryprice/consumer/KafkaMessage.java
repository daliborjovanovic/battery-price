package com.example.batteryprice.consumer;

import com.example.batteryprice.model.PriceOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class KafkaMessage {


    private UUID eventId;
    private Object payload;
    private PriceOperation operation;
}
