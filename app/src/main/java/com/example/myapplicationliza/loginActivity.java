package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.example.myapplicationliza.Sessions.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;

public class loginActivity extends AppCompatActivity {
    NeumorphButton connecter;
    private CountDownTimer countDownTimer;
    TextView inscriezz,mdp_oblie,textView8;
    EditText emaill,motdepasse;
    public static ApiInterface apiInterface;
    SessionManager sessionManager;
    private int loginAttempts = 0;
    private long suspensionEndTime = 0;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final long SUSPENSION_DURATION = 30000;

    public static SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //////////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        //////////////////////////////////////////////////////////////////////////////
        sessionManager=new SessionManager(this);
        sharedPreferences=getSharedPreferences("MODE_PRIVATE",0);
        ///////////////////////////////////////////////////////////////////////////////////

        inscriezz= findViewById(R.id.inscriezz);
        SpannableString content = new SpannableString(inscriezz.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        inscriezz.setText(content);
        mdp_oblie= findViewById(R.id.mdp_oblie);
        mdp_oblie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,Mdp_oublie.class);
                startActivity(intent);
            }
        });
        inscriezz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,inscriptionActivity.class);
                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////
        init();
    }
    public void init(){
        emaill= findViewById(R.id.email);
        motdepasse= findViewById(R.id.mot_de_passe);
        connecter= findViewById(R.id.connecter);
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });

    }

    private void login() {

        textView8= findViewById(R.id.textView8);
        String email=emaill.getText().toString().trim();
        String mot_de_passe=motdepasse.getText().toString().trim();
        connecter= findViewById(R.id.connecter);

        if(TextUtils.isEmpty(email) ){
            emaill.setError("Ce champ est obligatoire ");




        }else if( TextUtils.isEmpty(mot_de_passe)){
            motdepasse.setError("Ce champ est obligatoire ");

        }
        else {

            Call<users> call= apiInterface.performLogin(email,mot_de_passe);
            call.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {
                    if(response.body().getResponse().equals("ok")){

                        final String id_utilisateur=response.body().getId();

                        sessionManager.createSession(id_utilisateur);
                        sharedPreferences.edit().putString("id",id_utilisateur).apply();

                        // Toast.makeText(loginActivity.this, sharedPreferences.getString(USER_ID,null), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(loginActivity.this,acceuilActivity.class);
                        intent.putExtra("id_utilisateur",id_utilisateur);
                        startActivity(intent);



                    }else  if(response.body().getResponse().equals("No")){

                        textView8.setVisibility(View.INVISIBLE);

                        // Login failed
                        loginAttempts++;

                        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                            textView8.setVisibility(View.INVISIBLE);
                            // User reached maximum login attempts, suspend
                            suspensionEndTime = System.currentTimeMillis() + SUSPENSION_DURATION;

                            // Disable the connect button
                            connecter.setEnabled(false);
                            textView8.setVisibility(View.VISIBLE);

                            connecter.setBackgroundColor(ContextCompat.getColor(loginActivity.this, R.color.dark_shadow));


//#########################################################################################################
                            countDownTimer = new CountDownTimer(31000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    long secondsLeft = millisUntilFinished / 1000;
                                    // Update the TextView with the remaining time in seconds
                                    textView8.setText("Veuillez reessayer après " + secondsLeft + " seconds");
                                }

                                public void onFinish() {
                                    // When the timer finishes, make the resend button visible
                                    textView8.setText("");

                                }
                            };
                            countDownTimer.start();


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Enable the connect button
                                    connecter.setEnabled(true);
                                    textView8.setVisibility(View.INVISIBLE);
                                    connecter.setBackgroundColor(ContextCompat.getColor(loginActivity.this, R.color.purple_200));

                                    // Reset login attempts and suspension end time
                                    loginAttempts = 0;
                                    suspensionEndTime = 0;


                                }
                            }, SUSPENSION_DURATION);
                        } else {

                            Toast.makeText(loginActivity.this, "Informations incorrects. Veuillez réessayer .", Toast.LENGTH_SHORT).show();
                            textView8.setVisibility(View.INVISIBLE);

                        }
                    }
                }





                @Override
                public void onFailure(Call<users> call, Throwable t) {
                    Toast.makeText(loginActivity.this,"server error",Toast.LENGTH_LONG).show();

                }
            });

        }
    }
}