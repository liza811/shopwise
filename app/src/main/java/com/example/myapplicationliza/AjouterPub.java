package com.example.myapplicationliza;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Console;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphImageView;

public class AjouterPub extends AppCompatActivity {

    String[] items={"Cosmetique & Beauté","Vetement","Immobilier","Electronique & Electroménager","Automobile","Pièces détachées","Informatique"};
    AutoCompleteTextView autoCompleteTextView;

    String id;

    SwitchCompat swither;
    ArrayAdapter<String> adapterItems;
    LinearLayout ajouter_pub;
    String path;
    Button publie;
    String item="";
    TextView id_utilisateur;
    Bitmap  bitmap;
    TextInputEditText pub_titlee,prixx,quantitee,description;
    private final int GALLERY_REQ_CODE=1000;
    public static ApiInterface apiInterface;
    ImageView pub_image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ajouter_pub);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        id=getIntent().getStringExtra("id_utilisateur");
       // Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        pub_image= findViewById(R.id.pub_image);
        ajouter_pub= findViewById(R.id.ajouter_pub);
        id_utilisateur= findViewById(R.id.id_utilisateurr);
        id_utilisateur.setText(id);
     /////////////////////////////////////////////////////////////////////////////////


//////////////////// ajouter image apartir du gallery///////////////////////////////////////

        ajouter_pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery=new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 startActivityForResult(iGallery,GALLERY_REQ_CODE);

            }
        });








        /////////////////// combo box  categorie////////////////////////////////////////////////////////
        autoCompleteTextView= findViewById(R.id.autoComplete);

        adapterItems =new ArrayAdapter<String>(this,R.layout.list_items,items);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  Toast.makeText(AjouterPub.this, autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                item=autoCompleteTextView.getText().toString();
                item=items[i];
            }
        });
    /////////////////////////// switcher////////////////////////////////////////////////////////////////
    swither= findViewById(R.id.swither);
        ///////////////////////////////////////////////////////////////////////////////////

        publie= findViewById(R.id.publie);
        publie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                id_utilisateur= findViewById(R.id.id_utilisateurr);
                pub_titlee= findViewById(R.id.pub_title);

                prixx= findViewById(R.id.prix);
                quantitee= findViewById(R.id.quantite);
                description= findViewById(R.id.description);



                uploadImageToServer();

              //######################################################"
              /*  Call<users> call=apiInterface.performpub(pub_title,prix,statut,categorie,quantiteee,descriptionn,id);
                call.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        if(response.body().getResponsee().equals("ok")){
                            Toast.makeText(AjouterPub.this, "inserted", Toast.LENGTH_SHORT).show();
                        }else if(response.body().getResponsee().equals("failed!")){
                            Toast.makeText(AjouterPub.this, "failed", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {
                        Toast.makeText(AjouterPub.this,t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })*/


            }
        });






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                Uri uri = data.getData();

                    Context context = AjouterPub.this;
                    path = RealPathUtil.getRealPath(context, uri);

                        bitmap = BitmapFactory.decodeFile(path);

                            pub_image.setImageBitmap(bitmap);



                }
            }
        }


    public void uploadImageToServer() {
        // Toast.makeText(AjouterPub.this, item, Toast.LENGTH_SHORT).show();
        String statut = "";
        if (swither.isChecked()) {
            statut = "occasion";

        } else {
            statut = "neuf";
        }
        String pub_title = pub_titlee.getText().toString();
        if(pub_title.isEmpty()){
            pub_titlee.setError("Ce champ est obligatoire");
        }else if(prixx.getText().toString().isEmpty()){
            prixx.setError("Ce champ est obligatoire");
        }
        else if(item.equals("")){
            Toast.makeText(this, "Vous devrez choisir la catégorie", Toast.LENGTH_SHORT).show();
        }else if(quantitee.getText().toString().isEmpty()){
            quantitee.setError("Ce champ est obligatoire");
        }else if(description.getText().toString().isEmpty()){
            description.setError("Ce champ est obligatoire");
        }else {
            String id = id_utilisateur.getText().toString();

            // Toast.makeText(AjouterPub.this, pub_title, Toast.LENGTH_SHORT).show();
            //###############    prix #########################//
            String prix = prixx.getText().toString();
            //Toast.makeText(AjouterPub.this, prix, Toast.LENGTH_SHORT).show();
            //###############    quantité #########################//
            String quantiteee = quantitee.getText().toString();

            // Toast.makeText(AjouterPub.this, quantiteee, Toast.LENGTH_SHORT).show();
            //Toast.makeText(AjouterPub.this, statut, Toast.LENGTH_SHORT).show();
            String categorie = item;

            //###########################################################################"
            // Toast.makeText(AjouterPub.this, item, Toast.LENGTH_SHORT).show();
            String descriptionn = description.getText().toString();
            //################################################""

            File file = new File(Uri.parse(path).getPath());

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("sendimage", file.getName(), requestBody);
            ApiInterface service = ApiClient.getApiClient().create(ApiInterface.class);
            Call<users> call = service.callUploadApi(filePart, pub_title, prix, statut, categorie, quantiteee, descriptionn, id);
            call.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {
                    users fileModel = response.body();
                    description.setText("");
                    prixx.setText("");
                    pub_titlee.setText("");
                    quantitee.setText("");
                    pub_image.setImageBitmap(null);


                    Toast.makeText(AjouterPub.this, fileModel.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {
                    Toast.makeText(AjouterPub.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }




}
