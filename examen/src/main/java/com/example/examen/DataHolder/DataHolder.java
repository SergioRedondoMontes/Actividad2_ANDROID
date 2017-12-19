package com.example.examen.DataHolder;

import android.app.Activity;

import com.example.examen.FireBase.FireBaseAdmin;

/**
 * Created by sergioredondo on 19/12/17.
 */

public class DataHolder {
    static public DataHolder instance = new DataHolder();

    public DataHolder(){

    }

    public FireBaseAdmin fireBaseAdmin;

    public void initFireBaseAdmin(Activity activity){
        fireBaseAdmin = new FireBaseAdmin();
    }
}
