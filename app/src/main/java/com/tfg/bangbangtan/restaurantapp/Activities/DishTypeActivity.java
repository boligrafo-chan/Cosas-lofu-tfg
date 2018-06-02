package com.tfg.bangbangtan.restaurantapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tfg.bangbangtan.restaurantapp.R;

public class DishTypeActivity extends AppCompatActivity {

	private ListView dishsubTlistview;
	private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dish_type);

		//dishsubTlistview= (ListView) findViewById(R.id.dishSubtypeList);
	}
}
