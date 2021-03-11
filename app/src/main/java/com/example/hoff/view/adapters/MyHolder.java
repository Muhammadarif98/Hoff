package com.example.hoff.view.adapters;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoff.R;
import com.example.hoff.model.data.Item;
import com.example.hoff.view.adapters.MyAdapter;
import com.squareup.picasso.Picasso;


public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    CheckBox mFavorite;
    TextView mNumberOfReviews,mBrand,mStatus,mPrice,mPriceOld;
    RatingBar mRatingBar;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_2);
        this.mFavorite = itemView.findViewById(R.id.favorite);
        this.mNumberOfReviews = itemView.findViewById(R.id.numberOfReviews);
        this.mBrand = itemView.findViewById(R.id.brand);
        this.mStatus = itemView.findViewById(R.id.statusText);
        this.mPrice = itemView.findViewById(R.id.price);
        this.mPriceOld = itemView.findViewById(R.id.price_old);
        this.mRatingBar = itemView.findViewById(R.id.ratingBar);



    }

    public void bind(Item model, MyAdapter adapter){
        mNumberOfReviews.setText(String.valueOf(model.getNumberOfReviews()));
        mBrand.setText(model.getName());
        mStatus.setText(model.getStatusText());
        Picasso.get().load(model.getImage()).fit().into(mImageView);
        mPrice.setText(mPrice.getContext().getString(R.string.ruble, String.valueOf(model.getPrices().getNew())));
        mPriceOld.setText(mPriceOld.getContext().getString(R.string.ruble, String.valueOf(model.getPrices().getOld())));
        mRatingBar.setRating(model.getRating());
        String id = model.getId();

        mFavorite.setChecked(adapter.getValue(model.getId()));
        mFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            adapter.updateValue(id, isChecked);
        });

    }
}
