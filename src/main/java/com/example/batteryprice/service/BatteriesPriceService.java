package com.example.batteryprice.service;

import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.dto.KWPriceDto;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.graphql.types.BatteryInput;
import com.example.batteryprice.repository.BatteriesPriceRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
@Log4j2
@Builder
public class BatteriesPriceService {
 private final double KOEFICIJENT = 3.5;

    @Autowired
    BatteriesPriceRepository repo;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    WebClient webClient;

    @Autowired
    ObjectMapper objectMapper;

    public Battery getValueAndCalculatePrice(BatteriesInRangeDto batteriesDto) throws Exception  {
       KWPriceDto kwPriceDto = getValueRestCall();
       log.info("Consumer, new kwprice {}", getValueRestCall());

        Battery battery = new Battery();
        try {
            battery.setPrice(kwPriceDto.getPriceKw() * batteriesDto.getTotalCapacity());
            battery.setBatteryName(batteriesDto.getBatteries().get(0));
            repo.save(battery);
            return battery;
        }catch (Exception e) {
            e.printStackTrace();
            return battery;
        }

    }

    public KWPriceDto getValueRestCall() {
        return webClient
                .get().
                uri("/priceKw").
                retrieve()
                .bodyToMono(KWPriceDto.class)
                .block();
    }

    public Battery createBattery (BatteryInput batteryInput) {
        Battery battery = modelMapper.map(batteryInput, Battery.class);
        return repo.save(battery);
    }

    public List<Battery> getAllBatteries() {
        return repo.findAll();
    }


}
