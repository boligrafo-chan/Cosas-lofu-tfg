package com.tfg.bangbangtan.restaurantapp.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tfg.bangbangtan.restaurantapp.Models.CustomDish;
import com.tfg.bangbangtan.restaurantapp.Models.ExtraIngredient;
import com.tfg.bangbangtan.restaurantapp.Models.Order;
import com.tfg.bangbangtan.restaurantapp.R;
import com.tfg.bangbangtan.restaurantapp.ViewModels.OrderViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {

	OrderViewModel orderViewModel;

	@BindView(R.id.order_cost_txt)
	TextView order_cost_txt;
	@BindView(R.id.send_order_button)
	Button send_order_button;

	@BindView(R.id.textView358)
	TextView textID;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		ButterKnife.bind(this);
		orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
		order_cost_txt.setText(String.valueOf(Order.getInstance().getCost()));
		send_order_button.setOnClickListener(this::onClickSendOrder);
	}

	private void createOrder() {
		final LiveData<Order> orderLiveData = orderViewModel.createOrder();
		Observer<Order> orderResponseObserver = new ObserverOrder(orderLiveData);
		orderLiveData.removeObservers(this);
		orderLiveData.observe(OrderActivity.this, orderResponseObserver);
	}

	private void createOrderCustomDishes() {
		for (CustomDish customDish : Order.getInstance().getCustomDishes()) {
			final LiveData<CustomDish> customDishLiveData = orderViewModel.createCustomDish(customDish);
			Observer<CustomDish> customDishResponseObserver = new ObserverCustomDish(customDishLiveData);
			customDishLiveData.removeObservers(this);
			customDishLiveData.observe(OrderActivity.this, customDishResponseObserver);
		}
	}

	private void updateOrder(Order order) {
		Order.getInstance().setId(order.getId());
		//prueba
		textID.setText(String.valueOf(Order.getInstance().getId()));
		//fin prueba
		createOrderCustomDishes();

	}

	private void onClickSendOrder(View v) {
		send_order_button.setVisibility(View.INVISIBLE);
		createOrder();
		Toast.makeText(OrderActivity.this, "Creado Order de id: " + Order.getInstance().getId(), Toast.LENGTH_LONG).show();
	}

	private class ObserverOrder implements Observer<Order> {
		private LiveData<Order> orderLiveData;

		ObserverOrder(LiveData<Order> orderLiveData) {
			this.orderLiveData = orderLiveData;
		}

		@Override
		public void onChanged(@Nullable Order order) {
			if (order != null) {
				updateOrder(order);
				orderLiveData.removeObserver(this);
			}
		}
	}

	private class ObserverCustomDish implements Observer<CustomDish> {
		private LiveData<CustomDish> customDishLiveData;

		ObserverCustomDish(LiveData<CustomDish> customDishLiveData) {
			this.customDishLiveData = customDishLiveData;
		}

		@Override
		public void onChanged(@Nullable CustomDish customDish) {
			if (customDish != null) {
				customDishLiveData.removeObserver(this);
			}
		}
	}
}
