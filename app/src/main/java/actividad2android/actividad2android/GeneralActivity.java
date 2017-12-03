package actividad2android.actividad2android;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.milib.fragments.InicioFragment;

public class GeneralActivity extends AppCompatActivity {
    public InicioFragment inicioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        inicioFragment = (InicioFragment)getSupportFragmentManager().findFragmentById(R.id.fragment3);
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.show(inicioFragment);
        transition.commit();
    }

}
