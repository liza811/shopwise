package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.Adapters.CosmetiqueAdapter;
import com.example.myapplicationliza.Adapters.ElectroniqueAdapter;
import com.example.myapplicationliza.Adapters.ImmobilierAdapter;
import com.example.myapplicationliza.Adapters.VetementAdapter;
import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.example.myapplicationliza.Sessions.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphImageView;

public class VoirTout extends AppCompatActivity implements RecyclerViewInterface{
    private VetementAdapter vetementA;
    TextView textView4,aucune_recherche;
    SessionManager sessionManager;
    String iddd;
    private ImmobilierAdapter immobilierAdapter;
    NeumorphImageView exit5;
    CosmetiqueAdapter cosmetiqueA;
     ElectroniqueAdapter electroniqueAdapter, automobileAdapter, informatiqueAdapter, pieceAdapter;
    private List<ArticleOccasionModel> vetModel,articleModel, immobilierModel, electroniqueMdel, automobileModel, informatiqueModel, pieceModel;
    private RecyclerView RVVoirTout;
    String categorie;
    public static ApiInterface apiInterface;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_tout);
        categorie=getIntent().getStringExtra("categgorie");
        /////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager=new SessionManager(this);
       ////////////////////////////////////////////////////////////////////////
        //############################### exit  #################################""
        exit5 =findViewById(R.id.exit5);
        exit5.setOnClickListener(view -> finish());
        //###############################################################################
        textView4= findViewById(R.id.textView4);
        if(categorie.equals("search")){
            ///////////////////////////// search//////////////////////////////////////////////////////////////
            textView4.setVisibility(View.GONE);
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);
            String  text=getIntent().getStringExtra("text");
            vetModel=new ArrayList<>();
            Call<users> vetementCall =apiInterface.search(text);
            vetementCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> vetementCall, Response<users> response) {
                    vetModel=response.body().getSearch();
                    if (vetModel.size() >0) {
                        vetementA = new VetementAdapter(vetModel, VoirTout.this, VoirTout.this::onItemClick);
                        RVVoirTout.setAdapter(vetementA);
                        vetementA.notifyDataSetChanged();
                    }else {
                        aucune_recherche= findViewById(R.id.aucune_recherche);
                        aucune_recherche.setVisibility(View.VISIBLE);
                    }
                }
                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });}
       //##############################################################################################""

        if(categorie.equals("Articles d'occasion")){
            ///////////////////////////// rticles d'occasion//////////////////////////////////////////////////////////////
            textView4.setText("Articles d'occasion");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManagerr= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManagerr);

            vetModel=new ArrayList<>();
            Call<users> all =apiInterface.getArticle_occasion();
            all.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> all, Response<users> response) {
                    vetModel=response.body().getArticle_occasion();
                    vetementA=new VetementAdapter(vetModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(vetementA);
                    vetementA.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });}

          else  if(categorie.equals("Vetement")){
        ///////////////////////////// vetement//////////////////////////////////////////////////////////////
            textView4.setText("Vetement");
            RVVoirTout= findViewById(R.id.RVVoirTout);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
        RVVoirTout.setLayoutManager(gridLayoutManager);

            vetModel=new ArrayList<>();
            Call<users> vetementCall =apiInterface.getVetement();
            vetementCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> vetementCall, Response<users> response) {
                    vetModel=response.body().getArticle_vetement();
                    vetementA=new VetementAdapter(vetModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(vetementA);
                    vetementA.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });
        }else if(categorie.equals("Cosmetique & Beauté")){
            ///////////////////////////// Cosmetique //////////////////////////////////////////////////////////////
            textView4.setText("Cosmetique & Beauté");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);

            articleModel=new ArrayList<>();
            Call<users> vetementCall =apiInterface.getArticle();
            vetementCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> vetementCall, Response<users> response) {
                    articleModel=response.body().getArticle_cosmetique();
                    cosmetiqueA=new CosmetiqueAdapter(articleModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(cosmetiqueA);
                    cosmetiqueA.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });






        }else if(categorie.equals("Immobilier")){
            ///////////////////////////// Immobilier //////////////////////////////////////////////////////////////
            textView4.setText("Immobilier");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);

            immobilierModel=new ArrayList<>();
            Call<users> immobilieCall =apiInterface.getImmobilier();
            immobilieCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> immobilieCall, Response<users> response) {
                    immobilierModel=response.body().getArticle_imm();
                    immobilierAdapter=new ImmobilierAdapter(immobilierModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(immobilierAdapter);
                    immobilierAdapter.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });
        }
        else if(categorie.equals("Electronique & Electroménager")){
            ///////////////////////////// Electronique & Electroménager //////////////////////////////////////////////////////////////
            textView4.setText("Electronique & Electroménager");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);

            electroniqueMdel=new ArrayList<>();
            Call<users> electCall =apiInterface.getelectronique();
            electCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> vetementCall, Response<users> response) {
                    assert response.body() != null;
                    electroniqueMdel=response.body().getArticle_ele();
                    electroniqueAdapter=new ElectroniqueAdapter(electroniqueMdel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(electroniqueAdapter);
                    electroniqueAdapter.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });

        }
        else if(categorie.equals("Automobile")){
            ///////////////////////////// Automobile//////////////////////////////////////////////////////////////
            textView4.setText("Automobile");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);

            automobileModel=new ArrayList<>();
            Call<users> autoCall =apiInterface.getAutomobile();
            autoCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> autoCall, Response<users> response) {
                    automobileModel=response.body().getArticle_auto();
                    automobileAdapter=new ElectroniqueAdapter(automobileModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(automobileAdapter);
                    automobileAdapter.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });
        }
        else if(categorie.equals("Pièces détachées")){
            ///////////////////////////// pièces détachéesle//////////////////////////////////////////////////////////////
            textView4.setText("Pièces détachées");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);

            pieceModel=new ArrayList<>();
            Call<users> pieceCall =apiInterface.getPiece();
            pieceCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> autoCall, Response<users> response) {
                    pieceModel=response.body().getArticle_piece();
                    pieceAdapter=new ElectroniqueAdapter(pieceModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(pieceAdapter);
                    pieceAdapter.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });
        }
        else if(categorie.equals("Informatique")){
            ///////////////////////////// Informatique//////////////////////////////////////////////////////////////
            textView4.setText("Informatique");
            RVVoirTout= findViewById(R.id.RVVoirTout);

            GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
            RVVoirTout.setLayoutManager(gridLayoutManager);

            informatiqueModel=new ArrayList<>();
            Call<users> infoCall =apiInterface.getInformatique();
            infoCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> infoCall, Response<users> response) {
                    informatiqueModel=response.body().getArticle_info();
                    informatiqueAdapter=new ElectroniqueAdapter(informatiqueModel,VoirTout.this,VoirTout.this::onItemClick);
                    RVVoirTout.setAdapter(informatiqueAdapter);
                    informatiqueAdapter.notifyDataSetChanged();

                }



                @Override
                public void onFailure(Call<users> vetementCall, Throwable t) {
                    Toast.makeText(VoirTout.this, "faiiiiiiil", Toast.LENGTH_SHORT).show();

                }

            });
        }
    }



    @Override
    public void onItemClick(ArticleOccasionModel articleOccasionModel) {
       //12 Toast.makeText(this, "lizaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        String pub_id = articleOccasionModel.getPub_id();
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
                    Intent intent = new Intent(VoirTout.this,DetailsArticle.class);
                    intent.putExtra("pubb_title", pub_title);
                    intent.putExtra("pubb_id", id1);
                    intent.putExtra("pub_image", pub_image);
                    intent.putExtra("prix", prix);
                    intent.putExtra("descriptionn", descriptionn);
                    intent.putExtra("ut_id", iddd);


                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });

    }
    public void onStart() {
        super.onStart();
        if (sessionManager.isLogin()) {

            iddd=sessionManager.login();




        }
    }
}