package com.example.myapplicationliza;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;





import android.annotation.SuppressLint;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;

import com.example.myapplicationliza.Sessions.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;


import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphTextView;

public class acceuilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,RecyclerViewInterface{
 BottomNavigationView bnv;
 String idd;
 SessionManager sessionManager;
 FrameLayout frameLayout;
    public static ApiInterface apiInterface;

    String  id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        /////HIDE STATUS BAR/////////////////////
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        /////////////////////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);
      String iddd=sessionManager.login();
   //    Toast.makeText(this, iddd, Toast.LENGTH_SHORT).show();
       // initt();
        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new AcceuilFragment()).commit();





        frameLayout=findViewById(R.id.framelayout);
        bnv= findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(navigation);
        ///////////////REPLACING BY DEFAULT FRAGMENT////////////////////////
        id=getIntent().getStringExtra("id_utilisateur");
        //Toast.makeText(acceuilActivity.this, id, Toast.LENGTH_SHORT).show();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new AcceuilFragment()).commit();




    }

    private void initt() {
        Fragment selectedFragment=null;

        selectedFragment=new AcceuilFragment();
        id=getIntent().getStringExtra("id_utilisateur");
        //Toast.makeText(acceuilActivity.this, id, Toast.LENGTH_SHORT).show();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        selectedFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();

    }

    private String init(String id) {
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        return id;
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigation=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("MissingInflatedId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;
                    switch (item.getItemId()){
                        case R.id.acceuil:
                            selectedFragment=new AcceuilFragment();
                            id=getIntent().getStringExtra("id_utilisateur");
                            //Toast.makeText(acceuilActivity.this, id, Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("id",id);
                            selectedFragment.setArguments(bundle);

                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                            break;
                        case R.id.categorie:
                          //  Toast.makeText(acceuilActivity.this, id, Toast.LENGTH_SHORT).show();
                            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(acceuilActivity.this, R.style.CustomBottomSheetDialogThemee);
                            View bottomSheetView = bottomSheetDialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet);

                            //  corner radius
                            if (bottomSheetView != null) {
                                bottomSheetView.setBackgroundResource(R.drawable.custom_bottom_sheet_background);
                            }
                            View sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout, findViewById(R.id.bss));
                            sheetView.findViewById(R.id.info_bm).setOnClickListener(view -> {
                                Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                intent.putExtra("categgorie","Informatique");
                                startActivity(intent);
                                bottomSheetDialog.dismiss();



                            });
                            sheetView.findViewById(R.id.auto_bm).setOnClickListener(view -> {
                                Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                intent.putExtra("categgorie","Automobile");
                                startActivity(intent);
                                bottomSheetDialog.dismiss();



                            });
                            sheetView.findViewById(R.id.piece_bm).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                    intent.putExtra("categgorie","Pièces détachées");
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();



                                }
                            });
                            sheetView.findViewById(R.id.ele_bm).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                    intent.putExtra("categgorie","Electronique & Electroménager");
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();



                                }
                            });
                            sheetView.findViewById(R.id.imm_bm).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                    intent.putExtra("categgorie","Immobilier");
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();



                                }
                            });
                            sheetView.findViewById(R.id.vete_bm).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                    intent.putExtra("categgorie","Vetement");
                                    startActivity(intent);
                                    bottomSheetDialog.dismiss();



                                }
                            });


                           sheetView.findViewById(R.id.beaute).setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                  // Toast.makeText(acceuilActivity.this,"beg",Toast.LENGTH_SHORT).show();
                                   ///////////////////////////// Cosmetique //////////////////////////////////////////////////////////////

                                   Intent intent= new Intent(acceuilActivity.this,VoirTout.class);
                                   intent.putExtra("categgorie","Cosmetique & Beauté");
                                   startActivity(intent);
                                   bottomSheetDialog.dismiss();
                               }
                           });
                           bottomSheetDialog.setContentView(sheetView);
                           bottomSheetDialog.show();
                            break;

                        case R.id.compte:
                            if (sessionManager.isLogin()) {
                                selectedFragment = new MoiFragment();

                                id = getIntent().getStringExtra("id_utilisateur");
                                Bundle bundlee = new Bundle();
                                bundlee.putString("id", id);
                                selectedFragment.setArguments(bundlee);

                                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                            } else{
                                Dialog dialog=new Dialog(acceuilActivity.this,R.style.CustomDialogTheme);
                                dialog.setContentView(R.layout.action);
                                dialog.show();
                                //////////################""" CONTINUER  ###########################

                                TextView confirmer=dialog.findViewById(R.id.continuer);
                                confirmer.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent =new Intent(acceuilActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        dialog.dismiss();

                                    }


                                });
                                //////////################""" annuler ###########################
                                TextView neumorphButton_annuler=dialog.findViewById(R.id.annulerr);
                                neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        dialog.dismiss();
                                    }
                                });


                            }
                            break;
                        case R.id.ajouter:



                            if (sessionManager.isLogin()) {
                                idd=getIntent().getStringExtra("id_utilisateur");

                                selectedFragment=new CompteFragment();
                                Bundle bundleeee = new Bundle();
                                bundleeee.putString("id", idd);
                                selectedFragment.setArguments(bundleeee);

                                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();

                            }else{
                                Dialog dialog=new Dialog(acceuilActivity.this,R.style.CustomDialogTheme);
                                dialog.setContentView(R.layout.action);
                                dialog.show();
                                //////////################""" CONTINUER  ###########################

                                TextView confirmer=dialog.findViewById(R.id.continuer);
                                confirmer.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent =new Intent(acceuilActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        dialog.dismiss();

                                    }


                                });
                                //////////################""" annuler ###########################
                                TextView neumorphButton_annuler=dialog.findViewById(R.id.annulerr);
                                neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        dialog.dismiss();
                                    }
                                });


                            }
                            break;
                        case R.id.panier:
                            if (sessionManager.isLogin()) {
                                selectedFragment = new PanierFragment();


                                id = getIntent().getStringExtra("id_utilisateur");
                                Bundle bundleee = new Bundle();
                                bundleee.putString("id", id);

                                selectedFragment.setArguments(bundleee);

                                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                            }else{

                                    Dialog dialog=new Dialog(acceuilActivity.this, R.style.CustomDialogTheme);
                                    dialog.setContentView(R.layout.action);
                                    dialog.show();
                                    //////////################""" CONTINUER  ###########################

                                TextView confirmer=dialog.findViewById(R.id.continuer);
                                    confirmer.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent =new Intent(acceuilActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            dialog.dismiss();

                                        }


                                    });
                                    //////////################""" annuler ###########################
                                TextView neumorphButton_annuler=dialog.findViewById(R.id.annulerr);
                                    neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            dialog.dismiss();
                                        }
                                    });
                            }
                            break;
                    }

                    return true;
                }
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }


    @Override
    public void onItemClick(ArticleOccasionModel articleOccasionModel) {

    }
}