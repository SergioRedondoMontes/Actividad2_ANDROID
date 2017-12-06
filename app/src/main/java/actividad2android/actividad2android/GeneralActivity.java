package actividad2android.actividad2android;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.fragments.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

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
    }



    @Override
    public void fireBaseAdminUserRegister(boolean blconnected) {

    }

    @Override
    public void fireBaseAdminUserLogin(boolean blconnected) {

    }


}
