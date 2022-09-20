package com.example.batteryprice.repository;

import com.example.batteryprice.model.Battery;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class BatteryRepositoryTest {


    @Spy
    BatteriesPriceRepository repo;

    @Test
    void saveTest() {

        Battery battery = new Battery();
        battery.setId(null);
        battery.setBatteryName("batteryTest");
        battery.setPrice(5000);

        repo.save(battery);

        Mockito.verify(repo).save(battery);

    }
}
