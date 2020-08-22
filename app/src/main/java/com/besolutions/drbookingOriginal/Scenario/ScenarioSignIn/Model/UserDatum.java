package com.besolutions.drbookingOriginal.Scenario.ScenarioSignIn.Model;//
//  UserDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 1, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class UserDatum{

	@SerializedName("address")
	private Object address;
	@SerializedName("city_id")
	private String cityId;
	@SerializedName("created")
	private String created;
	@SerializedName("created_by")
	private Object createdBy;
	@SerializedName("date")
	private String date;
	@SerializedName("deleted")
	private String deleted;
	@SerializedName("gender")
	private Object gender;
	@SerializedName("id")
	private String id;
	@SerializedName("job_type")
	private String jobType;
	@SerializedName("last_updated_by")
	private Object lastUpdatedBy;
	@SerializedName("mail")
	private String mail;
	@SerializedName("name")
	private String name;
	@SerializedName("password")
	private String password;
	@SerializedName("phone")
	private String phone;
	@SerializedName("photo")
	private Object photo;
	@SerializedName("status")
	private String status;
	@SerializedName("verified")
	private String verified;

	public void setAddress(Object address){
		this.address = address;
	}
	public Object getAddress(){
		return this.address;
	}
	public void setCityId(String cityId){
		this.cityId = cityId;
	}
	public String getCityId(){
		return this.cityId;
	}
	public void setCreated(String created){
		this.created = created;
	}
	public String getCreated(){
		return this.created;
	}
	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}
	public Object getCreatedBy(){
		return this.createdBy;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
	public String getDeleted(){
		return this.deleted;
	}
	public void setGender(Object gender){
		this.gender = gender;
	}
	public Object getGender(){
		return this.gender;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setJobType(String jobType){
		this.jobType = jobType;
	}
	public String getJobType(){
		return this.jobType;
	}
	public void setLastUpdatedBy(Object lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Object getLastUpdatedBy(){
		return this.lastUpdatedBy;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getMail(){
		return this.mail;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPhoto(Object photo){
		this.photo = photo;
	}
	public Object getPhoto(){
		return this.photo;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setVerified(String verified){
		this.verified = verified;
	}
	public String getVerified(){
		return this.verified;
	}


	public UserDatum() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public UserDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		cityId = String.valueOf(jsonObject.opt("city_id"));
		created = String.valueOf(jsonObject.opt("created"));
		date = String.valueOf(jsonObject.opt("date"));
		deleted = String.valueOf(jsonObject.opt("deleted"));
		id = String.valueOf(jsonObject.opt("id"));
		jobType = String.valueOf(jsonObject.opt("job_type"));
		mail = String.valueOf(jsonObject.opt("mail"));
		name = String.valueOf(jsonObject.opt("name"));
		password = String.valueOf(jsonObject.opt("password"));
		phone = String.valueOf(jsonObject.opt("phone"));
		status = String.valueOf(jsonObject.opt("status"));
		verified = String.valueOf(jsonObject.opt("verified"));
		address = jsonObject.opt("address");
		createdBy = jsonObject.opt("created_by");
		gender = jsonObject.opt("gender");
		lastUpdatedBy = jsonObject.opt("last_updated_by");
		photo = jsonObject.opt("photo");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("address", address);
			jsonObject.put("city_id", cityId);
			jsonObject.put("created", created);
			jsonObject.put("created_by", createdBy);
			jsonObject.put("date", date);
			jsonObject.put("deleted", deleted);
			jsonObject.put("gender", gender);
			jsonObject.put("id", id);
			jsonObject.put("job_type", jobType);
			jsonObject.put("last_updated_by", lastUpdatedBy);
			jsonObject.put("mail", mail);
			jsonObject.put("name", name);
			jsonObject.put("password", password);
			jsonObject.put("phone", phone);
			jsonObject.put("photo", photo);
			jsonObject.put("status", status);
			jsonObject.put("verified", verified);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}