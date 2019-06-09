package com.example.clinicapp;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.devazt.networking.HttpClient;
import com.devazt.networking.OnHttpRequestComplete;
import com.devazt.networking.Response;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class detalles extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap LoadImage;
    private EditText PHONE;
    private Button BTNPHONE;

    private final int phonecode = 100;
    public String phoneNumber="9831069810";
    private String correo="chidez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        final FloatingActionsMenu menubotones=(FloatingActionsMenu) findViewById(R.id.grupofab);
        final TextView nombre = (TextView) findViewById(R.id.detail_name);
        final TextView esp = (TextView) findViewById(R.id.detail_especialidad);
        final TextView des = (TextView) findViewById(R.id.detail_description);
        imageView = (ImageView) findViewById(R.id.detail_image);
        HttpClient client = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {


                if (status.isSuccess()) {
                    Gson gson = new GsonBuilder().create();



                    try {
                        JSONObject jsono = new JSONObject(status.getResult());
                        JSONArray jsonArray = jsono.getJSONArray("crack5");
                        ArrayList<Person> personas = new ArrayList<Person>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String person = jsonArray.getString(i);

                            Person p = gson.fromJson(person, Person.class);
                            personas.add(p);
                            TextView t = new TextView(getBaseContext());
                            nombre.setText(p.getName());
                            esp.setText(p.getEspecialidad());
                            des.setText(p.getDescripcion()  );
                            String imageHttpAddress = p.getImage();
                            downloadFile(imageHttpAddress);
                            phoneNumber=p.getTelefono();
                            correo=p.getCorreo();



                        }


                    } catch (Exception e) {
                        e.printStackTrace();


                    }



                }

            }
        });
        client.excecute("https://gist.githubusercontent.com/reneantoniopalomo/d34d8d5b648101478d7e78ea2ed7098b/raw/458ce378eb7f266bd196e5470ad4f82cb01616a2/recio.json");
    }void downloadFile(String imageHttpAddress) {
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            LoadImage= BitmapFactory.decodeStream(conn.getInputStream());
            imageView.setImageBitmap(LoadImage);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error cargando la imagen: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        FloatingActionButton fab2=findViewById(R.id.email);
        final FloatingActionsMenu chido=findViewById(R.id.grupofab);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();

            }
        });





        FloatingActionButton fab = findViewById(R.id.llamar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneNumber!=null && !phoneNumber.isEmpty()) {
                    //comprobar version actual de android
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //Comprobar si ha aceptado, no ha aceptado, o nunca se le ha preguntado
                        if(CheckPermission(Manifest.permission.CALL_PHONE)){
                            //Ha aceptado
                            Intent i=new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+phoneNumber));
                            if(ActivityCompat.checkSelfPermission(detalles.this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)return;
                            startActivity(i);
                        }else{
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //No se le ha preguntado aún
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, phonecode);



                            }else{
                                //Ha denegado
                                Toast.makeText(detalles.this, "Please, enable the request permisssion",Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:"+ getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);





                            }

                        }


                    } else {
                        OldersVersions(phoneNumber);
                    }
                }
            }
            private void OldersVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                startActivity(intentCall);
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(detalles.this, "You declined the access", Toast.LENGTH_SHORT).show();
                }

            }


        });}










    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //caso del telefono
        switch (requestCode){
            case phonecode:
                String permission= permissions[0];
                int result=grantResults[0];
                if(permission.equals(Manifest.permission.CALL_PHONE)){
                    //comprobar la situacion del permiso
                    if(result== PackageManager.PERMISSION_GRANTED){
                        //concedió el permiso

                        Intent intentCall=new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+phoneNumber));
                        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)return;
                        startActivity(intentCall);
                    }else{
                        //No concedió su permiso
                        Toast.makeText(detalles.this, "You declined the access",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;

        }


    }

    private boolean CheckPermission(String permission){
        int result=this.checkCallingOrSelfPermission(permission);
        return result==PackageManager.PERMISSION_GRANTED;







    }
    private void enviarEmail(){
        //Instanciamos un Intent del tipo ACTION_SEND
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        //Definimos la tipologia de datos del contenido dle Email en este caso text/html
        emailIntent.setType("text/html");
        // Indicamos con un Array de tipo String las direcciones de correo a las cuales
        //queremos enviar el texto
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
        // Definimos un titulo para el Email
        emailIntent.putExtra(android.content.Intent.EXTRA_TITLE, "El Titulo");
        // Definimos un Asunto para el Email
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "El Asunto");
        // Obtenemos la referencia al texto y lo pasamos al Email Intent
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.my_text));
        try {
            //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
            startActivity(Intent.createChooser(emailIntent, "Enviar E-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {

        }


    }}




