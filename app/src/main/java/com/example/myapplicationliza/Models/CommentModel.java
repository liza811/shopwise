package com.example.myapplicationliza.Models;

public class CommentModel {
    public CommentModel() {
        // empty constructor
    }
    private String nom_utilisateur, comment, date;

    public CommentModel(String nom_utilisateur, String comment, String date) {
        this.nom_utilisateur = nom_utilisateur;
        this.comment = comment;
        this.date = date;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
