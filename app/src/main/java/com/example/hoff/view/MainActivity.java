package com.example.hoff.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.hoff.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linearLayout = findViewById(R.id.button);
        linearLayout.setOnClickListener(v -> catalog());
    }

    public void catalog() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
    }

}