package com.example.myapplicationliza;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import soup.neumorphism.NeumorphImageView;

public class Contacter_nous extends AppCompatActivity {
    NeumorphImageView card1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacter_nous);
        card1= findViewById(R.id.cexit);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Contacter_nous.this,acceuilActivity.class);
                startActivity(intent);
            }
        });
    }
}