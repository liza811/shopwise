package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

//import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import com.example.myapplicationliza.Sessions.SessionManager;

import soup.neumorphism.NeumorphButton;

public class MainActivity extends AppCompatActivity {
    NeumorphButton connecter;
    NeumorphButton skip;
    SessionManager sessionManager;

    NeumorphButton inscription;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        sessionManager=new SessionManager(this);
 ////////////////Connecter//////////////////////////
        connecter=findViewById(R.id.connecterbtn);
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,loginActivity.class);
                startActivity(intent);
               // Animatoo.animateSlideDown(MainActivity.this);
            }
        });
        ////////////////Inscription//////////////////////////
        inscription=findViewById(R.id.inscriptionbtn);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,inscriptionActivity.class);
                startActivity(intent);
                // Animatoo.animateSlideDown(MainActivity.this);
            }
        });
        ////////////////skipp//////////////////////////
        skip=findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,acceuilActivity.class);
                startActivity(intent);
                finish();
                // Animatoo.animateSlideDown(MainActivity.this);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(sessionManager.isLogin()){
            Intent intent=new Intent(MainActivity.this,acceuilActivity.class);
            startActivity(intent);
            finish();
        }
    }
}