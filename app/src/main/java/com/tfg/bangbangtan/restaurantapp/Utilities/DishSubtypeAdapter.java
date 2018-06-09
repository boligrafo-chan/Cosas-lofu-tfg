package com.tfg.bangbangtan.restaurantapp.Utilities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tfg.bangbangtan.restaurantapp.Activities.DishSubtypeActivity;
import com.tfg.bangbangtan.restaurantapp.Models.DishSubtype;
import com.tfg.bangbangtan.restaurantapp.R;

import java.util.List;

public class DishSubtypeAdapter extends RecyclerView.Adapter<DishSubtypeAdapter.ViewHolder>{

	private List<DishSubtype> dishSubtypes;

	public DishSubtypeAdapter(List<DishSubtype> dishSubtypes) {
		this.dishSubtypes = dishSubtypes;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ViewHolder(
				LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dishtype, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		String name= dishSubtypes.get(position).getName();
		String image= dishSubtypes.get(position).getImage();
		Picasso.get().load(image).into(holder.imageView);
		holder.textView.setText(name);
	}

	@Override
	public int getItemCount() {
		return dishSubtypes.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView textView;
		private ImageView imageView;
		ViewHolder(View itemView) {
			super(itemView);
			this.textView = itemView.findViewById(R.id.txt_DishType);
			this.imageView = itemView.findViewById(R.id.img_DishType);
		}
	}

}
