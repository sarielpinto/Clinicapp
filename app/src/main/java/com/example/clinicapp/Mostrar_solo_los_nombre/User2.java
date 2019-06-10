package com.example.clinicapp.Mostrar_solo_los_nombre;

public class User2 {

    private String name;



    public User2(){}
    public User2(String n, String c, double w){
        this.name=n;


    }
    public String getName() {
        return name;
    }





    @Override
    public String toString() {
        return name +" ";
    }
}
