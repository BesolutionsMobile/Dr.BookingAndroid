package com.besolutions.drbookingOriginal.Scenario.ScenarioFragments.SearchFragment.Model;//
//  Doctor.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 2, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Doctor{

	@SerializedName("address")
	private String address;
	@SerializedName("description")
	private String description;
	@SerializedName("favorite")
	private int favorite;
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
	@SerializedName("name")
	private String name;
	@SerializedName("price")
	private String price;
	@SerializedName("rating")
	private float rating;
	private  Boolean  isFav = false;

	public Boolean getIsFav(){
		return isFav;
	}
	public void setIsFav(Boolean favs) {
		isFav = favs;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setFavorite(int favorite){
		this.favorite = favorite;
	}
	public int getFavorite(){
		return this.favorite;
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
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setRating(float rating){
		this.rating = rating;
	}
	public float getRating(){
		return this.rating;
	}


	public Doctor() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Doctor(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = String.valueOf(jsonObject.opt("address"));
		description = String.valueOf(jsonObject.opt("description"));
		id = String.valueOf(jsonObject.opt("id"));
		image = String.valueOf(jsonObject.opt("image"));
		jobTitle = String.valueOf(jsonObject.opt("job_title"));
		latitude = String.valueOf(jsonObject.opt("latitude"));
		longitude = String.valueOf(jsonObject.opt("longitude"));
		name = String.valueOf(jsonObject.opt("name"));
		price = String.valueOf(jsonObject.opt("price"));
		favorite = jsonObject.optInt("favorite");
		rating = (float) jsonObject.optDouble("rating");

	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("address", address);
			jsonObject.put("description", description);
			jsonObject.put("favorite", favorite);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("job_title", jobTitle);
			jsonObject.put("latitude", latitude);
			jsonObject.put("longitude", longitude);
			jsonObject.put("name", name);
			jsonObject.put("price", price);
			jsonObject.put("rating", rating);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}