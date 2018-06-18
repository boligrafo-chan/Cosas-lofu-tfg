package com.tfg.bangbangtan.restaurantapp.API_Management;

import com.tfg.bangbangtan.restaurantapp.Models.Dish;
import com.tfg.bangbangtan.restaurantapp.Models.DishSubtype;
import com.tfg.bangbangtan.restaurantapp.Models.DishType;
import com.tfg.bangbangtan.restaurantapp.Models.ExtraIngredient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

	private static final String API_URL = "http://35.176.234.214/api/";
	private static APIManager instance;
	private static RestaurantService restaurantService;

	private APIManager() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(API_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		restaurantService = retrofit.create(RestaurantService.class);
	}

	public static APIManager getInstance() {
		if (instance == null) {
			instance = new APIManager();
		}
		return instance;
	}

	public void getDishSubtypes(int dishTypeId, final ResponseCallback<List<DishSubtype>> responseCallback) {
		Call<List<DishSubtype>> subtypesCall = restaurantService.getsubTypesFromType(dishTypeId);
		subtypesCall.enqueue(new Callback<List<DishSubtype>>() {
			@Override
			public void onResponse(Call<List<DishSubtype>> call, Response<List<DishSubtype>> response) {
				responseCallback.OnResponseSuccess(response.body());
			}

			@Override
			public void onFailure(Call<List<DishSubtype>> call, Throwable t) {
				responseCallback.OnResponseFailure();
			}
		});
	}

	public void getDishTypes(final ResponseCallback<List<DishType>> responseCallback) {
		Call<List<DishType>> typesCall = restaurantService.getAllDishTypes();

		typesCall.enqueue(new Callback<List<DishType>>() {
			@Override
			public void onResponse(Call<List<DishType>> call, Response<List<DishType>> response) {
				responseCallback.OnResponseSuccess(response.body());
			}

			@Override
			public void onFailure(Call<List<DishType>> call, Throwable t) {
				responseCallback.OnResponseFailure();
			}
		});
	}

	public List<ExtraIngredient> getRelatedExtras(int dishTypeId) {
		List<ExtraIngredient> result = null;
		Call<List<ExtraIngredient>> typesCall = restaurantService.getRelatedExtras(dishTypeId);
		return result;
	}

	public void getDishes(int dishTypeId, int dishSubtypeId, final ResponseCallback<List<Dish>> responseCallback) {
		Call<List<Dish>> dishesCall;
		if(dishSubtypeId == 0){
			dishesCall = restaurantService.getDishesByType(dishTypeId);
		}else{
			dishesCall = restaurantService.getDishesBySubtype(dishSubtypeId);
		}

		dishesCall.enqueue(new Callback<List<Dish>>() {
			@Override
			public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
				responseCallback.OnResponseSuccess(response.body());
			}

			@Override
			public void onFailure(Call<List<Dish>> call, Throwable t) {
				responseCallback.OnResponseFailure();
			}
		});
	}

	public interface ResponseCallback<T>{
		void OnResponseSuccess(T responseObject);
		void OnResponseFailure();
	}
}
