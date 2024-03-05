package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class continuer_inscription extends AppCompatActivity {
    NeumorphButton ajouterr;
    String id;
    TextInputEditText num_tel;
    public static ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuer_inscription);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        ajouterr= findViewById(R.id.ajouterr);
        num_tel= findViewById(R.id.num_tel);
        id=getIntent().getStringExtra("id_utilisateur");
        ajouterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num_t=num_tel.getText().toString();
                if(TextUtils.isEmpty(num_t) ){
                    num_tel.setError("Vous devrez remplir ce champ ");
                }else if(! PhoneNumberValidator.isValidPhoneNumber(num_t)) {
                    num_tel.setError("Veillez entrer un numéro de téléphone valide");
                }
                else{
                    Call<users> call= apiInterface.ajouter_num(id,num_t);
                    Toast.makeText(continuer_inscription.this, "Compte validée", Toast.LENGTH_SHORT).show();
                    num_tel.setText("");
                    Intent intent=new Intent(continuer_inscription.this,AjouterPub.class);
                    intent.putExtra("id_utilisateur",id);
                    startActivity(intent);

                    finish();
                    call.enqueue(new Callback<users>() {
                        @Override
                        public void onResponse(Call<users> call, Response<users> response) {


                        }

                        @Override
                        public void onFailure(Call<users> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}