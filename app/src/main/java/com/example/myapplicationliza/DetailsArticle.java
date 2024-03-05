package com.example.myapplicationliza;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.myapplicationliza.Adapters.CommentAdapter;
import com.example.myapplicationliza.Adapters.ImmobilierAdapter;
import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.Models.CommentModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.example.myapplicationliza.Sessions.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageView;


public class DetailsArticle extends AppCompatActivity implements RecyclerViewInterface {

    TextView dPubTitre, dPubPrix, dPubDescription, ut_id_text, pubb_id_text, dnom_utilisateur, dnum_tem, demail, dut_id, profile_logo,paiement;
    ImageView commenter;
    private int currentContentViewId = R.layout.activity_details_article;
    boolean showFullList = false;
    private RecyclerView RVCommentaire, RVAduvendeur, RVSimilaire;
    private ImmobilierAdapter immobilierAdapter;
    SessionManager sessionManager;
    LinearLayout lcom;
    private List<ArticleOccasionModel> hisModel;
    private List<CommentModel> commentModel;
    private CommentAdapter CommentAdapter;

    EditText commentaire;
    NeumorphButton panier;
    public static ApiInterface apiInterface;
    NeumorphButton acheter, message;

    @SuppressLint({"MissingInflatedId", "ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_article);
        //////////////////////////////////////////////////////////////
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);
        ////////////////////////////////////////////////////////////////////////////////////////

        //####################### recevoir les details d'article#########################################
        String pubb_title = getIntent().getStringExtra("pubb_title");
        String pubb_id = getIntent().getStringExtra("pubb_id");
        String pub_image = getIntent().getStringExtra("pub_image");
        String prix = getIntent().getStringExtra("prix");
        String descriptionn = getIntent().getStringExtra("descriptionn");
        String ut_id = getIntent().getStringExtra("ut_id");


        profile_logo = findViewById(R.id.profile_logo);
        dPubTitre = findViewById(R.id.dPubTitre);
        dPubPrix = findViewById(R.id.dPubPrix);
       // dPubImage = (ImageView) findViewById(R.id.dPubImage);
        commenter = findViewById(R.id.commenter);
        dPubDescription = findViewById(R.id.dPubDescription);
        ut_id_text = findViewById(R.id.ut_id);
        pubb_id_text = findViewById(R.id.pubb_id_text);
        dnom_utilisateur = findViewById(R.id.dnom_utilisateur);
        dnum_tem = findViewById(R.id.dnum_tel);
        demail = findViewById(R.id.demail);
        RVAduvendeur = findViewById(R.id.RVAduvendeur);


        dPubTitre.setText(pubb_title);
        // Glide.with(this).load(articleMod
        // el.getPub_image()).placeholder(R.drawable.shopwise).into(holder.article_image);
     //   int dpValue = 330; // The value in dp
       // float scale = getResources().getDisplayMetrics().density;
        // int pxValue = (int) (dpValue * scale + 0.5f);


        dPubPrix.setText(prix + " DA");
        dPubDescription.setText(descriptionn);
        ut_id_text.setText(ut_id);
        pubb_id_text.setText(pubb_id);

// Load the image with Glide
        // Load the image with Glide and extract the background color
        // Load the image using Glide

        NeumorphImageView neumorphImageView = findViewById(R.id.dPubImage); // Replace with your NeumorphImageView ID
        Glide.with(this)
                .load(pub_image) // Load your image here
                .transform(new CenterCrop())
                .override(1690, 1780)
                .into(neumorphImageView);


// Load the image with Glide and extract the bitmap
     /*   Glide.with(this)
                .asBitmap()
                .load(pub_image)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        // Here, 'bitmap' is the loaded bitmap
                        // You can now extract the dominant color from 'bitmap'

                        // Convert the 'bitmap' to a Mat
                        Mat image = new Mat();
                        Utils.bitmapToMat(bitmap, image);

                        // Extract the dominant color
                        Scalar dominantColor = colorUtils.extractDominantColor(image);
                        int red = (int) dominantColor.val[0];
                        int green = (int) dominantColor.val[1];
                        int blue = (int) dominantColor.val[2];

                        // Assuming you have extracted the dominant color as 'dominantColor' (Scalar object)


// Create an Android color integer
                        int alpha = 30;

// Create the color with decreased opacity
                        int backgroundColor = Color.argb(alpha, red, green, blue);

// Find your LinearLayout by its ID (replace R.id.yourLinearLayout with your actual ID)
                        CardView cardView = findViewById(R.id.card3);

// Set the background color

                        cardView.setCardBackgroundColor(backgroundColor);

                        cardView.setRadius(20);

                    }
                });



*/


        //##################################################################

        Call<users> calll = apiInterface.profile(ut_id);
        calll.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (response.body().getResponse().equals("ok")) {
                    dut_id = findViewById(R.id.dut_id);


                    dut_id.setText(response.body().getNom_utilisateur());


                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                //  Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        // {###########################   message  #########################""""""
        message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_utilis = ut_id_text.getText().toString();
                if (id_utilis == "") {
                    Dialog dialogg = new Dialog(DetailsArticle.this,R.style.CustomDialogTheme);

                    dialogg.setContentView(R.layout.action);
                    dialogg.show();
                    //////////################""" CONTINUER  ###########################

                    TextView confirmerr = dialogg.findViewById(R.id.continuer);
                    confirmerr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DetailsArticle.this, MainActivity.class);
                            startActivity(intent);
                            dialogg.dismiss();

                        }


                    });
                    //////////################""" annuler ###########################
                    TextView neumorphButton_annuler = dialogg.findViewById(R.id.annulerr);
                    neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialogg.dismiss();
                        }
                    });
                    //id_utilis="6";
                } else{
                    Intent intent = new Intent(DetailsArticle.this, Message.class);
                String pub_image = getIntent().getStringExtra("pub_image");
                String pubb_title = getIntent().getStringExtra("pubb_title");
                String pubb_id = getIntent().getStringExtra("pubb_id");
                String ut_id = getIntent().getStringExtra("ut_id");
                intent.putExtra("pub_image", pub_image);
                intent.putExtra("pubb_title", pubb_title);
                intent.putExtra("pubb_id", pubb_id);
                intent.putExtra("ut_id", ut_id);

                startActivity(intent);
            }}
        });
        //#####################"" afficher les cmnts  ##########################""
       afficher_les_cmntrs();
        //##################### commenter ################################"
        commenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentaire = findViewById(R.id.commentaire);
                ut_id_text = findViewById(R.id.ut_id);
                pubb_id_text = findViewById(R.id.pubb_id_text);
                String tcommentaire = commentaire.getText().toString().trim();
