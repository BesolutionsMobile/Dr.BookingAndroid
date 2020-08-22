package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.Doctor_Details_Fragment.Model;//
//  ModelDoctor_Details.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 5, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelDoctor_Details{

	@SerializedName("dates")
	private ModelDate[] dates;
	@SerializedName("doctor")
	private ModelDoctor doctor;
	@SerializedName("status")
	private int status;

	public void setDates(ModelDate[] dates){
		this.dates = dates;
	}
	public ModelDate[] getDates(){
		return this.dates;
	}
	public void setDoctor(ModelDoctor doctor){
		this.doctor = doctor;
	}
	public ModelDoctor getDoctor(){
		return this.doctor;
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
	public ModelDoctor_Details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		doctor = new ModelDoctor(jsonObject.optJSONObject("doctor"));
		JSONArray datesJsonArray = jsonObject.optJSONArray("dates");
		if(datesJsonArray != null){
			ArrayList<ModelDate> datesArrayList = new ArrayList<>();
			for (int i = 0; i < datesJsonArray.length(); i++) {
				JSONObject datesObject = datesJsonArray.optJSONObject(i);
				datesArrayList.add(new ModelDate(datesObject));
			}
			dates = (ModelDate[]) datesArrayList.toArray();
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
			jsonObject.put("doctor", doctor.toJsonObject());
			if(dates != null && dates.length > 0){
				JSONArray datesJsonArray = new JSONArray();
				for(ModelDate datesElement : dates){
					datesJsonArray.put(datesElement.toJsonObject());
				}
				jsonObject.put("dates", datesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}