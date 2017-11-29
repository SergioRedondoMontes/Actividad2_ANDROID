package com.example.milib.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.milib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    Button btnSave,btnBack;
    TextView lblEmail,lblPass;
    public EditText txtEmail,txtPass;
    RegisterFragmentEvents events;
    public RegisterFragmentListener listener;

    public void setListener(RegisterFragmentListener listener) {
        this.listener = listener;
    }



    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_register, container, false);

        events=new RegisterFragmentEvents(this);

        btnSave = v.findViewById(R.id.btnSave);
        btnSave.setText(R.string.btnSave);
        btnSave.setOnClickListener(events);
        btnBack = v.findViewById(R.id.btnBack);
        btnBack.setText(R.string.btnBack);
        btnBack.setOnClickListener(events);

        lblEmail = v.findViewById(R.id.lblEmail);
        lblEmail.setText(R.string.lblEmail);
        lblPass = v.findViewById(R.id.lblPass);
        lblPass.setText(R.string.lblPass);

        txtEmail = v.findViewById(R.id.txtEmail);
        txtPass = v.findViewById(R.id.txtPass);

        return v;
    }

}

class RegisterFragmentEvents implements View.OnClickListener{

    RegisterFragment registerFragment;

    public RegisterFragmentEvents(RegisterFragment registerFragment){this.registerFragment=registerFragment;}

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnBack){
            if(this.registerFragment.listener!=null) {
                this.registerFragment.listener.OnBackClicked();
            }
        }
        else if(view.getId() == R.id.btnSave){
            if(this.registerFragment.listener!=null){
                    this.registerFragment.listener.OnSaveClicked();
            }
        }

    }
}
