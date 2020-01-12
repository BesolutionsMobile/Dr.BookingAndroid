package com.besolutions.drbooking.Scenario.ScenarioSignUp.Model;//
//  Model_SignUp.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 2, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Model_SignUp{

	@SerializedName("id_user")
	private String idUser;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}
	public String getIdUser(){
		return this.idUser;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
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
	public Model_SignUp(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		idUser = String.valueOf(jsonObject.opt("id_user"));
		message = String.valueOf(jsonObject.opt("message"));
		status = jsonObject.optInt("status");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id_user", idUser);
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}