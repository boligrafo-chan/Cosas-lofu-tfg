package com.tfg.bangbangtan.restaurantapp.Repositories;

import android.arch.lifecycle.MutableLiveData;

import com.tfg.bangbangtan.restaurantapp.API_Management.APIManager;
import com.tfg.bangbangtan.restaurantapp.Models.CustomDish;

public class CustomDishRepository {

	public MutableLiveData<CustomDish> createCustomDish(CustomDish customDish){
		final MutableLiveData<CustomDish> responseLiveData= new MutableLiveData<>();
		APIManager.getInstance().createCustomDish(customDish, new APIManager.ResponseCallback<CustomDish>() {
			@Override
			public void OnResponseSuccess(CustomDish responseObject) {
				responseLiveData.setValue(responseObject);
			}

			@Override
			public void OnResponseFailure() {

			}
		});
		return responseLiveData;
	}
}
