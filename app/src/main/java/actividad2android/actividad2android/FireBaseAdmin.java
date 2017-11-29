package actividad2android.actividad2android;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sergioredondo on 24/11/17.
 */

public class FireBaseAdmin {
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Activity activity;
    FireBaseAdminListener listener;

    public void setListener(FireBaseAdminListener listener) {
        this.listener = listener;
    }

    public FireBaseAdmin(Activity activity){
        mAuth=FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        this.activity=activity;
    }


    public void loginWithEmailPass(final String email, final String password){
        System.out.println(password+"-----------------------------------"+ email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(FireBaseAdmin.this.activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            FireBaseAdmin.this.listener.fireBaseAdminUserConnected(true);

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            FireBaseAdmin.this.listener.fireBaseAdminUserConnected(false);
                        }

                        // ...
                    }
                });
    }


    public void signIn(String email, String password) {

        System.out.println(password+"-----------------------------------"+ email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            FireBaseAdmin.this.listener.fireBaseAdminUserRegister(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            FireBaseAdmin.this.listener.fireBaseAdminUserRegister(false);
                        }

                        // ...
                    }
                });

    }


    public interface FireBaseAdminListener{

        public void fireBaseAdminUserConnected(boolean blconnected);
        public void fireBaseAdminUserRegister(boolean blconnected);

    }
}
