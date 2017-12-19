package actividad2android.actividad2android;

import android.support.v7.widget.RecyclerView;

/**
 * Created by sergioredondo on 11/12/17.
 */

public interface ListaAdapterListener {
    public void ListaAdapterCellClick(ListaViewHolder cell,int posicion);
}
