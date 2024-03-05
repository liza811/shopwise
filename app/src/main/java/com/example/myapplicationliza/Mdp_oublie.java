package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;


import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Mdp_oublie extends AppCompatActivity {
    AppCompatButton mrechercher, verify;
    EditText memail;
    private CountDownTimer countDownTimer;
    long remainingTimeMillis = 0;
    private boolean emailSent = false;
    TextView reenvoyer,  emaill,TimeLeftTextView;

    TextInputEditText code1, code2, code3, code4;
    LinearLayout t3;
    public static ApiInterface apiInterface=ApiClient.getApiClient().create(ApiInterface.class);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdp_oublie);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        mrechercher = findViewById(R.id.mrechercher);





        memail = findViewById(R.id.memail);



        mrechercher.setOnClickListener(view -> rechercher());
    }

    public void rechercher() {
        String mon_mail = memail.getText().toString().trim();
        if (TextUtils.isEmpty(mon_mail)) {
            memail.setError("l'email est obligatoire ");
        } else {


            Call<users> call = apiInterface.send_email(mon_mail);
            call.enqueue(new Callback<users>() {
                @SuppressLint({"WrongViewCast", "MissingInflatedId"})
                @Override
                public void onResponse(Call<users> call, Response<users> response) {
                    //  String s=response.body().getResponse() ;
                    if (response.body().getResponse().equals("null")) {
                        memail.setError("email n'existe pas");

                        //Toast.makeText(inscriptionActivity.this,"account created",Toast.LENGTH_SHORT).show();

                    } else {
                        String codee = response.body().getResponse();
                        setContentView(R.layout.code_verification);
                        reenvoyer =  findViewById(R.id.reenvoyer);
                        // ################" underline #################################"""
                        SpannableString content = new SpannableString(reenvoyer.getText());
                        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                        reenvoyer.setText(content);
                        //##################################################################"
                        reenvoyer.setOnClickListener(view -> rechercher());
                        TimeLeftTextView=findViewById(R.id.TimeLeftTextView);
                        emailSent = true;
                        Call<users> calll = apiInterface.mail(mon_mail, codee);
//#########################################################################################################
                        countDownTimer = new CountDownTimer(31000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                long secondsLeft = millisUntilFinished / 1000;
                                // Update the TextView with the remaining time in seconds
                                TimeLeftTextView.setText("Resend in " + secondsLeft + " seconds");
                            }

                            public void onFinish() {
                                // When the timer finishes, make the resend button visible
                                TimeLeftTextView.setText("Didn't you receive any code?");
                                reenvoyer.setVisibility(View.VISIBLE);
                            }
                        };
                        countDownTimer.start();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                               // resendButton.setVisibility(View.VISIBLE);
                                emailSent = false;

                            }

                        }, 30000);


                        emaill =  findViewById(R.id.emaill);
                        verify =  findViewById(R.id.verify);

                        code1 = findViewById(R.id.code1);
                        code1.requestFocus();
                        code2 =findViewById(R.id.code2);
                        code3 =  findViewById(R.id.code3);
                        code4 =  findViewById(R.id.code4);
// Create TextWatchers for each EditText

                        TextWatcher textWatcher = new TextWatcher() {

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                // This method is not used, but it's part of the TextWatcher interface
                                code1.requestFocus();
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                // This method is not used, but it's part of the TextWatcher interface
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if (s == code1.getEditableText() && s.length() == 1) {
                                    code2.requestFocus();
                                } else if (s == code2.getEditableText() && s.length() == 1) {
                                    code3.requestFocus();
                                } else if (s == code3.getEditableText() && s.length() == 1) {
                                    code4.requestFocus();
                                }
                                // Check if all text fields have content, and enable/disable the button accordingly
                                boolean isCode1Empty = code1.getText().toString().isEmpty();
                                boolean isCode2Empty = code2.getText().toString().isEmpty();
                                boolean isCode3Empty = code3.getText().toString().isEmpty();
                                boolean isCode4Empty = code4.getText().toString().isEmpty();

                                boolean isAllFieldsNotEmpty = !isCode1Empty && !isCode2Empty && !isCode3Empty && !isCode4Empty;

                                verify.setEnabled(isAllFieldsNotEmpty);
                                if (isAllFieldsNotEmpty) {

                                    verify.setBackgroundResource(R.drawable.button_round);
                                    verify.setTextColor(ContextCompat.getColor(Mdp_oublie.this, R.color.white));
                                    verify.setOnClickListener(view -> {
                                        String concatenatedText = code1.getText().toString() +
                                                code2.getText().toString() +
                                                code3.getText().toString() +
                                                code4.getText().toString();
                                        if(emailSent){
                                        if (concatenatedText.equals(codee)) {
                                            Intent intent = new Intent(Mdp_oublie.this, Changer_mdp.class);
                                            intent.putExtra("mon_mail", mon_mail);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(Mdp_oublie.this, "incorrect code", Toast.LENGTH_SHORT).show();
                                        }}else{
                                            Toast.makeText(Mdp_oublie.this, "Code Expired", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {

                                    verify.setBackgroundResource(R.drawable.button_deseabled);
                                    verify.setTextColor(ContextCompat.getColor(Mdp_oublie.this, R.color.shadow_dark));
                                }

                            }
                        };
                        code1.addTextChangedListener(textWatcher);
                        code2.addTextChangedListener(textWatcher);
                        code3.addTextChangedListener(textWatcher);
                        code4.addTextChangedListener(textWatcher);
                        verify.setEnabled(false);
                        verify.setBackgroundResource(R.drawable.button_deseabled);
                        emaill.setText(mon_mail);





                        calll.enqueue(new Callback<users>() {

                            @Override
                            public void onResponse(Call<users> call, Response<users> response) {

                            }

                            @Override
                            public void onFailure(Call<users> call, Throwable t) {

                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {

                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


}
