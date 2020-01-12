package com.besolutions.drbooking.Scenario.ScenarioAppointment.Model;//
//  ModelReservation_Details.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 5, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelReservation_Details{

	@SerializedName("reservations")
	private ModelReservation[] reservations;
	@SerializedName("status")
	private int status;

	public void setReservations(ModelReservation[] reservations){
		this.reservations = reservations;
	}
	public ModelReservation[] getReservations(){
		return this.reservations;
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
	public ModelReservation_Details(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray reservationsJsonArray = jsonObject.optJSONArray("reservations");
		if(reservationsJsonArray != null){
			ArrayList<ModelReservation> reservationsArrayList = new ArrayList<>();
			for (int i = 0; i < reservationsJsonArray.length(); i++) {
				JSONObject reservationsObject = reservationsJsonArray.optJSONObject(i);
				reservationsArrayList.add(new ModelReservation(reservationsObject));
			}
			reservations = (ModelReservation[]) reservationsArrayList.toArray();
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
			if(reservations != null && reservations.length > 0){
				JSONArray reservationsJsonArray = new JSONArray();
				for(ModelReservation reservationsElement : reservations){
					reservationsJsonArray.put(reservationsElement.toJsonObject());
				}
				jsonObject.put("reservations", reservationsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}