if(!tcommentaire.isEmpty()){


                String ppub_id = pubb_id_text.getText().toString();
                String utt_id = ut_id_text.getText().toString();

                Call<users> call = apiInterface.getComment(utt_id, ppub_id, tcommentaire);
                call.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        if (response.body().getResponse().equals("ok")) {
                           // Toast.makeText(DetailsArticle.this, "Commentaire ajouté", Toast.LENGTH_SHORT).show();
                            commentaire.setText("");
                            afficher_les_cmntrs();
                        }
                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {

                    }
                });

            }}
        });
        //####################""" annonces du vendeur  #################################
        String ppub_id = pubb_id_text.getText().toString();
        LinearLayoutManager linearLayoutManagerPiece = new LinearLayoutManager(this);
        linearLayoutManagerPiece.setOrientation(RecyclerView.HORIZONTAL);
        RVAduvendeur.setLayoutManager(linearLayoutManagerPiece);
        hisModel = new ArrayList<>();
        Call<users> immobilieCall = apiInterface.annonces_du_vendeur(ppub_id);
        immobilieCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(@NonNull Call<users> call, Response<users> response) {
                hisModel = response.body().getAnnonce();
                immobilierAdapter = new ImmobilierAdapter(hisModel, DetailsArticle.this, DetailsArticle.this::onItemClick);
                RVAduvendeur.setAdapter(immobilierAdapter);
                immobilierAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
        // #############""""" annonces similaires  #######################"
        RVSimilaire = findViewById(R.id.RVSimilaire);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RVSimilaire.setLayoutManager(gridLayoutManager);
        hisModel = new ArrayList<>();
        Call<users> pieceCall = apiInterface.annonces_similaires(ppub_id);
        pieceCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {


                hisModel = response.body().getAnnonce();
                immobilierAdapter = new ImmobilierAdapter(hisModel, DetailsArticle.this, DetailsArticle.this);
                RVSimilaire.setAdapter(immobilierAdapter);
                immobilierAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<users> call, Throwable t) {

            }
        });

        //################# coordonnés####################################################"""

        String pub_id = pubb_id_text.getText().toString();
        Call<users> call = apiInterface.getContact(pub_id);
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                demail.setText(response.body().getEmail());
                dnom_utilisateur.setText(response.body().getNom_utilisateur());
                dnum_tem.setText(response.body().getNum_tel());
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
        //#####################################################################################################"
        //###########################""" btn panier  ####################################################
        panier = findViewById(R.id.panierr);
        panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_utilis = ut_id_text.getText().toString();
                if (id_utilis == "") {
                    Dialog dialogg = new Dialog(DetailsArticle.this,R.style.CustomDialogTheme);

                    dialogg.setContentView(R.layout.action);
                    dialogg.show();
                    //////////################""" CONTINUER  ###########################

                    TextView confirmerr = dialogg.findViewById(R.id.continuer);
                    confirmerr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DetailsArticle.this, MainActivity.class);
                            startActivity(intent);
                            dialogg.dismiss();

                        }


                    });
                    //////////################""" annuler ###########################
                    TextView neumorphButton_annuler = dialogg.findViewById(R.id.annulerr);
                    neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialogg.dismiss();
                        }
                    });
                    //id_utilis="6";
                } else {

                    String id_publi = pubb_id_text.getText().toString();
                    Call<users> call = apiInterface.performPanier(id_utilis, id_publi);
                    call.enqueue(new Callback<users>() {
                        @Override
                        public void onResponse(Call<users> call, Response<users> response) {
                            if (response.body().getResponse().equals("ok")) {
                                Toast.makeText(DetailsArticle.this, "Ajouté au panier", Toast.LENGTH_SHORT).show();

                            } else if (response.body().getResponse().equals("failed!")) {
                                Toast.makeText(DetailsArticle.this, " failed", Toast.LENGTH_SHORT).show();

                            } else if (response.body().getResponse().equals("repture")) {
                                Toast.makeText(DetailsArticle.this, " article en repture de stock", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<users> call, Throwable t) {

                        }
                    });

                }
            }
        });
        //############################" acheter btn#######################################
        acheter = findViewById(R.id.acheter);
        acheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_utilis = ut_id_text.getText().toString();
                if (id_utilis == "") {
                    Dialog dialogg = new Dialog(DetailsArticle.this,R.style.CustomDialogTheme);

                    dialogg.setContentView(R.layout.action);
                    dialogg.show();
                    //////////################""" CONTINUER  ###########################

                    TextView confirmerr = dialogg.findViewById(R.id.continuer);
                    confirmerr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DetailsArticle.this, MainActivity.class);
                            startActivity(intent);
                            dialogg.dismiss();

                        }


                    });
                    //////////################""" annuler ###########################
                    TextView neumorphButton_annuler = dialogg.findViewById(R.id.annulerr);
                    neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialogg.dismiss();
                        }
                    });
                    //id_utilis="6";
                } else {
                    Dialog dialog = new Dialog(DetailsArticle.this);
                    dialog.setContentView(R.layout.boite_dialog);
                    //////////################""" Confirmer  ###########################

                    NeumorphButton confirmer = dialog.findViewById(R.id.confirmer);
                    confirmer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            acheter();
                            dialog.dismiss();

                        }


                    });
                    //////////################""" annuler ###########################
                    NeumorphButton neumorphButton_annuler = dialog.findViewById(R.id.annuler);
                    neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(DetailsArticle.this, "Commande annulée", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

//########################################################################################################
      /*  ImageView showAllCommentsButton = findViewById(R.id.all_comments);
        showAllCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFullList=!showFullList;
                setContentView(R.layout.all_comments);
                 afficher_les_cmntrs();
            }
        });*/  ImageView showAllCommentsButton = findViewById(R.id.all_comments);
        showAllCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFullList=!showFullList;
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(DetailsArticle.this, R.style.CustomBottomSheetDialogThemee);
                bottomSheetDialog.setContentView(R.layout.all_comments);
                View bottomSheetView = bottomSheetDialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet);

                 //  corner radius
                if (bottomSheetView != null) {
                    bottomSheetView.setBackgroundResource(R.drawable.custom_bottom_sheet_background);
                }
               View sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.all_comments, findViewById(R.id.contentView2));
                // afficher_les_cmntrs();
                bottomSheetDialog.show();
            }
        });

    }

    private void afficher_les_cmntrs() {
        RVCommentaire = findViewById(R.id.RVCommentaire);
        LinearLayoutManager linearLayoutManagerCosmetique = new LinearLayoutManager(this);
        linearLayoutManagerCosmetique.setOrientation(RecyclerView.VERTICAL);
        RVCommentaire.setLayoutManager(linearLayoutManagerCosmetique);
        commentModel = new ArrayList<>();
        String pubb_id = getIntent().getStringExtra("pubb_id");

        Call<users> cosmetiqueCall = apiInterface.getComments(pubb_id);
        cosmetiqueCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                commentModel = response.body().getCommentaire();

                if (!commentModel.isEmpty()) {
                  if(showFullList){
                      CommentAdapter = new CommentAdapter(commentModel, DetailsArticle.this);
                  }else if(commentModel.size() > 3 ){
                      List<CommentModel> firstThreeComments = commentModel.subList(0, 3);
                      CommentAdapter = new CommentAdapter(firstThreeComments, DetailsArticle.this);
                  }else{
                      CommentAdapter = new CommentAdapter(commentModel, DetailsArticle.this);
                  }


                    RVCommentaire.setAdapter(CommentAdapter);
                    CommentAdapter.notifyDataSetChanged();
                    if (currentContentViewId == R.layout.activity_details_article) {
                    TextView aucun = findViewById(R.id.aucun);
                   aucun.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });


    }



    public void acheter() {

        String id_utilis = ut_id_text.getText().toString();
        // Toast.makeText(DetailsArticle.this, id_utilis, Toast.LENGTH_SHORT).show();
        String id_publi = pubb_id_text.getText().toString();
        Call<users> call = apiInterface.performAcheter(id_utilis, id_publi);
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (response.body().getResponse().equals("ok")) {
                    Toast.makeText(DetailsArticle.this, "Commande passée", Toast.LENGTH_SHORT).show();
                } else if (response.body().getResponse().equals("failed!")) {
                    Toast.makeText(DetailsArticle.this, "erreur", Toast.LENGTH_SHORT).show();
                } else if (response.body().getResponse().equals("article en repture de stock")) {
                    Toast.makeText(DetailsArticle.this, "article en repture de stock", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(DetailsArticle.this, "faill", Toast.LENGTH_SHORT).show();

            }
        });
    }




    @Override
    public void onItemClick(ArticleOccasionModel articleOccasionModel) {
        String pub_id = articleOccasionModel.getPub_id();
        String id_utilis = ut_id_text.getText().toString();
        Call<users> call = apiInterface.performDetails(pub_id);
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (response.body().getResponse().equals("ok")) {
                    String id1 = response.body().getPub_id();
                    String pub_title = response.body().getPub_title();
                    String pub_image = response.body().getPub_image();
                    String prix = response.body().getPrix();
                    String descriptionn = response.body().getDescriptionn();
                    Intent intent = new Intent(DetailsArticle.this, DetailsArticle.class);
                    intent.putExtra("pubb_title", pub_title);
                    intent.putExtra("pubb_id", id1);
                    intent.putExtra("pub_image", pub_image);
                    intent.putExtra("prix", prix);
                    intent.putExtra("descriptionn", descriptionn);
                    intent.putExtra("ut_id", id_utilis);

                   // Toast.makeText(DetailsArticle.this, id_utilis, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

            panier = findViewById(R.id.panierr);
            acheter = findViewById(R.id.acheter);
        paiement = findViewById(R.id.paiement);
        lcom = findViewById(R.id.lcom);
        String id_utilis = ut_id_text.getText().toString();
        if(id_utilis.equals("")){
            lcom.setVisibility(View.GONE);
        }
            pubb_id_text = findViewById(R.id.pubb_id_text);
        Call<users> call=apiInterface.liza(pubb_id_text.getText().toString());
   call.enqueue(new Callback<users>() {
       @SuppressLint("SetTextI18n")
       @Override
       public void onResponse(Call<users> call, Response<users> response) {
           if(response.body().getResponse().equals("yes")){
               panier.setVisibility(View.GONE);
               acheter.setVisibility(View.GONE);
               paiement.setText("ShopWise Messagerie");
           }
       }

       @Override
       public void onFailure(Call<users> call, Throwable t) {

       }
   });
    }


}