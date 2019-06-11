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
        setContentView(R.layout.activity_main);
        te_1=findViewById(R.id.t_1);
        te_2=findViewById(R.id.t_2);


    }
    public void onClick (View view){
        Intent miIntent = null;
        switch (view.getId()) {
            case R.id.b_1:
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //te_1.setText(year+"/"+"0"+(monthOfYear+1)+"/"+dayOfMonth);
                        if (monthOfYear < 10) {

                            month = "0" + monthOfYear;
                        } else if (monthOfYear >= 10) {
                            month = "" + monthOfYear;
                        }
                        if (dayOfMonth < 10) {

                            day = "0" + dayOfMonth;
                        } else if (dayOfMonth >= 10) {
                            day = "" + dayOfMonth;
                        }
                        te_1.setText(year + "/" + month + "/" + day);
                    }
                }
                        , ano, mes, dia);
                datePickerDialog.show();
                break;
            case R.id.b_2:
                final Calendar x = Calendar.getInstance();
                hora = x.get(Calendar.HOUR_OF_DAY);
                minutos = x.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        te_2.setText(hourOfDay + ":" + minute);
                        mas = (hourOfDay + ":" + (minute + 15));
                    }
                }, hora, minutos, false);
                timePickerDialog.show();


        }
        if (miIntent != null) {
            startActivity(miIntent);
        }

    }
}
