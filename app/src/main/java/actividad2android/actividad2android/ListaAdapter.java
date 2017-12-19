package actividad2android.actividad2android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sergioredondo on 3/12/17.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaViewHolder> {

    private ArrayList<User> listaC;
    public ListaAdapterListener listener;

    public void setListener(ListaAdapterListener listener){
        this.listener=listener;
    }

    public ListaAdapter(ArrayList<User> listaC){
        this.listaC=listaC;
    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_layout,null);
        ListaViewHolder listaViewHolder = new ListaViewHolder(view);

        return listaViewHolder;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        holder.textoC.setText(listaC.get(position).correo);
        holder.textoNombre.setText(listaC.get(position).nombre);
        holder.textoApellido.setText(listaC.get(position).apellido);
        holder.setListaAdapterListener(listener);
        holder.setPosicion(position);
    }

    @Override
    public int getItemCount() {
        return listaC.size();
    }
}


class ListaViewHolder extends RecyclerView.ViewHolder {

    public TextView textoC;
    public TextView textoNombre;
    public TextView textoApellido;
    ListaViewHolderEvents events;
    public ListaAdapterListener listaAdapterListener;
    public int posicion=-1;


    public ListaViewHolder(View itemView) {
        super(itemView);
        textoC=itemView.findViewById(R.id.textoC);
        textoNombre=itemView.findViewById(R.id.textoNombre);
        textoApellido=itemView.findViewById(R.id.textoApellido);
        events = new ListaViewHolderEvents(this);
        itemView.setOnClickListener(events);
    }

    public void setListaAdapterListener(ListaAdapterListener listaAdapterListener) {
        this.listaAdapterListener = listaAdapterListener;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}

class ListaViewHolderEvents implements View.OnClickListener{
    ListaViewHolder viewHolder;

    public ListaViewHolderEvents(ListaViewHolder listaViewHolder){
        this.viewHolder=listaViewHolder;
    }

    @Override
    public void onClick(View view) {
        viewHolder.listaAdapterListener.ListaAdapterCellClick(viewHolder,viewHolder.posicion);

    }
}
