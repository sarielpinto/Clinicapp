package com.example.clinicapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.clinicapp.Mostrar_solo_los_nombre.Lista;

public class LoadingActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 1500; // 3 segundos
    String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null){
            ID = (b.getString("ID"));
        }




        //Conexxion a internet
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();



        switch (ID) {
            case "registerActivity":

                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, RegisterActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    }, DURACION_SPLASH);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, ErrorConexion.class);
                            startActivity(intent);
                            finish();
                        }

                        ;
                    }, DURACION_SPLASH);

                }

                //Splash
                break;

            case "Menu":


                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, Menu.class);
                            startActivity(intent);
                            finish();
                        }


                    }, DURACION_SPLASH);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, ErrorConexion.class);
                            startActivity(intent);
                            finish();
                        }

                        ;
                    }, DURACION_SPLASH);

                }


                break;


            case "Citas":


                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, Main2Activity.class);
                            startActivity(intent);
                            finish();
                        }


                    }, DURACION_SPLASH);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, ErrorConexion.class);
                            startActivity(intent);
                            finish();
                        }

                        ;
                    }, DURACION_SPLASH);

                }

                break;

            case "Especialistas":

                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    }, DURACION_SPLASH);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, ErrorConexion.class);
                            startActivity(intent);
                            finish();
                        }

                        ;
                    }, DURACION_SPLASH);

                }

                break;

            case "Ubicación":


                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, MapsActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    }, DURACION_SPLASH);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, ErrorConexion.class);
                            startActivity(intent);
                            finish();
                        }

                        ;
                    }, DURACION_SPLASH);

                }

                break;

            case "Missitas":


                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, Lista.class);
                            startActivity(intent);
                            finish();
                        }


                    }, DURACION_SPLASH);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                            Intent intent = new Intent(LoadingActivity.this, ErrorConexion.class);
                            startActivity(intent);
                            finish();
                        }

                        ;
                    }, DURACION_SPLASH);

                }

                break;
        }
    }
}
