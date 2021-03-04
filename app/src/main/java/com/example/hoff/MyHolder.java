package com.example.hoff;


import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    CheckBox mFavorite;
    TextView mNumberOfReviews,mBrand,mPrice;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_2);
        this.mFavorite = itemView.findViewById(R.id.favorite);
        this.mNumberOfReviews = itemView.findViewById(R.id.numberOfReviews);
        this.mBrand = itemView.findViewById(R.id.brand);
        this.mPrice = itemView.findViewById(R.id.price);


    }

    public void bind(Example model,MyAdapter adapter){
        mNumberOfReviews.setText(model.items.getNumberOfReviews());
        mBrand.setText(model.items.getName());
        Picasso.get().load(model.items.getImage()).fit().into(mImageView);
        mPrice.setText(mPrice.getContext().getString(R.string.ruble, model.items.getPrices()));
        String id = model.items.getId();

        mFavorite.setChecked(adapter.getValue(id));
        mFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            adapter.updateValue(id, isChecked);
        });
        mFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }
}
