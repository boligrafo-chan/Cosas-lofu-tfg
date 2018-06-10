package com.tfg.bangbangtan.restaurantapp.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.tfg.bangbangtan.restaurantapp.Models.DishType;
import com.tfg.bangbangtan.restaurantapp.R;
import com.tfg.bangbangtan.restaurantapp.Utilities.AppString;
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

	DishTypeViewModel dishTypeViewModel;
	DishTypeAdapter dishTypeAdapter;
	List<DishType> dishTypes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		dishTypes = new ArrayList<>();
		dishTypeViewModel = ViewModelProviders.of(this).get(DishTypeViewModel.class);
		dishTypeAdapter = new DishTypeAdapter(DishTypeActivity.this, R.layout.list_menu_item, dishTypes);
		dishTypesListView.setAdapter(dishTypeAdapter);
		loadDishTypes();
	}

	@OnItemClick(R.id.mainDishTypeList)
	public void onItemClick(int position) {
		boolean hasSubtypes = dishTypes.get(position).getHasSubtypes();
		if (hasSubtypes) {
			//IR A ACTIVITY CON SUBTIPOS
			Intent showSubtypes;
			showSubtypes = new Intent(DishTypeActivity.this, DishSubtypeActivity.class);
			showSubtypes.putExtra(AppString.CLICKED_HAS_SUBTYPE, true);
			showSubtypes.putExtra(AppString.CLICKED_ITEM_ID, dishTypes.get(position).getId());
			startActivity(showSubtypes);
		}else {
			//IR A ACTIVITY CON PLATOS
			Toast.makeText(DishTypeActivity.this, "Tipo de plato sin subtipos", Toast.LENGTH_SHORT).show();
		}
	}

	private void loadDishTypes() {
		final LiveData<List<DishType>> dishTypesLiveData = dishTypeViewModel.getDishTypesList();
		Observer<List<DishType>> dishTypesResponseObserver = new ObserverDishTypes(dishTypesLiveData);
		dishTypesLiveData.removeObservers(this);
		dishTypesLiveData.observe(DishTypeActivity.this, dishTypesResponseObserver);
	}

	private void updateDishTypesOnUI(List<DishType> dishTypes) {
		this.dishTypes.clear();
		this.dishTypes.addAll(dishTypes);
		dishTypeAdapter.notifyDataSetChanged();
		Toast.makeText(DishTypeActivity.this, "DishTypes obtenidos", Toast.LENGTH_LONG).show();
	}

	private class ObserverDishTypes implements Observer<List<DishType>> {
		private LiveData<List<DishType>> dishTypesLiveData;

		ObserverDishTypes(LiveData<List<DishType>> dishTypesLiveData) {
			this.dishTypesLiveData = dishTypesLiveData;
		}

		@Override
		public void onChanged(@Nullable List<DishType> dishTypes) {
			if (dishTypes != null) {
				updateDishTypesOnUI(dishTypes);
				dishTypesLiveData.removeObserver(this);
			}
		}
	}
}