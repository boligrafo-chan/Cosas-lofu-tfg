package com.tfg.bangbangtan.restaurantapp.Models;

import com.google.gson.annotations.SerializedName;

public class DishSubtype {
	private int id;
	private String image;
	private String name;
	@SerializedName("dish_type_id")
	private int dishTypeId;

	public DishSubtype(String image, String name, int id, int dishTypeId) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.dishTypeId = dishTypeId;
	}

	public int getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDishTypeId() {
		return dishTypeId;
	}
}
