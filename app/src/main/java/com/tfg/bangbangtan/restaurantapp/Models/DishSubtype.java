package com.tfg.bangbangtan.restaurantapp.Models;

public class DishSubtype extends DishType {
	private int dish_type_id;

	public DishSubtype(String image, String name, int priority, int id, int dish_type_id) {
		super(image, name, priority, id);
		this.dish_type_id=dish_type_id;
	}

	public int getDishTypeId() {
		return dish_type_id;
	}


}
