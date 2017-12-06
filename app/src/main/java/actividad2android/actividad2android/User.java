package actividad2android.actividad2android;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by sergioredondo on 1/12/17.
 */
@IgnoreExtraProperties
public class User {
    public String nombre;
    public String apellido;
    public String correo;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }
}
