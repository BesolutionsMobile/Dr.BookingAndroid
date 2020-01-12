package com.besolutions.drbooking.Scenario.ScenarioProductDetails.Model;//
//  ModelProductDetails.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 4, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class ModelProductDetails{

	@SerializedName("product")
	private Details_Product product;
	@SerializedName("status")
	private int status;

	public void setProduct(Details_Product product){
		this.product = product;
	}
	public Details_Product getProduct(){
		return this.product;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelProductDetails(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		product = new Details_Product(jsonObject.optJSONObject("product"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("product", product.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}