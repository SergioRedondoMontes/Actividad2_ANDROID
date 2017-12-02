package actividad2android.actividad2android;

import android.app.Activity;
import android.app.FragmentTransaction;

/**
 * Created by sergioredondo on 21/11/17.
 */

public class DataHolder {
    static public DataHolder instance = new DataHolder();

    public DataHolder(){

    }

    static public String email = "sergio@gmail.com";
    static public String pass = "1234";

    public FireBaseAdmin fireBaseAdmin;

    public void initFireBaseAdmin(Activity activity){
        fireBaseAdmin = new FireBaseAdmin();
    }

}
