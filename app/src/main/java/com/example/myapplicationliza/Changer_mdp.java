package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Changer_mdp extends AppCompatActivity {
    TextInputEditText mot_de_passe_change;
    Button btn;
    public static ApiInterface apiInterface;
    boolean isPasswordVisible = false;
    RadioButton radioButtonMinLength,radioButtonSpecialChar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_mdp);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        mot_de_passe_change= findViewById(R.id.mot_de_passe_change);
        radioButtonMinLength= findViewById(R.id.radioButtonMinLength);
        radioButtonSpecialChar= findViewById(R.id.radioButtonSpecialChar);
        btn= findViewById(R.id.btn);
        mot_de_passe_change.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = charSequence.toString();
                boolean containsSpace = password.contains(" ");
                if(containsSpace || password.isEmpty()){
                    mot_de_passe_change.setError("espace interdit");
                    btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.light_background));
                    btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.shadow_dark));
                }
                else {

                    // Vérification de la longueur du mot de passe
                    radioButtonMinLength.setChecked(password.length() >= 8 && password.length() <= 20);

                    // Vérification de la présence d'un caractère spécial et d'un chiffre
                    boolean containsSpecialChar = !password.matches("[A-Za-z0-9]*");
                    boolean containsDigit = password.matches(".*\\d.*");


                    radioButtonSpecialChar.setChecked(containsSpecialChar && containsDigit);
                    boolean isMinLengthChecked = radioButtonMinLength.isChecked();
                    boolean isSpecialCharChecked = radioButtonSpecialChar.isChecked();

                    // Mettre à jour l'état du bouton en fonction des vérifications
                    if (isMinLengthChecked && isSpecialCharChecked && !containsSpace) {
                        btn.setEnabled(true);
                        btn.setClickable(true);
                        btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.primary));
                        btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.white));
                        //######################################################################""""
                        String s= getIntent().getStringExtra("mon_mail");

                    } else {
                        btn.setEnabled(false);
                        btn.setClickable(false);
                        btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.light_background));
                        btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.shadow_dark));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                boolean containsSpace = password.contains(" ");
                // Si le mot de passe est vide, décochez les deux boutons radio
                if (password.isEmpty() ) {
                    radioButtonMinLength.setChecked(false);
                    radioButtonSpecialChar.setChecked(false);
                    btn.setClickable(false);
                    btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.light_background));
                    btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.shadow_dark));
                }else if(containsSpace ){
                    btn.setClickable(false);
                   // mot_de_passe_change.setError("espace interdit");
                    btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.light_background));
                    btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.shadow_dark));
                }
                else {
                    // Vérification de la longueur du mot de passe
                    radioButtonMinLength.setChecked(password.length() >= 8 && password.length() <= 20);

                    // Vérification de la présence d'un caractère spécial et d'un chiffre
                    boolean containsSpecialChar = !password.matches("[A-Za-z0-9]*");
                    boolean containsDigit = password.matches(".*\\d.*");

                    radioButtonSpecialChar.setChecked(containsSpecialChar && containsDigit);
                }
                boolean isMinLengthChecked = radioButtonMinLength.isChecked();
                boolean isSpecialCharChecked = radioButtonSpecialChar.isChecked();

                // Mettre à jour l'état du bouton en fonction des vérifications
                if (isMinLengthChecked && isSpecialCharChecked && !containsSpace) {
                    btn.setEnabled(true);
                    btn.setClickable(true);
                   // btn.setBackgroundColor(0x765438);
                    btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.primary));
                    btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.white));
                } else {
                    btn.setEnabled(false);
                    btn.setClickable(false);
                    btn.setBackgroundColor(ContextCompat.getColor(Changer_mdp.this, R.color.light_background));
                    btn.setTextColor(ContextCompat.getColor(Changer_mdp.this, R.color.shadow_dark));
                }
                }
        });

//##########################################################################################################################""



        btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //######################################################################""""
        String s= getIntent().getStringExtra("mon_mail");
        Call<users> calll = apiInterface.changer_mdp(s,mot_de_passe_change.getText().toString());
      //  Toast.makeText(Changer_mdp.this, "Mot de passe reinitialiser", Toast.LENGTH_SHORT).show();

            Dialog dialog=new Dialog(Changer_mdp.this,R.style.CustomDialogTheme);
            dialog.setContentView(R.layout.password_changed);
            dialog.show();
        //////////################""" Done  ###########################

        TextView done=dialog.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Changer_mdp.this,loginActivity.class);
                 startActivity(intent);
                finish();

             }


        });
        //////////################""" dismiss ###########################
        TextView dismiss=dialog.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        calll.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
    }
});
    }
}