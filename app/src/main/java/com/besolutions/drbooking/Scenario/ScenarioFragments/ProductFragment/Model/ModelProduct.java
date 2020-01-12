package com.besolutions.drbooking.Scenario.ScenarioFragments.ProductFragment.Model;//
//  ModelProduct.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 4, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelProduct{

	@SerializedName("benefits")
	private String benefits;
	@SerializedName("description")
	private String description;
	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;
	@SerializedName("price")
	private String price;

	public void setBenefits(String benefits){
		this.benefits = benefits;
	}
	public String getBenefits(){
		return this.benefits;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}


	public ModelProduct() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelProduct(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		benefits = String.valueOf(jsonObject.opt("benefits"));
		description = String.valueOf(jsonObject.opt("description"));
		id = String.valueOf(jsonObject.opt("id"));
		image = String.valueOf(jsonObject.opt("image"));
		name = String.valueOf(jsonObject.opt("name"));
		price = String.valueOf(jsonObject.opt("price"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("benefits", benefits);
			jsonObject.put("description", description);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			jsonObject.put("price", price);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}