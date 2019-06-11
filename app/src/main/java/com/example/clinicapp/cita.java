package com.example.clinicapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class cita extends AppCompatActivity {

    TextView te_1,te_2;
    private  int dia,mes,ano,hora,minutos;
    String month;
    String day;
    String mas;
    //barra de progreso
    ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

    }
}
