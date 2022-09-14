package com.example.batteryprice.model.graphql.types;

import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public class Battery {
  private String id;

  private Double price;

  private String batteryName;

  public Battery() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
    return "Battery{" + "id='" + id + "'," +"price='" + price + "'," +"batteryName='" + batteryName + "'" +"}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery that = (Battery) o;
        return java.util.Objects.equals(id, that.id) &&
                            java.util.Objects.equals(price, that.price) &&
                            java.util.Objects.equals(batteryName, that.batteryName);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, price, batteryName);
  }

  public static com.example.batteryprice.model.graphql.types.Battery.Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {
    private String id;

    private Double price;

    private String batteryName;

    public Battery build() {
                  com.example.batteryprice.model.graphql.types.Battery result = new com.example.batteryprice.model.graphql.types.Battery();
                      result.id = this.id;
          result.price = this.price;
          result.batteryName = this.batteryName;
                      return result;
    }

    public com.example.batteryprice.model.graphql.types.Battery.Builder id(String id) {
      this.id = id;
      return this;
    }

    public com.example.batteryprice.model.graphql.types.Battery.Builder price(Double price) {
      this.price = price;
      return this;
    }

    public com.example.batteryprice.model.graphql.types.Battery.Builder batteryName(
        String batteryName) {
      this.batteryName = batteryName;
      return this;
    }
  }
}
