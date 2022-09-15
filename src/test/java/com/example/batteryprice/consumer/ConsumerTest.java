package com.example.batteryprice.consumer;

import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.PriceOperation;
import com.example.batteryprice.service.BatteriesPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ConsumerTest {


    @Mock
    BatteriesPriceService service;
    @InjectMocks
    @Autowired
    Consumer kafkaConsumer;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void cleanUp() {
        Mockito.clearInvocations(service);
    }


    @Test
    public void shouldConsumeMessage() throws Exception {
        KafkaMessage message = KafkaMessage.builder()
                .eventId(UUID.randomUUID())
                .operation(PriceOperation.HIGH)
                .payload(BatteriesInRangeDto.builder()
                        .avgCapacity(200)
                        .totalCapacity(600)
                        .batteries(new ArrayList<>())
                        .build())
                .build();
        BatteriesInRangeDto batteriesInRangeDto = (BatteriesInRangeDto) message.getPayload();
        Mockito.when(modelMapper.map(message.getPayload(), BatteriesInRangeDto.class)).thenReturn(batteriesInRangeDto);
        kafkaConsumer.consumeMessage(message);
        Mockito.verify(service).getValueAndCalculatePrice(batteriesInRangeDto, message.getOperation());

    }
}