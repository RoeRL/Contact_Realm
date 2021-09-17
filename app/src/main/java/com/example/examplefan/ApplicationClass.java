package com.example.examplefan;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("contact.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);

    }
}
