package com.besolutions.drbooking.Scenario.ScenarioSignIn.Model;//
//  Model_SignIn.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 1, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_SignIn{

	@SerializedName("status")
	private int status;
	@SerializedName("user_data")
	private UserDatum userData;

	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setUserData(UserDatum userData){
		this.userData = userData;
	}
	public UserDatum getUserData(){
		return this.userData;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Model_SignIn(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		userData = new UserDatum(jsonObject.optJSONObject("user_data"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			jsonObject.put("userData", userData.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}