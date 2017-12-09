package com.example.milib.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milib.R;

import static java.security.AccessController.getContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {


    public RecyclerView recyclerView;


    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista, container, false);
        recyclerView = v.findViewById(R.id.listaR);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
/*
        ArrayList<String> mDatos = new ArrayList<>();
        mDatos.add("bocadillo");
        mDatos.add("sandwich");
        mDatos.add("hamburguesa");

        ListaAdapter listaAdapter = new ListaAdapter(mDatos);
        recyclerView.setAdapter(listaAdapter);

*/

        return v;
    }

}
