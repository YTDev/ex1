package com.example.ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawer;
    NavigationView navig;
    ActionBarDrawerToggle toggle;
    Menu menu;
    JSONArray T=null;

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


        drawer=findViewById(R.id.drawer);
        navig=findViewById(R.id.navig);
        menu=navig.getMenu();//On va appliquer le Add sur cette element <=>arraylist
        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //partie JSON
        InputStream inputStream= null;
        try {
            inputStream = getResources().getAssets().open("categories.json");
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();
            String ligne;
            for (ligne=bufferedReader.readLine();ligne!=null;ligne=bufferedReader.readLine()){
                stringBuilder.append(ligne);
            }
            inputStream.close();
            String data=stringBuilder.toString();


            T=new JSONArray(data);
            if (T!=null){

                for(int i=0; i<T.length();i++){
                    menu.add(T.getJSONObject(i).getString("cat"));
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }







        navig.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });




    }
}
