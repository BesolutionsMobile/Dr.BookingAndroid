package com.besolutions.drbooking.Scenario.ScenarioAppointment.Model;//
//  ModelReservation.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 5, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelReservation{

	@SerializedName("date")
	private String date;
	@SerializedName("doctor")
	private String doctor;
	@SerializedName("doctor_id")
	private String doctorId;
	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("job_title")
	private String jobTitle;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("longitude")
	private String longitude;
	@SerializedName("place")
	private String place;
	@SerializedName("price")
	private String price;
	@SerializedName("rate")
	private float rate;
	@SerializedName("timeFrom")
	private String timeFrom;
	@SerializedName("timeTo")
	private String timeTo;

	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	public void setDoctor(String doctor){
		this.doctor = doctor;
	}
	public String getDoctor(){
		return this.doctor;
	}
	public void setDoctorId(String doctorId){
		this.doctorId = doctorId;
	}
	public String getDoctorId(){
		return this.doctorId;
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
	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
	public String getJobTitle(){
		return this.jobTitle;
	}
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
	public String getLatitude(){
		return this.latitude;
	}
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
	public String getLongitude(){
		return this.longitude;
	}
	public void setPlace(String place){
		this.place = place;
	}
	public String getPlace(){
		return this.place;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setRate(float rate){
		this.rate = rate;
	}
	public float getRate(){
		return this.rate;
	}
	public void setTimeFrom(String timeFrom){
		this.timeFrom = timeFrom;
	}
	public String getTimeFrom(){
		return this.timeFrom;
	}
	public void setTimeTo(String timeTo){
		this.timeTo = timeTo;
	}
	public String getTimeTo(){
		return this.timeTo;
	}

	public ModelReservation() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelReservation(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		date = String.valueOf(jsonObject.opt("date"));
		doctor = String.valueOf(jsonObject.opt("doctor"));
		doctorId = String.valueOf(jsonObject.opt("doctor_id"));
		id = String.valueOf(jsonObject.opt("id"));
		image = String.valueOf(jsonObject.opt("image"));
		jobTitle = String.valueOf(jsonObject.opt("job_title"));
		latitude = String.valueOf(jsonObject.opt("latitude"));
		longitude = String.valueOf(jsonObject.opt("longitude"));
		place = String.valueOf(jsonObject.opt("place"));
		price = String.valueOf(jsonObject.opt("price"));
		timeFrom = String.valueOf(jsonObject.opt("timeFrom"));
		timeTo = String.valueOf(jsonObject.opt("timeTo"));
		rate = (float) jsonObject.optDouble("rate");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("date", date);
			jsonObject.put("doctor", doctor);
			jsonObject.put("doctor_id", doctorId);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("job_title", jobTitle);
			jsonObject.put("latitude", latitude);
			jsonObject.put("longitude", longitude);
			jsonObject.put("place", place);
			jsonObject.put("price", price);
			jsonObject.put("rate", rate);
			jsonObject.put("timeFrom", timeFrom);
			jsonObject.put("timeTo", timeTo);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}