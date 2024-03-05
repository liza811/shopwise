package com.example.myapplicationliza.Models;

public class CategorieModel {
    public CategorieModel(){
        /// empty constructor
    }
    private String cat_image;
    private String cat_title;


    public CategorieModel( String cat_title,String cat_image) {
        this.cat_image = cat_image;
        this.cat_title = cat_title;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }
}
