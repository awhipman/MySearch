package com.html;

public class Locate {
	private String province;
	private String area;
	private String api;
	private String lng;
	private String lat;
	private String date;
	private String hour;
	private String sort;
	
	Locate(){
		;
	}
	
	Locate(String province, String area, String api, String date){
		this.province = province;
		this.area = area;
		this.api = api;
		this.date = date;
	}
	
	Locate(String province, String area, String api, String lat, String lng, String date){
		this.province = province;
		this.area = area;
		this.api = api;
		this.lat = lat;
		this.lng = lng;
		this.date = date;
	}
	
	
	public void setLocate(String lng, String lat) {
		this.lng = lng;
		this.lat = lat;
	}
	
	public String getAddress() {
		return this.province + this.area;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
