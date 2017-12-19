package com.example.examen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatosFragment extends Fragment {

    public TextView txtNombre, txtApellido, txtEmail;
    public Button btnBack;
    public DatosFragmentEvents events;
    public DatosFragmentListener listener;

    public void setListener(DatosFragmentListener listener) {
        this.listener = listener;
    }

    public DatosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_datos, container, false);

        events=new DatosFragmentEvents(this);

        btnBack = v.findViewById(R.id.btnVolver);
        btnBack.setOnClickListener(events);
        txtNombre = v.findViewById(R.id.txtNombreD);
        txtApellido = v.findViewById(R.id.txtApellidoD);
        txtEmail = v.findViewById(R.id.txtEmailD);
        return v;
    }

}

class DatosFragmentEvents implements View.OnClickListener{

DatosFragment datosFragment;

    public DatosFragmentEvents(DatosFragment datosFragment){
        this.datosFragment=datosFragment;
    }

    @Override
    public void onClick(View view) {
        System.out.println("-----------------------------------ONCLICK");
        if(view.getId() == R.id.btnVolver) {
            System.out.println("-----------------------------------btnBACK "+this.datosFragment.listener);
            if (this.datosFragment.listener != null)
                System.out.println("-----------------------------------btnBACK not null");
            this.datosFragment.listener.OnBackClicked();
        }
    }
}
