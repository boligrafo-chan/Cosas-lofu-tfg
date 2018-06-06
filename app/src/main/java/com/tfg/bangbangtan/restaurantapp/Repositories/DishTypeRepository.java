package com.tfg.bangbangtan.restaurantapp.Repositories;

import android.arch.lifecycle.MutableLiveData;

import com.tfg.bangbangtan.restaurantapp.API_Management.APIManager;
import com.tfg.bangbangtan.restaurantapp.Models.DishType;

import java.util.List;

public class DishTypeRepository {

	public MutableLiveData<List<DishType>> getDishTypesList() {
		final MutableLiveData<List<DishType>> getDishTypesResponseMutableLiveData = new MutableLiveData<>();

		APIManager.getInstance().getDishTypes(new APIManager.ResponseCallback<List<DishType>>() {
			@Override
			public void OnResponseSuccess(List<DishType> responseObject) {
				getDishTypesResponseMutableLiveData.setValue(responseObject);
			}

			@Override
			public void OnResponseFailure() {

			}
		});
		return getDishTypesResponseMutableLiveData;
	}
}