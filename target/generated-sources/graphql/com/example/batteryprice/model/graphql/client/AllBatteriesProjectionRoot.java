package com.example.batteryprice.model.graphql.client;

import com.netflix.graphql.dgs.client.codegen.BaseProjectionNode;

public class AllBatteriesProjectionRoot extends BaseProjectionNode {
  public AllBatteriesProjectionRoot id() {
    getFields().put("id", null);
    return this;
  }

  public AllBatteriesProjectionRoot price() {
    getFields().put("price", null);
    return this;
  }

  public AllBatteriesProjectionRoot batteryName() {
    getFields().put("batteryName", null);
    return this;
  }
}
