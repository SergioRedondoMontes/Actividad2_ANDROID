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
    public String emailReg;
    public String nombreReg;
    public String apellidoReg;

    public FireBaseAdmin fireBaseAdmin;

    public void initFireBaseAdmin(Activity activity){
        fireBaseAdmin = new FireBaseAdmin();
    }

}
