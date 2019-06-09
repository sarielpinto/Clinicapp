package com.example.clinicapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class splash extends AppCompatActivity {
    private final int DURACION_SPLASH = 3000; // 3 segundos
    private TextView texto;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        texto=(TextView) findViewById(R.id.texto);
        iv=(ImageView) findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        texto.startAnimation(myanim);
        iv.startAnimation(myanim);
        //Conexxion a internet
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null&&networkInfo.isConnectedOrConnecting()){
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                    Intent intent = new Intent(splash.this, LoginActivity.class);
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

        }

        //Splash

    }
}
