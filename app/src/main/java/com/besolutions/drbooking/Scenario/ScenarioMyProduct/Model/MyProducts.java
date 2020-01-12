package com.besolutions.drbooking.Scenario.ScenarioMyProduct.Model;

// Converter.java
// To use this code, add the following Maven dependency to your project:
//
//     com.fasterxml.jackson.core : jackson-databind : 2.9.0
//
// Import this package:
//
//     import io.quicktype.Converter;
//
// Then you can deserialize a JSON string with
//
//     MyProducts data = Converter.fromJsonString(jsonString);


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

// MyProducts.java

public class MyProducts {
    private long status;
    private Map<String, Product> products;
    @JsonProperty("status")
    public long getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(long value) { this.status = value; }
    @JsonProperty("products")
    public Map<String, Product> getProducts() { return products; }
    @JsonProperty("products")
    public void setProducts(Map<String, Product> value) { this.products = value; }
}

