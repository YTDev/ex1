package com.example.ex1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        MyPagerFragment F=new MyPagerFragment();
        Bundle b=new Bundle();
        b.putString("code",Produit.panier.get(position).getCode());
        b.putString("nom",Produit.panier.get(position).getNom());
        b.putDouble("prix",Produit.panier.get(position).getPrix());
        b.putString("cat",Produit.panier.get(position).getCat());
        b.putString("img",Produit.panier.get(position).getPhoto());
        F.setArguments(b);
        return F;
    }

    @Override
    public int getCount() {
        return Produit.panier.size();
    }
}
