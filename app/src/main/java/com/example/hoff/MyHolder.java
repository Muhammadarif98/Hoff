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
    TextView mNumberOfReviews,mBrand,mPrice,mPriceOld;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_2);
        this.mFavorite = itemView.findViewById(R.id.favorite);
        this.mNumberOfReviews = itemView.findViewById(R.id.numberOfReviews);
        this.mBrand = itemView.findViewById(R.id.brand);
        this.mPrice = itemView.findViewById(R.id.price);
        this.mPriceOld = itemView.findViewById(R.id.price_old);


    }

    public void bind(Item model,MyAdapter adapter){
        mNumberOfReviews.setText(String.valueOf(model.getNumberOfReviews()));
        mBrand.setText(model.getName());
        Picasso.get().load(model.getImage()).fit().into(mImageView);
        mPrice.setText(mPrice.getContext().getString(R.string.ruble, String.valueOf(model.getPrices().getNew())));
        mPriceOld.setText(mPriceOld.getContext().getString(R.string.ruble, String.valueOf(model.getPrices().getOld())));
        String id = model.getId();

        mFavorite.setChecked(adapter.getValue(model.getId()));
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
