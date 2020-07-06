package com.example.ex1;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPagerFragment extends Fragment {
    TextView txtCode,txtNom,txtPrix,txtCat;
    ImageView imageView;

    public MyPagerFragment() {
        // Required empty public constructor
    }



    public static Bitmap getImageFromAssetsFile(Context mContext, String fileName) {
        Bitmap image = null;
        AssetManager am = mContext.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_my_pager, container, false);
        txtCode=v.findViewById(R.id.txtCode);
        txtNom=v.findViewById(R.id.txtNom);
        txtPrix=v.findViewById(R.id.txtPrix);
        txtCat=v.findViewById(R.id.txtCat);
        imageView=v.findViewById(R.id.imageView);

        String code=getArguments().getString("code");
        String nom=getArguments().getString("nom");
        Double prix=getArguments().getDouble("prix");
        String cat=getArguments().getString("cat");
        String img=getArguments().getString("img");

        txtCode.setText(code);
        txtNom.setText(nom);
        txtPrix.setText(prix+"");
        txtCat.setText(cat);


        AssetManager assetManager=getActivity().getAssets();
        try {
            InputStream inputStream=assetManager.open(img);
            Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





//        try {
//
//            inputStream=getActivity().getResources().getAssets().open(img);
//        } catch (IOException e) {
//        e.printStackTrace();
//    }
//    InputStream is=new BufferedInputStream(inputStream);
//    b= BitmapFactory.decodeStream(is);
//        imageView.setImageBitmap(b);
//        Toast.makeText(getActivity(),nom,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(),img,Toast.LENGTH_SHORT).show();



        //imageView.setImageBitmap(getImageFromAssetsFile(getActivity(), img));
        //imageView.setImageResource(img);
        return v;
    }
}
