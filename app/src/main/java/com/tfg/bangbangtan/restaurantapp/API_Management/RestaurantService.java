package com.tfg.bangbangtan.restaurantapp.API_Management;

import com.tfg.bangbangtan.restaurantapp.Models.Dish;
import com.tfg.bangbangtan.restaurantapp.Models.DishSubtype;
import com.tfg.bangbangtan.restaurantapp.Models.DishType;
import com.tfg.bangbangtan.restaurantapp.Models.ExtraIngredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantService {

	@GET("dishTypes/{dishType}/extraIngredients")
	Call<List<ExtraIngredient>> getRelatedExtras(@Path("dishType") int dishTypeId);

	@GET("dishTypes/{main_item_id}/dishSubtypes")
	Call<List<DishSubtype>> getsubTypesFromType(@Path("main_item_id") int dishTypeId);

	@GET("dishTypes")
	Call<List<DishType>> getAllDishTypes();

	@GET("dishTypes/{dishTypeId}/dishes")
	Call<List<Dish>> getDishes(@Path("dishTypeId") int dishTypeId);

	@GET("dishTypes/{dishTypeId}/dishSubtypes/{dishSubtypeId}/dishes")
	Call<List<Dish>> getDishes(@Path("dishTypeId") int dishTypeId, @Path("dishSubtypeId") int dishSubtypeId);
}
