package com.example.examen;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.examen.DataHolder.DataHolder;
import com.example.examen.FireBase.FireBaseAdmin;
import com.example.milib.fragments.LoginFragment;
import com.example.milib.fragments.LoginFragmentListener;
import com.example.milib.fragments.RegisterFragment;
import com.example.milib.fragments.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    public LoginFragment loginFragment;
    public RegisterFragment registerFragment;
    MainActivityEvents mainActivityEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityEvents=new MainActivityEvents(this);

        //añadimnos los fragment que tenemos en milib
        loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        loginFragment.setListener(mainActivityEvents);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        registerFragment.setListener(mainActivityEvents);

        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(registerFragment);
        transition.show(loginFragment);
        transition.commit();

        DataHolder.instance.fireBaseAdmin=new FireBaseAdmin();
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        //descomentar para entrar automaticamente
        DataHolder.instance.fireBaseAdmin.login("a@a.com","12345678",this);
    }
}
class MainActivityEvents implements LoginFragmentListener,RegisterFragmentListener,FireBaseAdmin.FireBaseAdminListener {
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    /*
    @OnRegisteredClicked metodo que detecta la puñsacion sobre el boton registrar del fragment login
     */
    @Override
    public void OnRegisteredClicked() {
        FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transition.show(this.mainActivity.registerFragment);
        transition.hide(this.mainActivity.loginFragment);
        transition.commit();
    }

/*
@OnLoginClicked metodo que comprueba si hay datos en los campos de login y de ser asi llama a la funcion de
la clase FireBaseAdmin que hace el login
 */
    @Override
    public void OnLoginClicked() {
        Log.v("OnLoginClicked",mainActivity.loginFragment.txtEmail.getText().toString());
        Log.v("OnLoginClicked",mainActivity.loginFragment.txtPass.getText().toString());

        if (mainActivity.loginFragment.txtEmail.getText().toString()!=null && mainActivity.loginFragment.txtPass.getText().toString()!=null)
            DataHolder.instance.fireBaseAdmin.login(mainActivity.loginFragment.txtEmail.getText().toString(),
                    mainActivity.loginFragment.txtPass.getText().toString(),mainActivity);

    }

    /*
    @OnBackClicked metodo que detecta la pulsacion sobre el boton volver del fragment register
     */
    @Override
    public void OnBackClicked() {
        FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(this.mainActivity.registerFragment);
        transition.show(this.mainActivity.loginFragment);
        transition.commit();
    }
/*
@OnSaveClicked metodo que comprueba si hay datos en los campos de registro y de ser asi llama a la funcion de
la clase FireBaseAdmin que hace el registro
 */
    @Override
    public void OnSaveClicked() {
        if (mainActivity.registerFragment.txtEmail.getText().toString()!=null &&  mainActivity.registerFragment.txtPass.getText().toString()!=null)
            DataHolder.instance.fireBaseAdmin.register(mainActivity.registerFragment.txtEmail.getText().toString(),
                    mainActivity.registerFragment.txtPass.getText().toString(),mainActivity);
    }

    /*
    @fireBaseAdminUserRegiste si el metodo de FireBaseAdmin @FireBaseAdmin.register ha funcionado correctamente inserta el nuevo usuario
      y hace la transicion
     */
    @Override
    public void fireBaseAdminUserRegister(boolean blconnected) {
        Log.v("fireBaseRegister",""+blconnected);
        if (blconnected) {

            //insertar usuario en BBDD
            DataHolder.instance.fireBaseAdmin.writeNewUser(DataHolder.instance.fireBaseAdmin.currentUser.getUid(),
                    mainActivity.registerFragment.txtNombre.getText().toString(),
                    mainActivity.registerFragment.txtApellido.getText().toString(),
                    mainActivity.registerFragment.txtEmail.getText().toString());


            //transicion
            FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
            transition.hide(this.mainActivity.registerFragment);
            transition.show(this.mainActivity.loginFragment);
            transition.commit();
        }
    }

    /*
    @fireBaseAdminUserLogin  si el metodo de FireBaseAdmin @FireBaseAdmin.login ha funcionado correctamente hace la transicion
     */
    @Override
    public void fireBaseAdminUserLogin(boolean blconnected) {
        Log.v("fireBaseLogin",""+blconnected);
        if (blconnected){
            Intent intent = new Intent(mainActivity,GeneralActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();

        }
    }

    @Override
    public void fireBaseDataRecive(String rama, DataSnapshot dataSnapshot) {

    }
}
