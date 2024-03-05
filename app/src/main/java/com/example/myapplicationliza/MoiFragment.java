package com.example.myapplicationliza;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.example.myapplicationliza.Sessions.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoiFragment() {
        // Required empty public constructor
    }
    private View view;
    SessionManager sessionManager;
    String s;
    String iddd;
    TextView mon_id,fm_logo;
    RelativeLayout mes_annonces,mes_commandes,information_profil,Rdeconnecter,RMessagerie;
    public static ApiInterface apiInterface;
    TextView tnom_utilisateur,temail;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoiFragment newInstance(String param1, String param2) {
        MoiFragment fragment = new MoiFragment();
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
        view= inflater.inflate(R.layout.fragment_moi, container, false);
        /////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getContext());
        ////////////////////////////////////////////////////////////////////////
        //####################################################################"
        mon_id= view.findViewById(R.id.mon_idd);
        mon_id.setText(iddd);
        mes_annonces= view.findViewById(R.id.mes_annonces);
        mes_commandes= view.findViewById(R.id.mes_commandes);
        information_profil= view.findViewById(R.id.information_profil);
        Rdeconnecter= view.findViewById(R.id.Rdeconnecter);
        RMessagerie= view.findViewById(R.id.RMessagerie);

        //############################    BUNDLE       ############################""

       //##############################################################################
       init();
//###################################################################################""
        Rdeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.editor.clear();
                sessionManager.editor.commit();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        RMessagerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Messagerie.class);
                intent.putExtra("id",iddd);
                startActivity(intent);

            }
        });


        ///############################      information_profil  #####################################///////
        information_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),ParametreProfil.class);
                intent.putExtra("id",iddd);

                startActivity(intent);

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
       ///############################        MES commandes            ########################################
        mes_commandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "mes coomandes", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),HistoriqueCommandes.class);
                intent.putExtra("id",iddd);

                startActivity(intent);

            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
       ///############################        MES annoces            ########################################
        mes_annonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "mes annonces", Toast.LENGTH_SHORT).show();
                TextView   mon_idd= view.findViewById(R.id.mon_idd);
              // String id=mon_idd.getText().toString();
                Intent intent=new Intent(getContext(),HistoriqueAnnones.class);
                intent.putExtra("id",iddd);

                startActivity(intent);

            }
        });
        return  view;
    }

    private void init() {
       // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        Call<users> calll = apiInterface.profile(iddd);
        calll.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(response.body().getResponse().equals("ok")){
                    tnom_utilisateur= view.findViewById(R.id.tnom_utilisateur);
                    temail= view.findViewById(R.id.temail);
                    fm_logo= view.findViewById(R.id.fm_logo);

                    String b=response.body().getNom_utilisateur();
                    fm_logo.setText(b.substring(0,1));
                    if(b.charAt(0) == 'l') {
                        fm_logo.setBackgroundResource(R.drawable.profile_logo1);



                    }else if(b.charAt(0) == 'L'){
                        fm_logo.setBackgroundResource(R.drawable.round);

                    }else if(b.charAt(0) == 'N'){
                        fm_logo.setBackgroundResource(R.drawable.profile_logo2);

                    }else if(b.charAt(0) == 'R'){
                        fm_logo.setBackgroundResource(R.drawable.profile_logo3);

                    }else{
                        fm_logo.setBackgroundResource(R.drawable.profile_logo4);
                    }
                     tnom_utilisateur.setText(b);
                    temail.setText(response.body().getEmail());

                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
              //  Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        if (sessionManager.isLogin()) {

            iddd=sessionManager.login();
            fm_logo= view.findViewById(R.id.fm_logo);
            // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            Call<users> calll = apiInterface.profile(iddd);
            calll.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {
                    if(response.body().getResponse().equals("ok")){
                        tnom_utilisateur= view.findViewById(R.id.tnom_utilisateur);
                        temail= view.findViewById(R.id.temail);
                        String b=response.body().getNom_utilisateur();
                        tnom_utilisateur.setText(b);
                        fm_logo.setText(b.substring(0,1));

                        temail.setText(response.body().getEmail());

                    }
                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {
                 //   Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

                }
            });




        }
    }
}