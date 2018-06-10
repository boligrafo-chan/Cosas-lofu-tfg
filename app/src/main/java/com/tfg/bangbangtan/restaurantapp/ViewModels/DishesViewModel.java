package com.tfg.bangbangtan.restaurantapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.tfg.bangbangtan.restaurantapp.Models.Dish;
import com.tfg.bangbangtan.restaurantapp.Repositories.DishesRepository;

import java.util.List;

public class DishesViewModel extends AndroidViewModel {

	DishesRepository dishesRepository;

	public DishesViewModel(@NonNull Application application) {
		super(application);
		dishesRepository = new DishesRepository();
	}

	public LiveData<List<Dish>> getDishes(int dishTypeId, int dishSubtypeId){
		return dishesRepository.getDishes(dishTypeId, dishSubtypeId);
	}
}
