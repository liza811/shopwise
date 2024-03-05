package com.example.myapplicationliza.Models;

public class PanierModel {
    public PanierModel() {
        // empty constructor
    }

    private String pub_id;
    private String pub_title, pub_image, prix,vendeur_id;

    public PanierModel(String pub_id, String pub_title, String pub_image, String prix, String vendeur_id) {
        this.pub_id = pub_id;
        this.pub_title = pub_title;
        this.pub_image = pub_image;
        this.prix = prix;
        this.vendeur_id = vendeur_id;
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }

    public String getPub_title() {
        return pub_title;
    }

    public void setPub_title(String pub_title) {
        this.pub_title = pub_title;
    }

    public String getPub_image() {
        return pub_image;
    }

    public void setPub_image(String pub_image) {
        this.pub_image = pub_image;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getVendeur_id() {
        return vendeur_id;
    }

    public void setVendeur_id(String vendeur_id) {
        this.vendeur_id = vendeur_id;
    }
}
