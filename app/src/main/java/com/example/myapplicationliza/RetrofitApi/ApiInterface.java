package com.example.myapplicationliza.RetrofitApi;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    //////////////////////////////LOGIN//////////////////////////////////////////////////////////////////
    @GET("users/login.php")
    Call<users> performLogin(
            @Query("email") String email,
            @Query("mot_de_passe") String mot_de_passe


    );


    //////////////////////INSCRIPTION////////////////////////////////////////////////////
    @GET("users/inscription.php")
    Call<users> performInscription(
        @Query("email") String email,
        @Query("mot_de_passe") String mot_de_passe,
        @Query("nom_utilisateur") String nom_utilisateur


        );
    //####################""" mdp oublie ############################"""""
    //////////////////////INSCRIPTION////////////////////////////////////////////////////
    @GET("api/send_email.php")
    Call<users> send_email(
            @Query("email") String email



    );
    //////////////////////insert_msg////////////////////////////////////////////////////
    @GET("api/insert_msg.php")
    Call<users> insert_msg(
            @Query("sender_id") String sender_id,
            @Query("pub_id") String pub_id,
            @Query("message_content") String message_content



    );
    //////////////////////afficher_msg////////////////////////////////////////////////////
    @GET("api/afficher_msg.php")
    Call<users> afficher_msg(
            @Query("sender_id") String sender_id,
            @Query("pub_id") String pub_id




    );

    //////////////////////Modifier info profil////////////////////////////////////////////////////
    @GET("api/sauvgarder.php")
    Call<users> sauvgarder(
            @Query("utilisateur_id") String utilisateur_id,
            @Query("email") String email,
            @Query("nom_utilisateur") String nom_utilisateur,
            @Query("mot_de_passe") String mot_de_passe,
            @Query("num_tel") String num_tel


    );
    //////////////////////ACHETER////////////////////////////////////////////////////
    @GET("users/acheter.php")
    Call<users> performAcheter(
            @Query("id_utilisateur") String id_utilisateur,

            @Query("id_publication") String id_publication


    );
    //////////////////////panier////////////////////////////////////////////////////
    @GET("users/panier.php")
    Call<users> performPanier(
            @Query("utilisateur_id") String utilisateur_id,
            @Query("publication_id") String publication_id



    );
    //////////////////////      profil  /////////////////////////////////////////////////
    @GET("api/profile.php")
    Call<users> profile(@Query("utilisateur_id") String utilisateur_id);

   //################## valider panier ##########################
    @GET("api/valider.php")
    Call<users> valider(@Query("utilisateur_id") String utilisateur_id);

    @GET("api/recu_commande.php")
    Call<users> recu_commande(@Query("utilisateur_id") String utilisateur_id);

    @GET("api/price.php")
    Call<users> price(@Query("utilisateur_id") String utilisateur_id);
    ////////////////////////////////////////////////////////////////////////////////
    //################## vider panier ##########################
    @GET("api/vider_panier.php")
    Call<users> getVider(@Query("utilisateur_id") String utilisateur_id);
    ////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////getting categories /////////////////////////////
    @GET("api/categories.php")
    Call<users> getCategories();
  ////////////////////////////////////////////////////////////////////////////////
  @Multipart
  @POST("users/upload_api.php")
    Call<users> callUploadApi(@Part MultipartBody.Part image,
    @Query("pub_title") String pub_title,
    @Query("prix") String prix,
    @Query("statut") String statut,
    @Query("categorie") String categorie,
    @Query("quantite") String quantite,
    @Query("descriptionn") String descriptionn,
    @Query("id_utilisateur") String id
  );

    ////////////////////////////////////  publié ////////////////////////////////////////////
    @GET("users/inserer_pub.php")
    Call<users> performpub(

    );
    ////////////////// article occasion///////////////////////////////
    @GET("api/article_occasion.php")
    Call<users> getArticle_occasion();
    ////////////////// article cosmetique///////////////////////////////
    @GET("api/article_cosmetique.php")
    Call<users> getArticle();
    ////////////////// article vetement///////////////////////////////
    @GET("api/article_vetement.php")
    Call<users> getVetement();
   //////////////////// article immobilier///////////////////////////////
       @GET("api/article_ele.php")
       Call<users> getelectronique();
    //////////////////// article electronique///////////////////////////////
    @GET("api/article_auto.php")
    Call<users> getAutomobile();
    //////////////////// article automobile///////////////////////////////
    @GET("api/article_imm.php")
    Call<users> getImmobilier();
    //////////////////// article Informatique///////////////////////////////
    @GET("api/article_info.php")
    Call<users> getInformatique();
    //////////////////// article piece///////////////////////////////
    @GET("api/article_piece.php")
    Call<users> getPiece();




    ////////////////// publicite///////////////////////////////
    @GET("api/banners.php")
    Call<users> getBanners();
    //////////////////////////////LOGIN//////////////////////////////////////////////////////////////////
    @GET("api/detail_publication.php")
    Call<users> performDetails(
            @Query("pub_id") String pub_id



    );
