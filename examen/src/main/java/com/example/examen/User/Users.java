package com.example.examen.User;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by sergioredondo on 19/12/17.
 */

@IgnoreExtraProperties
public class Users {

    public HashMap<String,User> userHashMap;

    public Users(){}

    public Users (HashMap<String, User> userHashMap)  {
        this.userHashMap=userHashMap;
    }
}

