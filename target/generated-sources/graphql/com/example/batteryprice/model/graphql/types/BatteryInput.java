package com.example.batteryprice.model.graphql.types;

import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public class BatteryInput {
  private Double price;

  private String batteryName;

  public BatteryInput() {
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getBatteryName() {
    return batteryName;
  }

  public void setBatteryName(String batteryName) {
    this.batteryName = batteryName;
  }

  @Override
  public String toString() {
    return "BatteryInput{" + "price='" + price + "'," +"batteryName='" + batteryName + "'" +"}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatteryInput that = (BatteryInput) o;
        return java.util.Objects.equals(price, that.price) &&
                            java.util.Objects.equals(batteryName, that.batteryName);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(price, batteryName);
  }

  public static com.example.batteryprice.model.graphql.types.BatteryInput.Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {
    private Double price;

    private String batteryName;

    public BatteryInput build() {
                  com.example.batteryprice.model.graphql.types.BatteryInput result = new com.example.batteryprice.model.graphql.types.BatteryInput();
                      result.price = this.price;
          result.batteryName = this.batteryName;
                      return result;
    }

    public com.example.batteryprice.model.graphql.types.BatteryInput.Builder price(Double price) {
      this.price = price;
      return this;
    }

    public com.example.batteryprice.model.graphql.types.BatteryInput.Builder batteryName(
        String batteryName) {
      this.batteryName = batteryName;
      return this;
    }
  }
}
