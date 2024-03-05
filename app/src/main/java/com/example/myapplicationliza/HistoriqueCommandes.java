package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.myapplicationliza.Adapters.ImmobilierAdapter;
import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphImageView;

public class HistoriqueCommandes extends AppCompatActivity implements RecyclerViewInterface{

    String id;
    RecyclerView RVHistoriqueCommande;
    private ImmobilierAdapter immobilierAdapter;
    private List<ArticleOccasionModel> hisModel;
    NeumorphImageView cexit;

    public static ApiInterface apiInterface;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_commandes);
        id=getIntent().getStringExtra("id");
        cexit= findViewById(R.id.cexit);
        cexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        ////////////////////////////////////////////////////////////////////////
        RVHistoriqueCommande= findViewById(R.id.RVHistoriqueCommande);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
        RVHistoriqueCommande.setLayoutManager(gridLayoutManager);
        hisModel=new ArrayList<>();
        Call<users> immobilieCall =apiInterface.historique_commandes(id);
        immobilieCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                hisModel=response.body().getHistorique_commande();
                immobilierAdapter=new ImmobilierAdapter(hisModel,HistoriqueCommandes.this,HistoriqueCommandes.this::onItemClick);
                RVHistoriqueCommande.setAdapter(immobilierAdapter);
                immobilierAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
    }
    @Override
    public void onItemClick(ArticleOccasionModel articleOccasionModel) {

    }
}