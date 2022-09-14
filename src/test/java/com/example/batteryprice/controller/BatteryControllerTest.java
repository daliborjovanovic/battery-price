package com.example.batteryprice.controller;

import com.example.batteryprice.model.Battery;
import com.example.batteryprice.service.BatteriesPriceService;
import com.example.batteryprice.service.BatteryRepo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BatteryController.class)

class BatteryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BatteryRepo service;



    Battery battery1 = new Battery(ObjectId.get(), 21000, "BatteryTest1");
    Battery battery2 = new Battery(ObjectId.get(), 21000, "BatteryTest1");
    Battery battery3 = new Battery(ObjectId.get(), 2000, "aaa35");

    @Test
    void findBattery() throws Exception {

        String name = "aaa35";
        List<Battery> batteries = Arrays.asList(battery1, battery2, battery3);

        Mockito.when(service.find(name)).thenReturn(battery3);


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest).andExpect(jsonPath("$.batteryName").value(name));
    }
}