package com.example.clinicapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText userMail,userPassword;
    private Button btnlogin,btnRegister;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ImageView loginPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        btnlogin = findViewById(R.id.btnlogin);
        loginProgress = findViewById(R.id.login_progress);
        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this,com.example.clinicapp.Menu.class);
        loginPhoto = findViewById(R.id.login_photo);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity =new Intent(getApplicationContext(),LoadingActivity.class);
                registerActivity.putExtra("ID", "registerActivity");
                startActivity(registerActivity);
                finish();
            }
        });


        loginProgress.setVisibility(View.INVISIBLE);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.VISIBLE);
                btnlogin.setVisibility(View.INVISIBLE);

                final String mail= userMail.getText().toString();
                final String password=userPassword.getText().toString();

                if(mail.isEmpty() || password.isEmpty()){
                    showMessage("Por favor verifique todas los campos");
                    btnlogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    signIn(mail,password);
                }


            }
        });


    }

    private void signIn(String mail, String password) {


        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    loginProgress.setVisibility(View.INVISIBLE);
                    btnlogin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else
                {
                    showMessage("La cuenta no esta registrada");
                    btnlogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    private void updateUI() {


        /*startActivity(HomeActivity);
        finish();*/
        Intent homeActivity = new Intent (getApplicationContext(),LoadingActivity.class);
        homeActivity.putExtra("ID", "Menu");
        startActivity(homeActivity);
        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  mAuth.getCurrentUser();

        if(user != null )
        {
            //user is already connected so we need to redirect him to home page
            updateUI();
        }

    }
}
