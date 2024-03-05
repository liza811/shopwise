package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationliza.Adapters.ChatAdapter;
import com.example.myapplicationliza.Models.ChatMessage;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Discussion extends AppCompatActivity {
    private RecyclerView mChatRecyclerView;
    private ChatAdapter mChatAdapter;

    private List<ChatMessage> mChatMessages;
    ImageView dexit;
    EditText messagee;
    ImageView sendd;
    String id,nom;
    TextView nomm;

    public static ApiInterface apiInterface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        messagee= findViewById(R.id.messagee);
        dexit= findViewById(R.id.dexit);
        dexit.setOnClickListener(view -> {
            Intent intent=new Intent(Discussion.this,Messagerie.class);
            startActivity(intent);
            finish();
        });
        //////////////////////////////////////////////////////////////


        sendd= findViewById(R.id.sendd);
        //////////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        sendd.setOnClickListener(view -> {
            mChatRecyclerView.setAdapter(mChatAdapter);
            String m=messagee.getText().toString().trim();
            String p= getIntent().getStringExtra("nom");
            String s= getIntent().getStringExtra("id");
            Call<users> call = apiInterface.inser_msg2(s,p,m);
            messagee.setText("");
            restartActivity();
            call.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {

                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {

                }
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
          id=getIntent().getStringExtra("id");
          nom=getIntent().getStringExtra("nom");
          mChatRecyclerView= findViewById(R.id.RVMessagee);
          nomm= findViewById(R.id.nom);
          nomm.setText(nom);
        LinearLayoutManager linearLayoutManagerCosmetique = new LinearLayoutManager(this);
        linearLayoutManagerCosmetique.setOrientation(RecyclerView.VERTICAL);
        mChatRecyclerView.setLayoutManager(linearLayoutManagerCosmetique);
        mChatMessages = new ArrayList<>();


        Call<users> cosmetiqueCall = apiInterface.discussion(id, nom);
        cosmetiqueCall.enqueue(new Callback<users>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                mChatMessages = response.body().getMsg();
                if (mChatMessages.size() >0) {
                    mChatAdapter = new ChatAdapter(mChatMessages);
                    mChatRecyclerView.setAdapter(mChatAdapter);
                    mChatAdapter.notifyDataSetChanged();
                }else{}
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
    }
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}