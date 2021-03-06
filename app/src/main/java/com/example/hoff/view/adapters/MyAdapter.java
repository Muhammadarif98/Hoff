package com.example.hoff.view.adapters;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hoff.R;
import com.example.hoff.model.data.Item;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    List<Item> models = new ArrayList<>();
    SharedPreferences mPrefs;
    private boolean isLoadingAdded = false;

    public MyAdapter(SharedPreferences mPrefs){
        this.mPrefs = mPrefs;
    }

    public void addItems(List<Item> models) {
        this.models.addAll(models);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bind(models.get(position), MyAdapter.this);
    }
    public void updateValue(String id, boolean isChecked) {
        mPrefs.edit().putBoolean(id, isChecked).apply();
    }
    public boolean getValue(String id){
        return mPrefs.getBoolean(id,false);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


}
