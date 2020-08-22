package com.besolutions.drbookingOriginal.Scenario.ScenarioMyFavorit.Model;//
//  FavoriteUser.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 4, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class FavoriteUser{

	@SerializedName("doctors")
	private FavoriteDoctor[] doctors;
	@SerializedName("status")
	private int status;

	public void setDoctors(FavoriteDoctor[] doctors){
		this.doctors = doctors;
	}
	public FavoriteDoctor[] getDoctors(){
		return this.doctors;
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
	public FavoriteUser(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray doctorsJsonArray = jsonObject.optJSONArray("doctors");
		if(doctorsJsonArray != null){
			ArrayList<FavoriteDoctor> doctorsArrayList = new ArrayList<>();
			for (int i = 0; i < doctorsJsonArray.length(); i++) {
				JSONObject doctorsObject = doctorsJsonArray.optJSONObject(i);
				doctorsArrayList.add(new FavoriteDoctor(doctorsObject));
			}
			doctors = (FavoriteDoctor[]) doctorsArrayList.toArray();
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
			if(doctors != null && doctors.length > 0){
				JSONArray doctorsJsonArray = new JSONArray();
				for(FavoriteDoctor doctorsElement : doctors){
					doctorsJsonArray.put(doctorsElement.toJsonObject());
				}
				jsonObject.put("doctors", doctorsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}