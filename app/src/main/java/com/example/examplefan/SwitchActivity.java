package com.example.examplefan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SwitchActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFav, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        getSupportActionBar().hide();

        btnFav = findViewById(R.id.favorites_button);
        btnList = findViewById(R.id.full_button);


        btnFav.setOnClickListener(this);
        btnList.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == btnFav){
            startActivity(new Intent(SwitchActivity.this, ListFavoritesActivity.class));
        }
        if (view == btnList){
            startActivity(new Intent(SwitchActivity.this, ListActivity.class));
        }
    }
}