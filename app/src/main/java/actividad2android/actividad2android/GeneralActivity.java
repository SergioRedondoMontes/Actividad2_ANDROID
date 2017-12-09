package actividad2android.actividad2android;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.milib.fragments.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

public class GeneralActivity extends AppCompatActivity {
    public ListaFragment listaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        DataHolder.instance.fireBaseAdmin.initData();

        listaFragment = (ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentlista);
        /*FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(listaFragment);
        transition.commit();*/
        GeneralActivityEventes events = new GeneralActivityEventes(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);
        DataHolder.instance.fireBaseAdmin.descargarObservarRamaBBDD("user");

/*
        ArrayList<String> mDatos = new ArrayList<>();
        mDatos.add("bocadillo");
        mDatos.add("sandwich");
        mDatos.add("hamburguesa");

        ListaAdapter listaAdapter = new ListaAdapter(mDatos);

        listaFragment.recyclerView.setAdapter(listaAdapter);
*/
    }

}

class GeneralActivityEventes implements FireBaseAdmin.FireBaseAdminListener{

    GeneralActivity generalActivity;


    public GeneralActivityEventes(GeneralActivity generalActivity){
    this.generalActivity=generalActivity;
    }

    @Override
    public void fireBaseDataRecive(String rama, DataSnapshot dataSnapshot) {
        Log.v("general",rama+"......"+dataSnapshot);
        /*
        User user = dataSnapshot.getValue(User.class);
        Log.v("general","......"+user.nombre);
        Log.v("general","......"+user.apellido);
        Log.v("general","......"+user.correo);
        user.correo,user
       */
        GenericTypeIndicator<Map<String,User>> indicator = new GenericTypeIndicator<Map<String,User>>(){};
        Map<String,User> hashUsers= dataSnapshot.getValue(indicator);
        Log.v("general","contiene"+hashUsers);
        ListaAdapter listaAdapter = new ListaAdapter(new ArrayList<User>(hashUsers.values()));
        generalActivity.listaFragment.recyclerView.setAdapter(listaAdapter);
    }



    @Override
    public void fireBaseAdminUserRegister(boolean blconnected) {

    }

    @Override
    public void fireBaseAdminUserLogin(boolean blconnected) {

    }


}
