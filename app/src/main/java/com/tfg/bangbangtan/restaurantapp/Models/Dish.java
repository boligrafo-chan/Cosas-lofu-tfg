package com.tfg.bangbangtan.restaurantapp.Models;

public class Dish extends MenuItem {
	private int id;
	private String description;
	private int type;
	private int subtype;
	private double price;


	public Dish(String name, String image, String description, int type, int subtype, double price){
		this.description = description;
		this.type = type;
		this.subtype = subtype;
		this.price=price;
		setName(name);
		setImage(image);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}


	public int getSubtype() {
		return subtype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}


}
