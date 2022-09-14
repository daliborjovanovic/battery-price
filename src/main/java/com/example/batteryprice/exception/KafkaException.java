package com.example.batteryprice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service("kafkaException")
@Slf4j
public class KafkaException implements KafkaListenerErrorHandler {



    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        log.warn("Total capacity is not valid, {}", e.getMessage());

        if (e.getCause() instanceof RuntimeException) {
            throw e;
        }
        return null;
    }
}
