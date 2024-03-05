package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.Adapters.CommentAdapter;
import com.example.myapplicationliza.Adapters.MessagerieAdapter;
import com.example.myapplicationliza.Models.CommentModel;
import com.example.myapplicationliza.Models.MessagerieModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphImageView;

public class Messagerie extends AppCompatActivity implements RcDiscussion{
    String id;
    RecyclerView RVMessagerie;
    private List<MessagerieModel> commentModel;
    NeumorphImageView neumorphImageView;
    private MessagerieAdapter CommentAdapter;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagerie);
        id=getIntent().getStringExtra("id");
        neumorphImageView= findViewById(R.id.neumorphImageView);
        neumorphImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        RVMessagerie = findViewById(R.id.RVMessagerie);
        LinearLayoutManager linearLayoutManagerCosmetique = new LinearLayoutManager(this);
        linearLayoutManagerCosmetique.setOrientation(RecyclerView.VERTICAL);
        RVMessagerie.setLayoutManager(linearLayoutManagerCosmetique);
        commentModel = new ArrayList<>();
        Call<users> call = apiInterface.messagerie(id);
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                commentModel = response.body().getMsgg();
                CommentAdapter = new MessagerieAdapter(commentModel, Messagerie.this,Messagerie.this::onItemClickk);
                RVMessagerie.setAdapter(CommentAdapter);
                CommentAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClickk(MessagerieModel messagerieModel) {
        String nom = messagerieModel.getNom();
        Intent intenntt = new Intent(Messagerie.this, Discussion.class);
        intenntt.putExtra("nom", nom);
        intenntt.putExtra("id", id);
        startActivity(intenntt);
    }
}