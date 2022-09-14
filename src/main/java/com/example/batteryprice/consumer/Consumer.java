package com.example.batteryprice.consumer;

import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.exception.KafkaException;
import com.example.batteryprice.service.BatteriesPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Consumer {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BatteriesPriceService service;
    @Autowired
    KafkaProperties kafkaProperties;
    @Autowired
    KafkaException kafkaException;


    @Bean
    private ConsumerFactory<String, BatteriesInRangeDto> consumerFactory() {
       Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(BatteriesInRangeDto.class));
    }

    @KafkaListener(topics = "battery-price", errorHandler = "kafkaException")
    public void consumeMessage(BatteriesInRangeDto batteriesInRangeDto) throws Exception {
        log.info("Consumer consume msg");
        if (batteriesInRangeDto.getTotalCapacity()>100000000) {
            throw new RuntimeException("failed");
        }
        service.getValueAndCalculatePrice(batteriesInRangeDto);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BatteriesInRangeDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BatteriesInRangeDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
