package com.example.batteryprice.model.graphql.client;

import com.netflix.graphql.dgs.client.codegen.BaseProjectionNode;

public class SaveBatteryProjectionRoot extends BaseProjectionNode {
  public SaveBatteryProjectionRoot id() {
    getFields().put("id", null);
    return this;
  }

  public SaveBatteryProjectionRoot price() {
    getFields().put("price", null);
    return this;
  }

  public SaveBatteryProjectionRoot batteryName() {
    getFields().put("batteryName", null);
    return this;
  }
}
