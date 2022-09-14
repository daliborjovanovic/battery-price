package com.example.batteryprice.datafetchers;



import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.graphql.client.AllBatteriesGraphQLQuery;
import com.example.batteryprice.model.graphql.client.AllBatteriesProjectionRoot;
import com.example.batteryprice.model.graphql.client.SaveBatteryGraphQLQuery;
import com.example.batteryprice.model.graphql.client.SaveBatteryProjectionRoot;
import com.example.batteryprice.model.graphql.types.BatteryInput;
import com.example.batteryprice.service.BatteriesPriceService;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.BaseProjectionNode;
import com.netflix.graphql.dgs.client.codegen.BaseSubProjectionNode;
import com.netflix.graphql.dgs.client.codegen.GraphQLQuery;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;


import java.util.Arrays;
import java.util.List;



import static org.junit.jupiter.api.Assertions.*;


@Import({DgsAutoConfiguration.class, BatteryDatafetcher.class, BatteriesPriceService.class})
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class BatteryDatafetcherTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Autowired
    ModelMapper modelMapper;
    @MockBean
    BatteriesPriceService service;

    AutoCloseable autoCloseable;

//    @BeforeEach
//    void setup() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
//    }
//    @AfterEach
//    void teardown() throws Exception {
//        autoCloseable.close();
//    }



    @Test
    void saveBatteryTest() throws Exception {
        BatteryInput input = new BatteryInput();
        input.setBatteryName("batteryName");
        input.setPrice(150.0);

    Battery batteryToCreate = modelMapper.map(input, Battery.class);

    Mockito.when(service.createBattery(input)).thenReturn(batteryToCreate);

    var graphQLQueryRequest = new GraphQLQueryRequest(
            SaveBatteryGraphQLQuery.newRequest().batteryInput(input).build(),
            new SaveBatteryProjectionRoot().id().price().batteryName()
    );


        var battery = dgsQueryExecutor.executeAndExtractJsonPathAsObject(
                graphQLQueryRequest.serialize(), "data.saveBattery", Battery.class);

      assertEquals(battery.getPrice(), batteryToCreate.getPrice());
      assertEquals(battery.getBatteryName(), batteryToCreate.getBatteryName());
      assertEquals(battery.getId(), batteryToCreate.getId());
    }

    @Test
    void allBatteriesTest() {
        Battery battery1 = Battery.builder().id(ObjectId.get()).batteryName("bat1").price(350).build();
       Battery battery2 = Battery.builder().id(ObjectId.get()).batteryName("bat2").price(350).build();
       List<Battery> savedBatteries = Arrays.asList(battery1, battery2);

       Mockito.when(service.getAllBatteries()).thenReturn(savedBatteries);

        GraphQLQueryRequest graphQLQuery;
        graphQLQuery = new GraphQLQueryRequest(
                 AllBatteriesGraphQLQuery.newRequest().build(),
                new AllBatteriesProjectionRoot().id()
        );

        List<Battery> batteries = dgsQueryExecutor.executeAndExtractJsonPath(
            graphQLQuery.serialize(), "data.allBatteries");
      assertEquals(batteries.size(), savedBatteries.size());
    }
}