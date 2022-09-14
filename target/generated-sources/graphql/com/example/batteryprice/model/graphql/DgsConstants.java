package com.example.batteryprice.model.graphql;

import java.lang.String;

public class DgsConstants {
  public static final String QUERY_TYPE = "Query";

  public static class QUERY {
    public static final String TYPE_NAME = "Query";

    public static final String AllBatteries = "allBatteries";
  }

  public static class MUTATION {
    public static final String TYPE_NAME = "Mutation";

    public static final String SaveBattery = "saveBattery";

    public static class SAVEBATTERY_INPUT_ARGUMENT {
      public static final String BatteryInput = "batteryInput";
    }
  }

  public static class BATTERY {
    public static final String TYPE_NAME = "Battery";

    public static final String Id = "id";

    public static final String Price = "price";

    public static final String BatteryName = "batteryName";
  }

  public static class BATTERYINPUT {
    public static final String TYPE_NAME = "BatteryInput";

    public static final String Price = "price";

    public static final String BatteryName = "batteryName";
  }
}
