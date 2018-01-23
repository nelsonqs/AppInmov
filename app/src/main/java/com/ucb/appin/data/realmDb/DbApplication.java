package com.ucb.appin.data.realmDb;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Juan choque on 10/14/2017.
 */

public class DbApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("appin.realm")
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
