package com.example.myapplicationliza.RetrofitApi;

import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.Models.CategorieModel;
import com.example.myapplicationliza.Models.ChatMessage;
import com.example.myapplicationliza.Models.CommentModel;
import com.example.myapplicationliza.Models.MessagerieModel;
import com.example.myapplicationliza.Models.PanierModel;
import com.example.myapplicationliza.Models.recu_commandeModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class users {
    @SerializedName("response")
    private String Response;
    public String getResponse() {
        return Response;
    }
    @SerializedName("res")
    private String res;

    @SerializedName("nom_utilisateur")
    private String nom_utilisateur;
    @SerializedName("email")
    private String email;
    @SerializedName("mot_de_passe")
    private String mot_de_passe;
    @SerializedName("pub_id")
    private String pub_id;

    @SerializedName("pub_title")
    private String pub_title;

    @SerializedName("pub_image")
    private String pub_image;

    @SerializedName("prix")
    private String prix;
    @SerializedName("num_tel")
    private String num_tel;
    @SerializedName("descriptionn")
    private String descriptionn;

    @SerializedName("responsee")
    private String responsee;


    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("id_utilisateur")
    private String id;
    @SerializedName("publicite")
    private String publicite;



    @SerializedName("categorie")
    private List<CategorieModel> categorie;
    @SerializedName("article_occasion")
    private List<ArticleOccasionModel> article_occasion;
    @SerializedName("article_cosmetique")
    private List<ArticleOccasionModel> article_cosmetique;
    @SerializedName("commande")
    private List<PanierModel> commande;
    @SerializedName("commande_valide")
    private List<recu_commandeModel> commande_valide;
    @SerializedName("commentaire")
    private List<CommentModel> commentaire;
    @SerializedName("msg")
    private List<ChatMessage> msg;
    @SerializedName("article_vetement")
    private List<ArticleOccasionModel> article_vetement;
    @SerializedName("article_imm")
    private List<ArticleOccasionModel> article_imm;
    @SerializedName("article_ele")
    private List<ArticleOccasionModel> article_ele;
    @SerializedName("article_auto")
    private List<ArticleOccasionModel> article_auto;
    @SerializedName("historique_annonce")
    private List<ArticleOccasionModel> historique_annonce;
    @SerializedName("historique_annonce1")
    private List<ArticleOccasionModel> historique_annonce1;


    @SerializedName("historique_commande")
    private List<ArticleOccasionModel> historique_commande;
    @SerializedName("msgg")
    private List<MessagerieModel> msgg;
    @SerializedName("annonce")
    private List<ArticleOccasionModel> annonce;
    @SerializedName("search")
    private List<ArticleOccasionModel> search;


    public List<ArticleOccasionModel> getHistorique_commande() {
        return historique_commande;
    }

    @SerializedName("article_info")
    private List<ArticleOccasionModel> article_info;
    @SerializedName("article_piece")
    private List<ArticleOccasionModel> article_piece;

    public List<PanierModel> getCommande() {
        return commande;
    }

    public void setCommande(List<PanierModel> commande) {
        this.commande = commande;
    }

    public List<ArticleOccasionModel> getArticle_piece() {
        return article_piece;
    }

    public List<ArticleOccasionModel> getArticle_info() {
        return article_info;
    }

    public List<ArticleOccasionModel> getArticle_auto() {
        return article_auto;
    }

    public List<ArticleOccasionModel> getArticle_ele() {
        return article_ele;
    }

    public List<ArticleOccasionModel> getArticle_imm() {
        return article_imm;
    }

    public List<ArticleOccasionModel> getArticle_vetement() {
        return article_vetement;
    }



    public void setResponse(String response) {
        Response = response;
    }

    public String getResponsee() {
        return responsee;
    }

    public void setResponsee(String responsee) {
        this.responsee = responsee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPublicite(String publicite) {
        this.publicite = publicite;
    }

    public List<CategorieModel> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<CategorieModel> categorie) {
        this.categorie = categorie;
    }

    public List<ArticleOccasionModel> getArticle_occasion() {
        return article_occasion;
    }

    public void setArticle_occasion(List<ArticleOccasionModel> article_occasion) {
        this.article_occasion = article_occasion;
    }

    public List<ArticleOccasionModel> getArticle_cosmetique() {
        return article_cosmetique;
    }

    public void setArticle_cosmetique(List<ArticleOccasionModel> article_cosmetique) {
        this.article_cosmetique = article_cosmetique;
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



    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDescriptionn() {
        return descriptionn;
    }

    public void setDescriptionn(String descriptionn) {
        this.descriptionn = descriptionn;
    }

    public void setArticle_vetement(List<ArticleOccasionModel> article_vetement) {
        this.article_vetement = article_vetement;
    }

    public void setArticle_imm(List<ArticleOccasionModel> article_imm) {
        this.article_imm = article_imm;
    }

    public void setArticle_ele(List<ArticleOccasionModel> article_ele) {
        this.article_ele = article_ele;
    }

    public void setArticle_auto(List<ArticleOccasionModel> article_auto) {
        this.article_auto = article_auto;
    }

    public List<ArticleOccasionModel> getHistorique_annonce() {
        return historique_annonce;
    }

    public void setHistorique_annonce(List<ArticleOccasionModel> historique_annonce) {
        this.historique_annonce = historique_annonce;
    }

    public void setArticle_info(List<ArticleOccasionModel> article_info) {
        this.article_info = article_info;
    }

    public void setArticle_piece(List<ArticleOccasionModel> article_piece) {
        this.article_piece = article_piece;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setHistorique_commande(List<ArticleOccasionModel> historique_commande) {
        this.historique_commande = historique_commande;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getPublicite() {
        return publicite;
    }

    public List<CommentModel> getCommentaire() {
        return commentaire;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setCommentaire(List<CommentModel> commentaire) {
        this.commentaire = commentaire;
    }

    public List<ArticleOccasionModel> getAnnonce() {
        return annonce;
    }

    public void setAnnonce(List<ArticleOccasionModel> annonce) {
        this.annonce = annonce;
    }

    public List<ChatMessage> getMsg() {
        return msg;
    }

    public void setMsg(List<ChatMessage> msg) {
        this.msg = msg;
    }

    public List<ArticleOccasionModel> getSearch() {
        return search;
    }

    public void setSearch(List<ArticleOccasionModel> search) {
        this.search = search;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public List<MessagerieModel> getMsgg() {
        return msgg;
    }

    public void setMsgg(List<MessagerieModel> msgg) {
        this.msgg = msgg;
    }

    public List<ArticleOccasionModel> getHistorique_annonce1() {
        return historique_annonce1;
    }

    public void setHistorique_annonce1(List<ArticleOccasionModel> historique_annonce1) {
        this.historique_annonce1 = historique_annonce1;
    }

    public List<recu_commandeModel> getCommande_valide() {
        return commande_valide;
    }

    public void setCommande_valide(List<recu_commandeModel> commande_valide) {
        this.commande_valide = commande_valide;
    }
}