package com.example.examplefan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    Button addFavBtn;
//    public TextView firstName;
//    public TextView lastName;
//    public TextView email;
    private Activity _activity;
    private String first_Name, last_Name, _email;
    private int _id;
    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        System.out.println(bundle);

        addFavBtn = findViewById(R.id.add_fav_button);

        first_Name = bundle.getString("first_name");
        last_Name = bundle.getString("last_name");
        _email = bundle.getString("email");
        TextView name = findViewById(R.id.txt_nama);
        TextView email = findViewById(R.id.txt_email);
        name.setText(first_Name + " " + last_Name);
        email.setText(_email);

        addFavBtn.setOnClickListener(this);



//        //making realm
        Realm.init(DetailActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

    }

    @Override
    public void onClick(View view) {



        if (view == addFavBtn) {
            Model model = new Model(_id, first_Name, last_Name, _email);

            realmHelper = new RealmHelper(realm);
            realmHelper.save(model);
            Toast.makeText(DetailActivity.this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show();


        }
    }
}