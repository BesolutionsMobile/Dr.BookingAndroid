package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.ProfileFragment.Model;//
//  ModelEditProfile.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 4, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class ModelEditProfile{

	@SerializedName("status")
	private int status;
	@SerializedName("user")
	private ModelUser user;

	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setUser(ModelUser user){
		this.user = user;
	}
	public ModelUser getUser(){
		return this.user;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelEditProfile(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		user = new ModelUser(jsonObject.optJSONObject("user"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("user", user.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}