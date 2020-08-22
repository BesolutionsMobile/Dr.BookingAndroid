package com.besolutions.drbookingOriginal.Scenario.ScenarioMyProduct.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private String id;
    private String price;
    private String name;
    private String description;
    private String benefits;
    private String image;
    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }
    @JsonProperty("price")
    public String getPrice() { return price; }
    @JsonProperty("price")
    public void setPrice(String value) { this.price = value; }
    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }
    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }
    @JsonProperty("benefits")
    public String getBenefits() { return benefits; }
    @JsonProperty("benefits")
    public void setBenefits(String value) { this.benefits = value; }
    @JsonProperty("image")
    public String getImage() { return image; }
    @JsonProperty("image")
    public void setImage(String value) { this.image = value; }
}
