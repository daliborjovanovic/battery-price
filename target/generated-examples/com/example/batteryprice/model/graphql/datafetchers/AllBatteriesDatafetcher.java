package com.example.batteryprice.model.graphql.datafetchers;

import com.example.batteryprice.model.graphql.types.Battery;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;

@DgsComponent
public class AllBatteriesDatafetcher {
  @DgsData(
      parentType = "Query",
      field = "allBatteries"
  )
  public List<Battery> getAllBatteries(DataFetchingEnvironment dataFetchingEnvironment) {
    return null;
  }
}
