package com.example.milib.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milib.ListaAdapter;
import com.example.milib.R;

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
        recyclerView.findViewById(R.id.listaR);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        ListaAdapter listaAdapter = new ListaAdapter();
        recyclerView.setAdapter(listaAdapter);


        return v;
    }

}
