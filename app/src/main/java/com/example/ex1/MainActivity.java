package com.example.ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Produit> lst=new ArrayList<Produit>();
    DrawerLayout drawer;
    NavigationView navig;
    ActionBarDrawerToggle toggle;
    Menu menu;
    JSONArray T=null;
    JSONArray TPro=null;
    InputStream inputStream= null;
    InputStreamReader inputStreamReader=null;
    BufferedReader bufferedReader=null;
    StringBuilder stringBuilder=null;
    String ligne=null;
    String data=null;
    TextView txt;
    String str;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=findViewById(R.id.titre);
        str=txt.getText().toString();

        drawer=findViewById(R.id.drawer);
        navig=findViewById(R.id.navig);
        menu=navig.getMenu();//On va appliquer le Add sur cette element <=>arraylist
        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //partie JSON

        try {
            inputStream = getResources().getAssets().open("categories.json");
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            stringBuilder=new StringBuilder();

            for (ligne=bufferedReader.readLine();ligne!=null;ligne=bufferedReader.readLine()){
                stringBuilder.append(ligne);
            }
            inputStream.close();
            data=stringBuilder.toString();


            T=new JSONArray(data);
            if (T!=null){

                for(int i=0; i<T.length();i++){
                    menu.add(T.getJSONObject(i).getString("cat"));
                }
            }

            //Partie Produits

            inputStream =getResources().getAssets().open("produits.json");
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            stringBuilder=new StringBuilder();
            while((ligne=bufferedReader.readLine())!=null){
                stringBuilder.append(ligne);
            }
            inputStream.close();
            data=stringBuilder.toString();
            TPro=new JSONArray(data);





        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }







        navig.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals("Panier")){
//                    String panier="";
//                    for(int i=0;i<Produit.panier.size();i++){
//                        panier+=Produit.panier.get(i).getNom()+"\n";
//                    }
//                    Toast.makeText(getApplicationContext(),"Votre panier :\n"+panier,Toast.LENGTH_SHORT).show();


                    Intent it=new Intent(getApplicationContext(),PanierActivity.class);
                    it.putExtra("monPanier",Produit.panier);
                    startActivity(it);


                }
                else{
                //Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
                txt.setText("");
                txt.setText(str+" "+item.getTitle());
                lst.clear();
                try{
                    for(int i=0;i<TPro.length();i++){
                        String catpro=TPro.getJSONObject(i).getString("cat");
                        if(item.getTitle().equals(catpro)){
                            Produit P=new Produit(TPro.getJSONObject(i).getString("code"),
                                    TPro.getJSONObject(i).getString("nom"),
                                    TPro.getJSONObject(i).getDouble("prix"),
                                    TPro.getJSONObject(i).getString("cat"),
                                    TPro.getJSONObject(i).getString("photo"));
                            lst.add(P);
                        }

                    }
//                    Toast.makeText(getApplicationContext(),String.valueOf(lst.size()),Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

                FragmentManager fm=getSupportFragmentManager();

                F1Fragment F=(F1Fragment) fm.findFragmentById(R.id.frag1);
                F.Afficher(lst);

                F2Fragment F2=(F2Fragment)fm.findFragmentById(R.id.frag2);
                F2.Afficher(null,null);
                drawer.closeDrawer(GravityCompat.START);}
                return true;
            }
        });




    }
}
