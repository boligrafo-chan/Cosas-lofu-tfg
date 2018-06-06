package com.tfg.bangbangtan.restaurantapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import android.support.annotation.NonNull;

import com.tfg.bangbangtan.restaurantapp.Models.DishType;
import com.tfg.bangbangtan.restaurantapp.Repositories.DishTypeRepository;

import java.util.List;

public class DishTypeViewModel extends AndroidViewModel {

	private DishTypeRepository mDishTypeRepository;


	public DishTypeViewModel(@NonNull Application application) {
		super(application);
		mDishTypeRepository = new DishTypeRepository();
	}

	public LiveData<List<DishType>> getDishTypesList() {
		return mDishTypeRepository.getDishTypesList();
	}
}
