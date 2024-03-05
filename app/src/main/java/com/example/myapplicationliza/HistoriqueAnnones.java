package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageView;

public class HistoriqueAnnones extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView RVHistorique;
    NeumorphImageView aexit;
    NeumorphButton repture_stock,stock;
    private ImmobilierAdapter immobilierAdapter;
    private List<ArticleOccasionModel> hisModel;
    String id;
    public static ApiInterface apiInterface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_annones);
        id=getIntent().getStringExtra("id");
      //  Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        /////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        ////////////////////////////////////////////////////////////////////////
        RVHistorique= findViewById(R.id.RVHistorique);
        repture_stock= findViewById(R.id.repture_stock);
        aexit= findViewById(R.id.aexit);
        aexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        stock= findViewById(R.id.stock);
        repture_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RVHistorique= findViewById(R.id.RVHistorique);
                GridLayoutManager gridLayoutManager= new GridLayoutManager(HistoriqueAnnones.this,2);
                RVHistorique.setLayoutManager(gridLayoutManager);
                hisModel=new ArrayList<>();

                Call<users> immobilieCall =apiInterface.historique_annonce1(id);

                immobilieCall.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        hisModel=response.body().getHistorique_annonce1();
                        immobilierAdapter=new ImmobilierAdapter(hisModel,HistoriqueAnnones.this,HistoriqueAnnones.this::onItemClick);
                        RVHistorique.setAdapter(immobilierAdapter);
                        immobilierAdapter.notifyDataSetChanged();
                        repture_stock.setShapeType(1);
                        stock.setShapeType(0);
                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {
                        Toast.makeText(HistoriqueAnnones.this, t.toString(), Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
        //#######################################################################
        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStart();
                repture_stock.setShapeType(0);
                stock.setShapeType(1);
            }
        });

    }

    @Override
    public void onItemClick(ArticleOccasionModel articleOccasionModel) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        RVHistorique= findViewById(R.id.RVHistorique);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
        RVHistorique.setLayoutManager(gridLayoutManager);
        hisModel=new ArrayList<>();

        Call<users> immobilieCall =apiInterface.historique_annonce(id);

        immobilieCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                hisModel=response.body().getHistorique_annonce();
                immobilierAdapter=new ImmobilierAdapter(hisModel,HistoriqueAnnones.this,HistoriqueAnnones.this::onItemClick);
                RVHistorique.setAdapter(immobilierAdapter);
                immobilierAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(HistoriqueAnnones.this, t.toString(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}