package com.example.batteryprice.datafetchers;


import com.example.batteryprice.dto.BatteriesInRangeDto;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.graphql.types.BatteryInput;
import com.example.batteryprice.repository.BatteriesPriceRepository;
import com.example.batteryprice.service.BatteriesPriceService;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.DgsMutation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@DgsComponent
@Data
@AllArgsConstructor
public class BatteryDatafetcher {



   private BatteriesPriceService service;
   private ModelMapper modelMapper;


    @DgsQuery
    public List<Battery> allBatteries() {
        return service.getAllBatteries();
    }

    @DgsMutation
    public Battery saveBattery(@InputArgument("batteryInput") BatteryInput batteryInput) throws Exception {
      return service.createBattery(batteryInput);
    }
}
