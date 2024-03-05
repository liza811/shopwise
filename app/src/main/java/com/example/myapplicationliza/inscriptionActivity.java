package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
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

public class inscriptionActivity extends AppCompatActivity {

    TextView connectez;
    EditText emaill,motdepasse,nomutilisateur;
    NeumorphButton inscrire;
    public static ApiInterface apiInterface;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
     ///////////////////////////////////////////////////////////////////////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //////////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

      //////////////////////////////////////////////////////////////////////////////////////////////
        connectez= findViewById(R.id.connectez);
        SpannableString content = new SpannableString(connectez.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        connectez.setText(content);
        connectez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(inscriptionActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });

        init();
    }
     public void init(){

        emaill= findViewById(R.id.email);
        motdepasse= findViewById(R.id.mot_de_passe);
        nomutilisateur= findViewById(R.id.nom_utilisateur);
        inscrire= findViewById(R.id.inscrire);
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emaill.getText().toString().trim();
                String mot_de_passe=motdepasse.getText().toString().trim();
                String nom_utilisateur=nomutilisateur.getText().toString().trim();
                boolean containsSpecialChar = !mot_de_passe.matches("[A-Za-z0-9]*");
                boolean containsDigit = mot_de_passe.matches(".*\\d.*");

                if(TextUtils.isEmpty(email)){
                    emaill.setError("l'email est obligatoire ");
                } else if (TextUtils.isEmpty(mot_de_passe)) {
                    motdepasse.setError("le mot de passe est obligatoire ");

                } else if (TextUtils.isEmpty(nom_utilisateur)) {
                    nomutilisateur.setError("nom d'utilisateur est obligatoire ");

                }
                else if ( ! EmailValidator.isValidEmail(email)) {
                    emaill.setError("la forme de l'email est incorrecte");
                }else
                if (!containsSpecialChar || !containsDigit || mot_de_passe.length() < 8 || mot_de_passe.length() >20) {
                    motdepasse.setError("Le mot de passe doit contenir des lettres,chiffres et caractères");
                }
                else if (EmailValidator.isValidEmail(email) && containsSpecialChar && containsDigit && mot_de_passe.length() >= 8 && mot_de_passe.length() <=20) {

                    Call<users> call=apiInterface.performInscription(email,mot_de_passe,nom_utilisateur);
                    call.enqueue(new Callback<users>() {
                        @Override
                        public void onResponse(Call<users> call, Response<users> response) {
                            if(response.body().getResponse().equals("ok")){
                                Toast.makeText(inscriptionActivity.this,"Compte crée",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(inscriptionActivity.this,loginActivity.class);
                                startActivity(intent);
                                finish();

                            }else  if(response.body().getResponse().equals("failed!")){
                                Toast.makeText(inscriptionActivity.this,"account creation failed!",Toast.LENGTH_LONG).show();

                            }else  if(response.body().getResponse().equals("already!")){
                                Toast.makeText(inscriptionActivity.this,"Email exist déjà",Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<users> call, Throwable t) {

                        }
                    });
                }
                else {

                    //emaill.setError("la forme de l'email est incorrecte");
                }

            }
        });

     }
   /* public void choisirStatut(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.achteur:
                if (checked) {
                    vendeur.setChecked(false);

                }
                break;
            case R.id.vendeur:
                if (checked) {
                    achteur.setChecked(false);


                }
                break;
        }

    }*/
}