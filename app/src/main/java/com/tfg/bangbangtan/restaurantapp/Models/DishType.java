package com.tfg.bangbangtan.restaurantapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DishType {
	private int id;
	private String image;
	private String name;
	private int priority;

	@SerializedName("dish_subtypes")
	private List<DishSubtype> dishSubtypes;

	public DishType(String image , String name , int priority, int id) {
		this.image=image;
		this.name=name;
		this.priority = priority;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public List<DishSubtype> getDishSubtypes() {
		return dishSubtypes;
	}

	public void setDishSubtypes(List<DishSubtype> dishSubtypes) {
		this.dishSubtypes = dishSubtypes;
	}

	/*No hay set id , seguridad*/
}
