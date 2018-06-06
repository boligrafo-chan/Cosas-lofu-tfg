package com.tfg.bangbangtan.restaurantapp.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tfg.bangbangtan.restaurantapp.Models.DishType;
import com.tfg.bangbangtan.restaurantapp.R;
import com.tfg.bangbangtan.restaurantapp.Utilities.DishTypeAdapter;
import com.tfg.bangbangtan.restaurantapp.ViewModels.DishTypeViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class DishTypeActivity extends AppCompatActivity {

	@BindView(R.id.mainDishTypeList)
	ListView dishTypesListView;

	DishTypeViewModel mDishTypeViewModel;
	DishTypeAdapter dishTypeAdapter;
	List<DishType> dishTypes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		dishTypes = new ArrayList<>();
		mDishTypeViewModel = ViewModelProviders.of(this).get(DishTypeViewModel.class);
		dishTypeAdapter = new DishTypeAdapter(DishTypeActivity.this, R.layout.list_item_dishtype, dishTypes);
		dishTypesListView.setAdapter(dishTypeAdapter);
		loadDishTypes();
	}

	@OnItemClick(R.id.mainDishTypeList)
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(DishTypeActivity.this, "Click en elemento", Toast.LENGTH_LONG).show();
	}

	private void loadDishTypes() {
		final LiveData<List<DishType>> dishTypesLiveData = mDishTypeViewModel.getDishTypesList();
		Observer<List<DishType>> dishTypesResponseObserver = new Observer<List<DishType>>() {
			@Override
			public void onChanged(@Nullable List<DishType> dishTypes) {
				if (dishTypes != null) {
					updateDishTypesOnUI(dishTypes);
					dishTypesLiveData.removeObserver(this);
				}
			}
		};
		dishTypesLiveData.removeObservers(this);
		dishTypesLiveData.observe(DishTypeActivity.this, dishTypesResponseObserver);
	}

	private void updateDishTypesOnUI(List<DishType> dishTypes) {
		this.dishTypes.clear();
		this.dishTypes.addAll(dishTypes);
		dishTypeAdapter.notifyDataSetChanged();
		Toast.makeText(DishTypeActivity.this, "DishTypes obtenidos", Toast.LENGTH_LONG).show();
	}
}