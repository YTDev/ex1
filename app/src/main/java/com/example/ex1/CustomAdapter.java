package com.example.ex1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Produit> AL=new ArrayList<>();
    FragmentManager FM;
    Bitmap b;
    InputStream inputStream= null;
    public CustomAdapter(Context context, ArrayList<Produit> AL,FragmentManager FM) {
        this.context = context;
        this.AL = AL;
        this.FM=FM;
    }

    @Override
    public int getCount() {
        return AL.size();
    }

    @Override
    public Object getItem(int position) {
        return AL.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.layout_detail_lvpro,parent,false);
        ImageView img=convertView.findViewById(R.id.img);
        TextView txt=convertView.findViewById(R.id.txt);
        ImageButton imgBInfo=convertView.findViewById(R.id.imgBInfo);
        ImageView imgAdd=convertView.findViewById(R.id.imgAdd);


        txt.setText(AL.get(position).getNom());


        try {
            inputStream = context.getResources().getAssets().open(AL.get(position).getPhoto());
            InputStream is=new BufferedInputStream(inputStream);

            b= BitmapFactory.decodeStream(is);// b contient la derniere valeur de position
            img.setImageBitmap(b);
        } catch (IOException e) {
        e.printStackTrace();
    }
//        Toast.makeText(context,position+"\n"+AL.get(position).getPhoto(),Toast.LENGTH_SHORT).show();
        imgBInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                F2Fragment F2=(F2Fragment) FM.findFragmentById(R.id.frag2);

                try {
                    inputStream = context.getResources().getAssets().open(AL.get(position).getPhoto());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream is=new BufferedInputStream(inputStream);
                b= BitmapFactory.decodeStream(is);// b contient la derniere valeur de position

//                F2.Afficher(AL.get(position),b);
                F2.Afficher((Produit) getItem(position),b);
                Toast.makeText(context,"Position :"+position+"\nPhoto :"+ AL.get(position).getPhoto(),Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"Infos "+position,Toast.LENGTH_SHORT).show();
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produit pAj=(Produit) getItem(position);
                for(int i=0;i<Produit.panier.size();i++){
                    if(Produit.panier.get(i).getCode().equals(pAj.getCode())){
                        Toast.makeText(context,"Produit existe déja",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                Produit.panier.add((Produit) getItem(position));
                Toast.makeText(context,"Le produit "+AL.get(position).getNom().toString()+" est ajouté au panier",Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"Add "+String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
