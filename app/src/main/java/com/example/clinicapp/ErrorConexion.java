package com.example.clinicapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ErrorConexion extends AppCompatActivity {
    private final int DURACION_SPLASH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_conexion);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Toast.makeText(getApplicationContext(),"Intentarlo mas tarde falta conexion de internet",Toast.LENGTH_LONG).show();
                finish();
            };
        }, DURACION_SPLASH);
    }
}
