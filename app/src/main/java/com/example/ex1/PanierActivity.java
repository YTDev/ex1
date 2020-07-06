package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class PanierActivity extends AppCompatActivity {
ArrayList<Produit>lstPro=new ArrayList<>();
TextView txtPanier;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        txtPanier=findViewById(R.id.txtPanier);
        Intent it=getIntent();
        lstPro=(ArrayList<Produit>) it.getSerializableExtra("monPanier");
        txtPanier.setText(lstPro.size()+"");

        vp=findViewById(R.id.vp);
        PagerAdapter pa=new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pa);

    }
}
