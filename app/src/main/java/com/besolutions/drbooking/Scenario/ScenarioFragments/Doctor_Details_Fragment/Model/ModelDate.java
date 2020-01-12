package com.besolutions.drbooking.Scenario.ScenarioFragments.Doctor_Details_Fragment.Model;//
//  ModelDate.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 5, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelDate{

	@SerializedName("created")
	private String created;
	@SerializedName("created_by")
	private String createdBy;
	@SerializedName("deleted")
	private String deleted;
	@SerializedName("from_time")
	private String fromTime;
	@SerializedName("id")
	private String id;
	@SerializedName("id_user")
	private String idUser;
	@SerializedName("last_updated_by")
	private String lastUpdatedBy;
	@SerializedName("place")
	private String place;
	@SerializedName("reservation_date")
	private String reservationDate;
	@SerializedName("status")
	private String status;
	@SerializedName("to_time")
	private String toTime;
	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreated(String created){
		this.created = created;
	}
	public String getCreated(){
		return this.created;
	}
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	public String getCreatedBy(){
		return this.createdBy;
	}
	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
	public String getDeleted(){
		return this.deleted;
	}
	public void setFromTime(String fromTime){
		this.fromTime = fromTime;
	}
	public String getFromTime(){
		return this.fromTime;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setIdUser(String idUser){
		this.idUser = idUser;
	}
	public String getIdUser(){
		return this.idUser;
	}
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedBy(){
		return this.lastUpdatedBy;
	}
	public void setPlace(String place){
		this.place = place;
	}
	public String getPlace(){
		return this.place;
	}
	public void setReservationDate(String reservationDate){
		this.reservationDate = reservationDate;
	}
	public String getReservationDate(){
		return this.reservationDate;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setToTime(String toTime){
		this.toTime = toTime;
	}
	public String getToTime(){
		return this.toTime;
	}
	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}
	public String getUpdatedAt(){
		return this.updatedAt;
	}


	public ModelDate() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelDate(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		created = String.valueOf(jsonObject.opt("created"));
		createdBy = String.valueOf(jsonObject.opt("created_by"));
		deleted = String.valueOf(jsonObject.opt("deleted"));
		fromTime = String.valueOf(jsonObject.opt("from_time"));
		id = String.valueOf(jsonObject.opt("id"));
		idUser = String.valueOf(jsonObject.opt("id_user"));
		lastUpdatedBy = String.valueOf(jsonObject.opt("last_updated_by"));
		place = String.valueOf(jsonObject.opt("place"));
		reservationDate = String.valueOf(jsonObject.opt("reservation_date"));
		status = String.valueOf(jsonObject.opt("status"));
		toTime = String.valueOf(jsonObject.opt("to_time"));
		updatedAt = String.valueOf(jsonObject.opt("updatedAt"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("created", created);
			jsonObject.put("created_by", createdBy);
			jsonObject.put("deleted", deleted);
			jsonObject.put("from_time", fromTime);
			jsonObject.put("id", id);
			jsonObject.put("id_user", idUser);
			jsonObject.put("last_updated_by", lastUpdatedBy);
			jsonObject.put("place", place);
			jsonObject.put("reservation_date", reservationDate);
			jsonObject.put("status", status);
			jsonObject.put("to_time", toTime);
			jsonObject.put("updatedAt", updatedAt);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}