package com.example.examen;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.examen.DataHolder.DataHolder;
import com.example.examen.FireBase.FireBaseAdmin;
import com.example.examen.User.User;
import com.example.milib.fragments.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

public class GeneralActivity extends AppCompatActivity {
    public ListaFragment listaFragment;
    public DatosFragment datosFragment;
    public Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataHolder.instance.fireBaseAdmin.initData();

        setContentView(R.layout.activity_general);

        btnLogOut = this.findViewById(R.id.btnLogOut);
        btnLogOut.setText(R.string.logOut);

        //muestra el fragment que contiene la lista
        listaFragment = (ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentlista);
        datosFragment = (DatosFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentDatos);
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(listaFragment);
        transition.hide(datosFragment);
        transition.commit();


        GeneralActivityEventes events = new GeneralActivityEventes(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);

        //decimos dese que rama vamos a observar
        DataHolder.instance.fireBaseAdmin.descargarObservarRamaBBDD("user");

    }

}

class GeneralActivityEventes implements FireBaseAdmin.FireBaseAdminListener,ListaAdapterListener {

    GeneralActivity generalActivity;
    ListaAdapter listaAdapter;


    public GeneralActivityEventes(GeneralActivity generalActivity){
        this.generalActivity=generalActivity;
    }


    @Override
    public void fireBaseAdminUserRegister(boolean blconnected) {

    }

    @Override
    public void fireBaseAdminUserLogin(boolean blconnected) {

    }

    /*
    @fireBaseDataRecive
     */
    @Override
    public void fireBaseDataRecive(String rama, DataSnapshot dataSnapshot) {
        Log.v("general",rama+"......"+dataSnapshot);

        GenericTypeIndicator<Map<String,User>> indicator = new GenericTypeIndicator<Map<String,User>>(){};
        Map<String,User> hashUsers= dataSnapshot.getValue(indicator);
        Log.v("general","contiene"+hashUsers);
        listaAdapter = new ListaAdapter(new ArrayList<User>(hashUsers.values()));
        listaAdapter.setListener(this);
        generalActivity.listaFragment.recyclerView.setAdapter(listaAdapter);
    }

    @Override
    public void ListaAdapterCellClick(ListaViewHolder cell, int posicion) {
        Log.v("ListaAdapterCellClick",posicion+"");
        generalActivity.datosFragment.txtNombre.setText(cell.textoNombre.getText().toString());
        generalActivity.datosFragment.txtApellido.setText(cell.textoApellido.getText().toString());
        generalActivity.datosFragment.txtEmail.setText(cell.textoC.getText().toString());

        FragmentTransaction transition = generalActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(generalActivity.listaFragment);
        transition.show(generalActivity.datosFragment);
        transition.commit();

    }
}
