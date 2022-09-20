package com.example.batteryprice.service;

import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.dto.KWPriceDto;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.PriceOperation;
import com.example.batteryprice.model.graphql.types.BatteryInput;
import com.example.batteryprice.repository.BatteriesPriceRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

@Service
@Data
@Log4j2
@Builder
public class BatteriesPriceService {


    @NonNull
    private BatteriesPriceRepository repo;
    @NonNull
    private ModelMapper modelMapper;
    @NonNull
    private WebClient webClient;
    @NonNull
    private ObjectMapper objectMapper;

    public Battery getValueAndCalculatePrice(BatteriesInRangeDto batteriesDto, PriceOperation priceOperation) throws Exception  {
       KWPriceDto kwPriceDto = getValueRestCall(priceOperation);
       log.info("Consumer, new kwprice {}", kwPriceDto);

        Battery battery = new Battery();
        try {
            battery.setPrice(kwPriceDto.getPriceKw() * batteriesDto.getTotalCapacity());
            battery.setBatteryName(batteriesDto.getBatteries().get(0));
            repo.save(battery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return battery;

    }

    public KWPriceDto getValueRestCall(PriceOperation priceOperation) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("operation", priceOperation.toString())
                        .build())
                .retrieve()
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
