package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class ParametreProfil extends AppCompatActivity {
    String id;
    public static ApiInterface apiInterface;
    TextView profil_id,pp_logo;
    NeumorphButton sauvgarder,pexit;
    EditText eemail,enom_utilisateur,emot_de_passe,num_tel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre_profil);
        id=getIntent().getStringExtra("id");

        /////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        ////////////////////////////////////////////////////////////////////////
        profil_id= findViewById(R.id.profil_id);

        pp_logo= findViewById(R.id.pp_logo);
        profil_id.setText(id);
        /////////////////////////////////////////////////////////////////////////////
        eemail= findViewById(R.id.eemail);
        enom_utilisateur= findViewById(R.id.enom_utilisateur);
        emot_de_passe= findViewById(R.id.emot_de_passe);
        num_tel= findViewById(R.id.num_tel);
        sauvgarder= findViewById(R.id.sauvgarder);
        /////////////////////////////////////////////////////////////////
       initt();

//////////////////////////////////// sauvgarder ////////////////////////////////////////
        sauvgarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String email=  eemail.getText().toString().trim();
              String nom_utilisateur=  enom_utilisateur.getText().toString().trim();
              String mot_de_passe=  emot_de_passe.getText().toString().trim();
              String num_tell=  num_tel.getText().toString().trim();
             if(! PhoneNumberValidator.isValidPhoneNumber(num_tell)) {
                num_tel.setError("Veillez entrer un numéro de téléphone valide");
            }else if(email.isEmpty()){
                 eemail.setError("Ce champ est obligatoire");
             }else if(nom_utilisateur.isEmpty()){
                 enom_utilisateur.setError("Ce champ est obligatoire");
             }else if(mot_de_passe.isEmpty()){
                 emot_de_passe.setError("Ce champ est obligatoire");
             }else{
              //########################################################################
                Call<users> call = apiInterface.sauvgarder(id,email,nom_utilisateur,mot_de_passe,num_tell);
                Toast.makeText(ParametreProfil.this, "changements sauvgardés", Toast.LENGTH_SHORT).show();
                call.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        initt();

                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {

                    }
                });

            }}
        });
    }

    private void initt() {
        Call<users> calll = apiInterface.profile(id);
        calll.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(response.body().getResponse().equals("ok")){


                    eemail.setText(response.body().getEmail());
                    enom_utilisateur.setText(response.body().getNom_utilisateur());
                    emot_de_passe.setText(response.body().getMot_de_passe());
                    pp_logo.setText(response.body().getNom_utilisateur().substring(0,1));
                if(response.body().getNum_tel().equals("null")){
                    num_tel.setText("");
                }else{
                    num_tel.setText(response.body().getNum_tel());
                }


                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(ParametreProfil.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}