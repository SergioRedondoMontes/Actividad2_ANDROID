package com.example.milib.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class LoginFragment extends Fragment {

    Button btnRegister,btnLogin;
    TextView lblEmail,lblPass;
    public EditText txtEmail,txtPass;
    LoginFragmentEvents events;
    public LoginFragmentListener listener;

    public void setListener(LoginFragmentListener listener) {
        this.listener = listener;
    }

    public LoginFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        events=new LoginFragmentEvents(this);

        lblEmail = v.findViewById(R.id.lblEmailL);
        lblEmail.setText(R.string.lblEmail);
        lblPass = v.findViewById(R.id.lblPassL);
        lblPass.setText(R.string.lblPass);

        txtEmail = v.findViewById(R.id.txtEmailL);
        txtPass = v.findViewById(R.id.txtPassL);

        btnRegister = v.findViewById(R.id.btnRegister);
        btnRegister.setText(R.string.btnRegister);
        btnRegister.setOnClickListener(events);
        btnLogin = v.findViewById(R.id.btnLogin);
        btnLogin.setText(R.string.btnLogin);
        btnLogin.setOnClickListener(events);

        return v;
    }

}

class LoginFragmentEvents implements View.OnClickListener{

    LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment loginFragment){
        this.loginFragment=loginFragment;
    }

    @Override
    public void onClick(View view) {
        System.out.println("-----------------------------------ONCLICK");
        if(view.getId() == R.id.btnRegister){
            System.out.println("-----------------------------------btnrEGISTER");
            if(this.loginFragment.listener!=null)
                System.out.println("-----------------------------------btnrEGISTER2 not null");
                this.loginFragment.listener.OnRegisteredClicked();
        }
        else if(view.getId() == R.id.btnLogin) {
            System.out.println("-----------------------------------btnlogin");
            //if (this.loginFragment.txtEmail.getText().toString().equals(DataHolder.instance.email) &&
              //      this.loginFragment.txtPass.getText().toString().equals(DataHolder.instance.pass)) {
            if(this.loginFragment.listener!=null)
                System.out.println("-----------------------------------btnlogin2 NOT NULL");
                this.loginFragment.listener.OnLoginClicked();
            //}
        }
    }
}








