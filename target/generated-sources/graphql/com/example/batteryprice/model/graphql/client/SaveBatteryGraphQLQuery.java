package com.example.batteryprice.model.graphql.client;

import com.example.batteryprice.model.graphql.types.BatteryInput;
import com.netflix.graphql.dgs.client.codegen.GraphQLQuery;
import java.lang.Override;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

public class SaveBatteryGraphQLQuery extends GraphQLQuery {
  public SaveBatteryGraphQLQuery(BatteryInput batteryInput, Set<String> fieldsSet) {
    super("mutation");
    if (batteryInput != null || fieldsSet.contains("batteryInput")) {
        getInput().put("batteryInput", batteryInput);
    }
  }

  public SaveBatteryGraphQLQuery() {
    super("mutation");
  }

  @Override
  public String getOperationName() {
     return "saveBattery";
                    
  }

  public static Builder newRequest() {
    return new Builder();
  }

  public static class Builder {
    private Set<String> fieldsSet = new HashSet<>();

    private BatteryInput batteryInput;

    public SaveBatteryGraphQLQuery build() {
      return new SaveBatteryGraphQLQuery(batteryInput, fieldsSet);
               
    }

    public Builder batteryInput(BatteryInput batteryInput) {
      this.batteryInput = batteryInput;
      this.fieldsSet.add("batteryInput");
      return this;
    }
  }
}
