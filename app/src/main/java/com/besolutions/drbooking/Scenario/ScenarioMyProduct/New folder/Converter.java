package com.besolutions.drbooking.Scenario.ScenarioMyProduct.Utils;

import com.besolutions.drbooking.Scenario.ScenarioMyProduct.Model.MyProducts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public class Converter {
    // Serialize/deserialize helpers
    public static MyProducts fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }
    public static String toJsonString(MyProducts obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }
    private static ObjectReader reader;
    private static ObjectWriter writer;
    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        reader = mapper.reader(MyProducts.class);
        writer = mapper.writerFor(MyProducts.class);
    }
    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }
    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}