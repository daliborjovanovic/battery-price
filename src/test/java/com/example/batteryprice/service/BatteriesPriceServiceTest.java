package com.example.batteryprice.service;



import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.repository.BatteriesPriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class BatteriesPriceServiceTest {

    @Mock
    BatteriesPriceRepository repo;
    BatteriesPriceService service;

    @Autowired
    WebClient webClient;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = BatteriesPriceService.builder().repo(repo).webClient(webClient).build();
    }
    @AfterEach
    void teardown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void countPriceTest() throws Exception {
        List<String> batteriesNames = Arrays.asList("batt1", "batt2", "batt3");
        BatteriesInRangeDto batteriesInRangeDto = BatteriesInRangeDto.builder()
                .batteries(batteriesNames)
                .totalCapacity(600.0)
                .avgCapacity(200.0)
                .build();
        Battery battery = Battery.builder()
                .batteryName("batt1")
                .price(3.5*600)
                .build();
       Battery bat = service.getValueAndCalculatePrice(batteriesInRangeDto);
       verify(repo.save(bat));

    }

}
