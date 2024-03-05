package com.example.myapplicationliza.Models;

public class ArticleOccasionModel {
    public ArticleOccasionModel() {
        // empty constructor
    }

    private String pub_id;
    private String pub_title, pub_image, prix;

    public ArticleOccasionModel(String pub_id, String pub_title, String pub_image, String prix) {
        this.pub_id = pub_id;
        this.pub_title = pub_title;
        this.pub_image = pub_image;
        this.prix = prix;
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
}