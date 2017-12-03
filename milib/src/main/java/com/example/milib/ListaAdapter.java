package com.example.milib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sergioredondo on 3/12/17.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaViewHolder> {

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_layout,null);
        ListaViewHolder listaViewHolder = new ListaViewHolder(view);

        return listaViewHolder;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        holder.textoC.setText("POSICION" + position);

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}


class ListaViewHolder extends RecyclerView.ViewHolder{

    public TextView textoC;


    public ListaViewHolder(View itemView) {
        super(itemView);
    }
}
