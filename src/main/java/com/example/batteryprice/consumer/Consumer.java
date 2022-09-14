package com.example.batteryprice.consumer;

import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.exception.KafkaException;
import com.example.batteryprice.service.BatteriesPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BatteriesPriceService service;
    @Autowired
    KafkaProperties kafkaProperties;
    @Autowired
    KafkaException kafkaException;



    private ConsumerFactory< String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties())
;    }

    @KafkaListener(topics = "my-topic", errorHandler = "kafkaException")
    public void consumeMessage(String message) throws Exception {

        BatteriesInRangeDto batteriesDto = objectMapper.readValue(message, BatteriesInRangeDto.class);
        if (batteriesDto.getTotalCapacity()>10000) {
            throw new RuntimeException("failed");
        }
        service.getValueAndCalculatePrice(batteriesDto);

    }
}