////////////////////// messagerie//////////////////////
    @GET("api/m.php")
    Call<users> messagerie(@Query("currentUser") String currentUser);

    ////////////////////// historique annonces//////////////////////
    @GET("api/historique_annonce.php")
    Call<users> historique_annonce(@Query("utilisateur_id") String utilisateur_id);
    @GET("api/historique_annonce1.php")
    Call<users> historique_annonce1(@Query("utilisateur_id") String utilisateur_id);

    //////////////////////  historique_commandes//////////////////////
    @GET("api/historique_commandes.php")
    Call<users> historique_commandes(@Query("utilisateur_id") String utilisateur_id);
    ////////////////// panier///////////////////////////////
    @GET("api/cmd.php")
    Call<users> getPanier(@Query("utilisateur_id") String utilisateur_id);

    @GET("api/search.php")
    Call<users> search(@Query("text") String text);


////////////// contact et coordonnés//////////////////////

    @GET("api/contact.php")
    Call<users> getContact(@Query("pub_id") String pub_id);
////////////// commenter//////////////////////

    @GET("api/enregistrer_commentaire.php")
    Call<users> getComment(@Query("id_utilisateur") String id_utilisateur,
                           @Query("id_publication") String id_publication,
                           @Query("commentaire") String commentaire

    );
    ////////////////// afficher commentaire///////////////////////////////
    @GET("api/afficher_commentaire.php")
    Call<users> getComments(@Query("id_publication") String id_publication);
    ////////////////// annonces du vendeur///////////////////////////////
    @GET("api/annonces_du_vendeur.php")
    Call<users> annonces_du_vendeur(@Query("publication_id") String publication_id);
    ////////////////// discussions///////////////////////////////
    @GET("api/discussion.php")
    Call<users> discussion(
            @Query("moi") String moi,
            @Query("autre") String autre
    );
    ////////////////// annonces similaires///////////////////////////////
    @GET("api/annonces_similaires.php")
    Call<users> annonces_similaires(@Query("publication_id") String publication_id);

///  ###########################""    liza ###################################"""""
    @GET("api/liza.php")
    Call<users> liza(
            @Query("pub_id") String pub_id

    );
    @GET("api/ajouter_num.php")
    Call<users> ajouter_num(
            @Query("utilisateur_id") String utilisateur_id,
            @Query("num_tel") String num_tel




    );
    @GET("api/changer_mdp.php")
    Call<users> changer_mdp(
            @Query("utilisateur_id") String utilisateur_id,
            @Query("mot_de_passe") String mot_de_passe




    );
    @GET("api/mail.php")
    Call<users> mail(
            @Query("email") String email,
            @Query("verificationCode") String verificationCode




    );

    @GET("api/chercher_utilisateur.php")
    Call<users> chercher_utilisateur(
            @Query("pub_id") String pub_id



    );
    @GET("api/inser_msg2.php")
    Call<users> inser_msg2(
            @Query("sender_id") String sender_id,
            @Query("reciever_name") String reciever_name,
            @Query("message_content") String message_content



    );
    @GET("api/delete_panier_item.php")
    Call<users> delete_panier_item(
            @Query("utilisateur_id") String utilisateur_id,

            @Query("pub_id") String pub_id



    );
}
