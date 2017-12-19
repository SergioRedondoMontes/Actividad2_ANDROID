package com.example.examen.FireBase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sergioredondo on 19/12/17.
 */

public class FireBaseAdmin {
    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FireBaseAdminListener listener;

    public void setListener(FireBaseAdminListener listener) {
        this.listener = listener;
    }

    public FireBaseAdmin(){
        mAuth=FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public void initData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }


    public void register(final String email, final String password,Activity activity){
        System.out.println(password+"-----------------------------------"+ email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            FireBaseAdmin.this.listener.fireBaseAdminUserRegister(true);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.v("register", "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            FireBaseAdmin.this.listener.fireBaseAdminUserRegister(false);
                        }

                        // ...
                    }
                });
    }


    public void login(final String email, String password, Activity activity) {

        System.out.println(password+"-----------------------------------"+ email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.v("login","BIEEEEN");
                            FirebaseUser user = mAuth.getCurrentUser();
                            FireBaseAdmin.this.listener.fireBaseAdminUserLogin(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.v("login","MAAAAAAAL"+email);
                            FireBaseAdmin.this.listener.fireBaseAdminUserLogin(false);
                        }

                        // ...
                    }
                });

    }


    public void descargarObservarRamaBBDD(final String rama){
        // Read from the database
        DatabaseReference refTemp = myRef.child(rama);
        refTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                listener.fireBaseDataRecive(rama,dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                listener.fireBaseDataRecive(rama,null);
                Log.v("onCancelled",rama+"......");
            }
        });

    }


    public void writeNewUser(String userId, String nombre, String apellido, String email) {
        /*initData();
        User user = new User(nombre, apellido, email);
        Log.v("writeNewUser",user.nombre+" "+user.apellido+" "+user.correo+"---"+userId);
        DatabaseReference refTemp = myRef.child("user");
        refTemp.child(userId).setValue(user);
        */
    }



    public interface FireBaseAdminListener{

        public void fireBaseAdminUserRegister(boolean blconnected);
        public void fireBaseAdminUserLogin(boolean blconnected);
        public void fireBaseDataRecive( String rama,DataSnapshot dataSnapshot);

    }
}

