package com.example.batteryprice.model.graphql.client;

import com.netflix.graphql.dgs.client.codegen.GraphQLQuery;
import java.lang.Override;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

public class AllBatteriesGraphQLQuery extends GraphQLQuery {
  public AllBatteriesGraphQLQuery() {
    super("query");
  }

  @Override
  public String getOperationName() {
     return "allBatteries";
                    
  }

  public static Builder newRequest() {
    return new Builder();
  }

  public static class Builder {
    private Set<String> fieldsSet = new HashSet<>();

    public AllBatteriesGraphQLQuery build() {
      return new AllBatteriesGraphQLQuery();                                     
    }
  }
}
