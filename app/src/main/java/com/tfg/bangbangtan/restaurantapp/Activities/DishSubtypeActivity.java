package com.tfg.bangbangtan.restaurantapp.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tfg.bangbangtan.restaurantapp.Models.DishSubtype;
import com.tfg.bangbangtan.restaurantapp.R;
import com.tfg.bangbangtan.restaurantapp.Utilities.AppString;
import com.tfg.bangbangtan.restaurantapp.Utilities.DishSubtypeAdapter;
import com.tfg.bangbangtan.restaurantapp.ViewModels.DishSubtypeViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DishSubtypeActivity extends AppCompatActivity {

	DishSubtypeViewModel dishSubtypeViewModel;
	List<DishSubtype> dishSubtypes;

	@BindView(R.id.recycler_list_view)
	RecyclerView dishSubtypesListView;
	DishSubtypeAdapter dishSubtypeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_list);
		ButterKnife.bind(this);
		dishSubtypes = new ArrayList<>();
		dishSubtypeViewModel = ViewModelProviders.of(this).get(DishSubtypeViewModel.class);
		dishSubtypeAdapter = new DishSubtypeAdapter(dishSubtypes);
		dishSubtypesListView.setAdapter(dishSubtypeAdapter);

		RecyclerView.LayoutManager llmanager = new LinearLayoutManager(this);
		//llmanager.setOrientation(LinearLayoutManager.VERTICAL);
		dishSubtypesListView.setLayoutManager(llmanager);

		int dishTypeId = getIntent().getIntExtra(AppString.CLICKED_ITEM_ID, 0);
		loadDishSubtypes(dishTypeId);
	}

	private void loadDishSubtypes(int dishTypeId) {
		final LiveData<List<DishSubtype>> dishSubtypesLiveData = dishSubtypeViewModel.getDishSubtypesList(dishTypeId);
		Observer<List<DishSubtype>> dishSubtypesResponseObserver = new DishSubtypeActivity.ObserverDishSubtypes(dishSubtypesLiveData);
		dishSubtypesLiveData.removeObservers(this);
		dishSubtypesLiveData.observe(DishSubtypeActivity.this, dishSubtypesResponseObserver);
	}

	private void updateDishSubtypesOnUI(List<DishSubtype> dishSubtypes) {
		this.dishSubtypes.clear();
		this.dishSubtypes.addAll(dishSubtypes);
		dishSubtypeAdapter.notifyDataSetChanged();
		Toast.makeText(DishSubtypeActivity.this, "DishSubtypes obtenidos", Toast.LENGTH_LONG).show();
	}

	private class ObserverDishSubtypes implements Observer<List<DishSubtype>> {
		private LiveData<List<DishSubtype>> dishSubtypesLiveData;

		ObserverDishSubtypes(LiveData<List<DishSubtype>> dishSubtypesLiveData) {
			this.dishSubtypesLiveData = dishSubtypesLiveData;
		}

		@Override
		public void onChanged(@Nullable List<DishSubtype> dishTypes) {
			if (dishTypes != null) {
				updateDishSubtypesOnUI(dishTypes);
				dishSubtypesLiveData.removeObserver(this);
			}
		}
	}
}
