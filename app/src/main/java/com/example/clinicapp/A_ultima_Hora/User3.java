package com.example.clinicapp.A_ultima_Hora;

public class User3 {

    private String name;
    private String country;
    private double weight;


    public User3(){}
    public User3(String n, String c, double w){
        this.name=n;
        this.country=c;
        this.weight=w;

    }
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getWeight() {
        return weight;
    }



    @Override
    public String toString() {
        return name +" "+ country +" " +weight;
    }
}