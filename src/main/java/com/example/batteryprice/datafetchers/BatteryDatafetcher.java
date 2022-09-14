package com.example.batteryprice.datafetchers;


import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.graphql.types.BatteryInput;
import com.example.batteryprice.repository.BatteriesPriceRepository;
import com.example.batteryprice.service.BatteriesPriceService;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.DgsMutation;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@DgsComponent
@Builder
public class BatteryDatafetcher {


    @Autowired
    BatteriesPriceService service;

    @Autowired
    ModelMapper modelMapper;


    @DgsQuery
    public List<Battery> allBatteries() {
        return service.getAllBatteries();
    }

    @DgsMutation
    public Battery saveBattery(@InputArgument("batteryInput") BatteryInput batteryInput) throws Exception {
      return service.createBattery(batteryInput);
    }
}
