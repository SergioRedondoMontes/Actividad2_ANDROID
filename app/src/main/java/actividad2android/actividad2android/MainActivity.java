package actividad2android.actividad2android;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.fragments.LoginFragment;
import com.example.milib.fragments.LoginFragmentListener;
import com.example.milib.fragments.RegisterFragment;
import com.example.milib.fragments.RegisterFragmentListener;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    public LoginFragment loginFragment;
    public RegisterFragment registerFragment;
//    public InicioFragment inicioFragment;
    MainActivityEvents mainActivityEvents;
    //FireBaseAdmin fireBaseAdmin;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityEvents=new MainActivityEvents(this);

        loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        loginFragment.setListener(mainActivityEvents);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        registerFragment.setListener(mainActivityEvents);
      //  inicioFragment = (InicioFragment)getSupportFragmentManager().findFragmentById(R.id.fragment3);

        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(registerFragment);
       // transition.hide(inicioFragment);
        transition.show(loginFragment);
        transition.commit();

        DataHolder.instance.fireBaseAdmin=new FireBaseAdmin();
        DataHolder.instance.fireBaseAdmin.setListener(mainActivityEvents);

        //descomentar para entrar automaticamente
       // DataHolder.instance.fireBaseAdmin.login("a@a.com","12345678",this);
    }
}
class MainActivityEvents implements LoginFragmentListener,RegisterFragmentListener,FireBaseAdmin.FireBaseAdminListener{
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void OnRegisteredClicked() {
        FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transition.show(this.mainActivity.registerFragment);
       // transition.hide(this.mainActivity.inicioFragment);
        transition.hide(this.mainActivity.loginFragment);
        transition.commit();
    }

    @Override
    public void OnLoginClicked() {
        if (mainActivity.loginFragment.txtEmail.getText().toString()!=null && mainActivity.loginFragment.txtPass.getText().toString()!=null)
        DataHolder.instance.fireBaseAdmin.login(mainActivity.loginFragment.txtEmail.getText().toString(),
                mainActivity.loginFragment.txtPass.getText().toString(),mainActivity);

    }

    @Override
    public void OnBackClicked() {
        FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(this.mainActivity.registerFragment);
       // transition.hide(this.mainActivity.inicioFragment);
        transition.show(this.mainActivity.loginFragment);
        transition.commit();
    }

    @Override
    public void OnSaveClicked() {
        System.out.println("-------------Intentando REGISTRAR");
        if (mainActivity.registerFragment.txtEmail.getText().toString()!=null &&  mainActivity.registerFragment.txtPass.getText().toString()!=null)
        DataHolder.instance.fireBaseAdmin.loginWithEmailPass(mainActivity.registerFragment.txtEmail.getText().toString(),
                mainActivity.registerFragment.txtPass.getText().toString(),mainActivity);
    }

    @Override
    public void fireBaseAdminUserLogin(boolean blconnected) {
        System.out.println("-----------------------------------"+ blconnected);
        if (blconnected){
            /*creo que puedo hacerlo de otra manera
            Log.v("dataholder",DataHolder.instance.emailReg);
            Log.v("dataholder",DataHolder.instance.fireBaseAdmin.currentUser.getEmail());
            if (DataHolder.instance.fireBaseAdmin.currentUser.getEmail().equals(DataHolder.instance.emailReg)){
            DataHolder.instance.fireBaseAdmin.writeNewUser(DataHolder.instance.fireBaseAdmin.currentUser.getUid(),
                    DataHolder.instance.nombreReg,
                    DataHolder.instance.apellidoReg,
                    DataHolder.instance.emailReg);
            }*/
            Intent intent = new Intent(mainActivity,GeneralActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();

           /* DataHolder.instance.fireBaseAdmin.descargarObservarRamaBBDD("user");
            FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
            transition.hide(this.mainActivity.registerFragment);
            transition.hide(this.mainActivity.loginFragment);
            transition.show(this.mainActivity.inicioFragment);
            transition.commit();*/
        }
    }

    @Override
    public void fireBaseAdminUserRegister(boolean blconnected) {
        System.out.println("-----------------------------------"+ blconnected);
        if (blconnected) {
            //insertar usuario en BBDD
            DataHolder.instance.fireBaseAdmin.writeNewUser(DataHolder.instance.fireBaseAdmin.currentUser.getUid(),
                    mainActivity.registerFragment.txtNombre.getText().toString(),
                    mainActivity.registerFragment.txtApellido.getText().toString(),
                    mainActivity.registerFragment.txtEmail.getText().toString());
            /*
            DataHolder.instance.nombreReg = mainActivity.registerFragment.txtNombre.getText().toString();
            DataHolder.instance.apellidoReg = mainActivity.registerFragment.txtApellido.getText().toString();
            DataHolder.instance.emailReg = mainActivity.registerFragment.txtEmail.getText().toString();
            Log.v("dataholder",DataHolder.instance.nombreReg);
            Log.v("dataholder",DataHolder.instance.apellidoReg);
            Log.v("dataholder",DataHolder.instance.emailReg);
*/
            FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
            transition.hide(this.mainActivity.registerFragment);
            transition.show(this.mainActivity.loginFragment);
           // transition.show(this.mainActivity.inicioFragment);
            transition.commit();
        }
    }

    @Override
    public void fireBaseDataRecive(String rama, DataSnapshot dataSnapshot) {
            if (rama.equals("users/sergio")){
                User user=dataSnapshot.getValue(User.class);
            }else if(rama.equals("users")){
                Users users = dataSnapshot.getValue(Users.class);
            }
    }

}
