package com.example.clinicapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class splash extends AppCompatActivity {
    private final int DURACION_SPLASH = 3000; // 3 segundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Conexxion a internet
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null&&networkInfo.isConnectedOrConnecting()){
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    Intent intent = new Intent(splash.this, Signup.class);
                    startActivity(intent);
                    finish();
                };
            }, DURACION_SPLASH);
            Toast.makeText(getApplicationContext(),"Conectado",Toast.LENGTH_SHORT).show();
        }else{
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    Intent intent = new Intent(splash.this, ErrorConexion.class);
                    startActivity(intent);
                    finish();
                };
            }, DURACION_SPLASH);
            Toast.makeText(getApplicationContext(),"Falta Internet",Toast.LENGTH_SHORT).show();
        }

        //Splash

    }
}
