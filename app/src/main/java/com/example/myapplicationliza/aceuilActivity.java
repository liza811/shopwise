package com.example.myapplicationliza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import soup.neumorphism.NeumorphImageButton;
import soup.neumorphism.NeumorphImageView;

public class aceuilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    private LinearLayout menuLayout;
    private NeumorphImageView menuButton;
    private TextView menuItem1, menuItem2, menuItem3;
   // NavigationView navigationView;
  //  DrawerLayout drawerLayout;
    DrawerLayout drawerLayout;
    ImageView navigationBar;
    NavigationView navigationView;
    private View view;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceuil);
        ///////////////////////////////////////////////////
       /* toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer_layout);
        ////////////////////////////////////////////////////
        setSupportActionBar(toolbar);
        //////////////////////////////////////////////////
        navigationView.bringChildToFront(navigationView);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);*/
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuLayout.getVisibility() == View.GONE) {
                    // Show the menu
                    menuLayout.setVisibility(View.VISIBLE);
                } else {
                    // Hide the menu
                    menuLayout.setVisibility(View.GONE);
                }
            }
        });
    }
    /////////////TO AVOID CLOSING APP ON BACK BUTTON////////
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_acceuil, container, false);
       // onSetNavigationDrawerEvent();
        return view;
    }
   /* private void onSetNavigationDrawerEvent() {
        drawerLayout= view.findViewById(R.id.drawer_layout);
        navigationView= view.findViewById(R.id.navigationView);
        navigationBar= (ImageView) view.findViewById(R.id.navigationBar);
        one= (TextView) view.findViewById(R.id.one);
        two= (TextView) view.findViewById(R.id.two);
        navigationBar.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
         }*/




    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

  /*  @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView,true);
                break;
            case R.id.one:
                Toast.makeText(aceuilActivity.this,"one",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView,true);
                break;
            case R.id.two:
                Toast.makeText(aceuilActivity.this,"two",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView,true);
                break;
        }

    }*/
}