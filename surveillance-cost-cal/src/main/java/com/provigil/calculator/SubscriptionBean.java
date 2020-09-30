package com.provigil.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubscriptionBean {
	
	public int id;
	public int area;
	public String planType;
	public String location;
	List<Map<Integer,Double >>mapList=new ArrayList<Map<Integer,Double>>();
	
	
	public List<Map<Integer, Double>> getMap() {
		return mapList;
	}
	public void setMap(List<Map<Integer, Double>> map) {
		this.mapList = map;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
