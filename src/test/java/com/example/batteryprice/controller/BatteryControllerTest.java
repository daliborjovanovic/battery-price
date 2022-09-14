package com.example.batteryprice.controller;


import com.example.batteryprice.model.Battery;


import com.example.batteryprice.repository.UserRepo;
import com.example.batteryprice.service.BatteryRepo;


import org.bson.types.ObjectId;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Import(BatteryController.class)
class BatteryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BatteryRepo service;
    @MockBean
    UserRepo userRepo;

    Battery battery1 = new Battery(ObjectId.get(), 21000, "BatteryTest1");
    Battery battery2 = new Battery(ObjectId.get(), 21000, "BatteryTest1");
    Battery battery3 = new Battery(ObjectId.get(), 2000, "aaa35");

    @Test
    void findBattery() throws Exception {

        String name = "aaa35";
        List<Battery> batteries = List.of(battery1, battery2, battery3);

        Mockito.when(service.find(name)).thenReturn(battery3);


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/api/")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest).andExpect(jsonPath("$.batteryName").value(battery3.getBatteryName()))
                .andExpect(jsonPath("$.price").value(battery3.getPrice()));
    }
}