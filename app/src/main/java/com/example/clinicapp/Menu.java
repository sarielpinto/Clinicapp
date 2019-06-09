package com.example.clinicapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void citas(View view) {
        Intent intent= new Intent(Menu.this,Main2Activity.class);
        startActivity(intent);
    }


    public void Especilistas(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void Ubicacion(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }
    public void missitas(View view){
        Intent intent = new Intent(getApplicationContext(), Citas.class);
        startActivity(intent);
    }
}