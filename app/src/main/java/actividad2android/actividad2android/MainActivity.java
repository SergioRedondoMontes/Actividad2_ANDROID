package actividad2android.actividad2android;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.milib.fragments.LoginFragment;
import com.example.milib.fragments.LoginFragmentListener;
import com.example.milib.fragments.RegisterFragment;
import com.example.milib.fragments.RegisterFragmentListener;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public LoginFragment loginFragment;
    public RegisterFragment registerFragment;
    MainActivityEvents mainActivityEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        mainActivityEvents=new MainActivityEvents(this);
        loginFragment.setListener(mainActivityEvents);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        registerFragment.setListener(mainActivityEvents);


        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(registerFragment);
        transition.show(loginFragment);
        transition.commit();
    }
}
class MainActivityEvents implements LoginFragmentListener,RegisterFragmentListener{
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public void OnRegisteredClicked() {
        FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transition.show(this.mainActivity.registerFragment);
        transition.hide(this.mainActivity.loginFragment);
        transition.commit();

    }

    @Override
    public void OnLoginClicked() {

    }

    @Override
    public void OnBackClicked() {
        Log.d(TAG, "OnBackClicked: ...............----------------------...................");
        FragmentTransaction transition = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(this.mainActivity.registerFragment);
        transition.show(this.mainActivity.loginFragment);
        transition.commit();
    }

    @Override
    public void OnSaveClicked() {

    }
}
