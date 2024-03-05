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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplicationliza.Adapters.ChatAdapter;
import com.example.myapplicationliza.Adapters.CosmetiqueAdapter;
import com.example.myapplicationliza.Models.ChatMessage;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message extends AppCompatActivity {
    TextView dateTime,mPubtitle,mPub_id,mut_id,nom,nomlogo;
    ImageView mImage,send;
    private RecyclerView mChatRecyclerView;
    private ChatAdapter mChatAdapter;
    private List<ChatMessage> mChatMessages;
    EditText message;
    public static ApiInterface apiInterface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message= findViewById(R.id.message);
        //////////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        send= findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPub_id= findViewById(R.id.mPub_id);
                mut_id= findViewById(R.id.mut_id);
                mChatRecyclerView= findViewById(R.id.RVMessage);
               mChatRecyclerView.setAdapter(mChatAdapter);
               String m=message.getText().toString().trim();
               String p= mPub_id.getText().toString();
               String s= mut_id.getText().toString();

                Call<users> call = apiInterface.insert_msg(s,p,m);
                //mChatAdapter.notifyDataSetChanged();

             /*   mChatMessages=new ArrayList<>();
                mChatMessages.add(new ChatMessage(m,"12/03/2022",s,p,s));
                mChatAdapter = new ChatAdapter(mChatMessages);
                mChatRecyclerView.setAdapter(mChatAdapter);
                mChatAdapter.notifyDataSetChanged();*/
               // onStart();
                restartActivity();

                call.enqueue(new Callback<users>() {

                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                     //   mChatAdapter = new ChatAdapter(m);



                        //Toast.makeText(Message.this, "liza", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {

                    }
                });

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        dateTime= findViewById(R.id.dateTime);
        mPubtitle= findViewById(R.id.mPubtitle);
        mPub_id= findViewById(R.id.mPub_id);
        mut_id= findViewById(R.id.mut_id);
        nom= findViewById(R.id.nom);
        nomlogo= findViewById(R.id.nomlogo);
        mImage= findViewById(R.id.mImage);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now= LocalDateTime.now();
            dateTime.setText(dtf.format(now));

            String pub_image=getIntent().getStringExtra("pub_image");
            String pubb_title=getIntent().getStringExtra("pubb_title");
            String pubb_id=getIntent().getStringExtra("pubb_id");
            String ut_id=getIntent().getStringExtra("ut_id");
            mPubtitle.setText(pubb_title);
            mPub_id.setText(pubb_id);
            mut_id.setText(ut_id);
            Glide.with(this).load(pub_image).into(mImage);

            //#####################################################################"
            Call<users> calll = apiInterface.chercher_utilisateur(pubb_id);
            calll.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {
                  String   nomm =response.body().getId();
                    nom.setText(nomm);
                    nomlogo.setText(nomm.substring(0,1));
                    if(nomm.charAt(0) == 'l') {
                        nomlogo.setBackgroundResource(R.drawable.profile_logo1);



                    }else  if(nomm.charAt(0) == 'L') {
                        nomlogo.setBackgroundResource(R.drawable.round);

                    }else  if(nomm.charAt(0) == 'N') {
                        nomlogo.setBackgroundResource(R.drawable.profile_logo2);

                    }else  if(nomm.charAt(0) == 'R') {
                        nomlogo.setBackgroundResource(R.drawable.profile_logo3);

                    }else{
                        nomlogo.setBackgroundResource(R.drawable.profile_logo4);
                    }

                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {

                }
            });
           //#############################################################################""""

            mChatRecyclerView= findViewById(R.id.RVMessage);
            LinearLayoutManager linearLayoutManagerCosmetique = new LinearLayoutManager(this);
            linearLayoutManagerCosmetique.setOrientation(RecyclerView.VERTICAL);
            mChatRecyclerView.setLayoutManager(linearLayoutManagerCosmetique);
            mChatMessages = new ArrayList<>();


            Call<users> cosmetiqueCall = apiInterface.afficher_msg(mut_id.getText().toString(), mPub_id.getText().toString());
            cosmetiqueCall.enqueue(new Callback<users>() {
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
    }
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


}