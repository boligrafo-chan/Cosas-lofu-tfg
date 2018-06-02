package com.tfg.bangbangtan.restaurantapp.API_Management;

import com.tfg.bangbangtan.restaurantapp.Models.DishSubtype;
import com.tfg.bangbangtan.restaurantapp.Models.DishType;
import com.tfg.bangbangtan.restaurantapp.Models.ExtraIngredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantService {

	@GET("dishType/{dishType}/extraIngredients")
	Call<List<ExtraIngredient>> getRelatedExtras(@Path("dishType") int type_id);

	@GET("dishType/{main_item_id}/dishSubtypes")
	Call<List<DishSubtype>> getsubTypesFromType(@Path("main_item_id") int type_id);

	@GET("dishType")
	Call<List<DishType>> getAllDishTypes();
}
