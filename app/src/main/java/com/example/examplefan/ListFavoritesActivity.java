package com.example.examplefan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListFavoritesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    FavAdapter adapter2;
    List<Model> models;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorites);


        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerViewFavorites);

        recyclerView.setLayoutManager(new LinearLayoutManager(ListFavoritesActivity.this));


        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        models = new ArrayList<>();

        models = realmHelper.getAllContact();

        Log.d("TAG", "dataArray: " + models.size());

        adapter2 = new FavAdapter(models, this);
        recyclerView.setAdapter(adapter2);

    }
}