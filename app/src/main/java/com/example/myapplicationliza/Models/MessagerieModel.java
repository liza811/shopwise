package com.example.myapplicationliza.Models;

public class MessagerieModel {
    public MessagerieModel(){
        /// empty constructor
    }
    private  String message;
    private  String time;
    private  String nom;

    public MessagerieModel(String message, String time, String nom) {
        this.message = message;
        this.time = time;
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
