package com.example.clinicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.clinicapp.Mostrar_solo_los_nombre.Lista;
public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void citas(View view) {
        /*Intent intent= new Intent(Menu.this,Main2Activity.class);
        startActivity(intent);*/
        Intent MenuActivity = new Intent (getApplicationContext(),LoadingActivity.class);
        MenuActivity.putExtra("ID", "Citas");
        startActivity(MenuActivity);
    }


    public void Especilistas(View view) {
        /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);*/

        Intent MenuActivity = new Intent (getApplicationContext(),LoadingActivity.class);
        MenuActivity.putExtra("ID", "Especialistas");
        startActivity(MenuActivity);
    }

    public void Ubicacion(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);


        Intent MenuActivity = new Intent (getApplicationContext(),LoadingActivity.class);
        MenuActivity.putExtra("ID", "Ubicacion");
        startActivity(MenuActivity);
    }
    public void missitas(View view){
        Intent MenuActivity = new Intent (getApplicationContext(),LoadingActivity.class);
        MenuActivity.putExtra("ID", "Missitas");
        startActivity(MenuActivity);
    }
}