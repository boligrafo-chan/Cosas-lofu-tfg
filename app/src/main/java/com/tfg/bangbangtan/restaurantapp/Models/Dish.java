package com.tfg.bangbangtan.restaurantapp.Models;

public class Dish {
	private int id;
	private String name;
	private String image;
	private String description;
	private int type;
	private int subtype;
	private double price;


	public Dish(int id, String image, String name, String description, int type, int subtype, double price){
		this.image = image;
		this.name=name;
		this.description = description;
		this.type = type;
		this.subtype = subtype;
		this.price=price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
