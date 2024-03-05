package com.example.myapplicationliza;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import soup.neumorphism.NeumorphImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompteFragment() {
        // Required empty public constructor
    }
    private View view;
    String iddd;
    Button ajouterAnnonce;
    SessionManager sessionManager;
    public static ApiInterface apiInterface;
    NeumorphImageView exit;

    public static CompteFragment newInstance(String param1, String param2) {
        CompteFragment fragment = new CompteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_compte, container, false);
        ajouterAnnonce= view.findViewById(R.id.ajouterAnnonce);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager=new SessionManager(getContext());




        ajouterAnnonce.setOnClickListener(view -> {
            Call<users> call= apiInterface.profile(iddd);
            call.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {

                    Intent intent;
                    if(response.body().getNum_tel().equals("null")){
                        intent = new Intent(getContext(), continuer_inscription.class);
                    }else{
                        intent = new Intent(getContext(), AjouterPub.class);
                    }
                    intent.putExtra("id_utilisateur",iddd);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {

                }
            });





          //  getFragmentManager().beginTransaction().replace(R.id.framelayout,new AjouterAnnonce()).commit();
        });
       exit= view.findViewById(R.id.exit);
        exit.setOnClickListener(view -> {
           Intent intent=new Intent(getContext(),acceuilActivity.class);
           startActivity(intent);

        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (sessionManager.isLogin()) {

            iddd=sessionManager.login();




        }
    }
}