package com.example.myapplicationliza.Models;

public class recu_commandeModel {
    public recu_commandeModel(){
        /// empty constructor
    }
    private String pub_title;
    private String prix;

    public recu_commandeModel(String pub_title, String prix) {
        this.pub_title = pub_title;
        this.prix = prix;
    }

    public String getPub_title() {
        return pub_title;
    }

    public void setPub_title(String pub_title) {
        this.pub_title = pub_title;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
