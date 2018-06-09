package com.tfg.bangbangtan.restaurantapp.Models;

import com.google.gson.annotations.SerializedName;

public class DishType {
	private int id;
	private String image;
	private String name;

	@SerializedName("has_subtypes")
	private boolean hasSubtypes;

	public DishType(String image, String name, int id) {
		this.image=image;
		this.name=name;
		this.id=id;
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

	public int getId() {
		return id;
	}

	public boolean getHasSubtypes() {
		return hasSubtypes;
	}

	public void setHasSubtypes(boolean hasSubtypes) {
		this.hasSubtypes = hasSubtypes;
	}
	/*No hay set id , seguridad*/
}
