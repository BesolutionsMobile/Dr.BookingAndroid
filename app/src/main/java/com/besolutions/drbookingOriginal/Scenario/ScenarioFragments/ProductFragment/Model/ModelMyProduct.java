package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProductFragment.Model;//
//  ModelMyProduct.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 4, 2020

import org.json.*;
import java.util.*;

import com.google.gson.annotations.SerializedName;


public class ModelMyProduct{

	@SerializedName("products")
	private ModelProduct[] products;
	@SerializedName("status")
	private int status;

	public void setProducts(ModelProduct[] products){
		this.products = products;
	}
	public ModelProduct[] getProducts(){
		return this.products;
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
	public ModelMyProduct(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray productsJsonArray = jsonObject.optJSONArray("products");
		if(productsJsonArray != null){
			ArrayList<ModelProduct> productsArrayList = new ArrayList<>();
			for (int i = 0; i < productsJsonArray.length(); i++) {
				JSONObject productsObject = productsJsonArray.optJSONObject(i);
				productsArrayList.add(new ModelProduct(productsObject));
			}
			products = (ModelProduct[]) productsArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			if(products != null && products.length > 0){
				JSONArray productsJsonArray = new JSONArray();
				for(ModelProduct productsElement : products){
					productsJsonArray.put(productsElement.toJsonObject());
				}
				jsonObject.put("products", productsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}