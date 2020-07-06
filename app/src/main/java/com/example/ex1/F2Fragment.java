package com.example.ex1;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class F2Fragment extends Fragment {
    TextView txtCode,txtNom,txtPrix,txtCat;
    ImageView imageView;
    public F2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_f2, container, false);
         txtCode=v.findViewById(R.id.txtCode);
         txtNom=v.findViewById(R.id.txtNom);
         txtPrix=v.findViewById(R.id.txtPrix);
         txtCat=v.findViewById(R.id.txtCat);
         imageView=v.findViewById(R.id.imageView);
         return v;
    }

    public void Afficher(Produit p, Bitmap bm){
        if(p==null){
            txtCode.setText("");
            txtNom.setText("");
            txtCat.setText("");
            txtPrix.setText("");
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }
else {
        txtCode.setText(p.getCode());
        txtNom.setText(p.getNom());
        txtCat.setText(p.getCat());
        txtPrix.setText(p.getPrix().toString());
        imageView.setImageBitmap(bm);
}


    }
}
