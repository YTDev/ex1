package com.example.ex1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class F1Fragment extends Fragment {
    ListView lv;

    public F1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_f1, container, false);
        lv=v.findViewById(R.id.lv);
        return v;
    }

    void Afficher(ArrayList<Produit> lstPro){
        CustomAdapter customAdapter=new CustomAdapter(getContext(),lstPro,getFragmentManager());
        lv.setAdapter(customAdapter);
    }
}
