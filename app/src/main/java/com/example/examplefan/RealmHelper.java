package com.example.examplefan;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final Model model){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(Model.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    model.setId(nextId);
                    Model _model = realm.copyToRealm(model);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }
    //menampilkan data

    public List<Model> getAllContact() {
        RealmResults<Model> results = realm.where(Model.class).findAll();
        return results;
    }


    // menghapus
    public void delete(Integer id){

        realm.executeTransaction(new Realm.Transaction() {
            final Model model = realm.where(Model.class).equalTo("id", id).findFirst();
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm();
            }
        });
    }
}


