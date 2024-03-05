package com.example.myapplicationliza;

import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplicationliza.Adapters.panierAdapter;
import com.example.myapplicationliza.Adapters.recu_commandeAdapter;

import com.example.myapplicationliza.Models.PanierModel;
import com.example.myapplicationliza.Models.recu_commandeModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.example.myapplicationliza.Sessions.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanierFragment extends Fragment implements  RecyclerViewPanier {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PanierFragment() {
        // Required empty public constructor
    }

String s ,iddd;
    private  View view;
    SessionManager sessionManager;
    public static ApiInterface apiInterface;
    RecyclerView RVPanier;
    TextView price,redirect;
    LinearLayout lprice;
    NeumorphButton vider_panier,valider_panier;
    private List<PanierModel> panierM;
    NeumorphImageView ppexit;
    private List<recu_commandeModel> commandeModel;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PanierFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PanierFragment newInstance(String param1, String param2) {
        PanierFragment fragment = new PanierFragment();
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
        view= inflater.inflate(R.layout.fragment_panier, container, false);
      /*  Bundle bundle=this.getArguments();
        pan_id=(TextView) view.findViewById(R.id.pan_id);
        if(bundle!= null){
            s=bundle.getString("id");
            
            pan_id.setText(s);
            }

       mon_id=(TextView) view.findViewById(R.id.mon_id);*/
        //////////////////////////////////////////////////////////////
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        sessionManager=new SessionManager(getContext());
        ppexit= view.findViewById(R.id.ppexit);

        //////////////////////////////////////////////////////////////////////////////

      //  Toast.makeText(getContext(), mon_id.getText().toString(), Toast.LENGTH_SHORT).show();
        //pan_id.setText(mon_id.getText().toString());
      //############################## valider ##############################""
        valider_panier= view.findViewById(R.id.valider_panier);
        price= view.findViewById(R.id.price);
        valider_panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.boite_dialog);
                //////////################""" Confirmer  ###########################

                NeumorphButton confirmer = dialog.findViewById(R.id.confirmer);
                confirmer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Call<users> call = apiInterface.recu_commande(iddd);
                        call.enqueue(new Callback<users>() {
                            @Override
                            public void onResponse(Call<users> call, Response<users> response) {
                                Dialog dialog = new Dialog(getContext());
                                dialog.setContentView(R.layout.recu_commande);
                                RecyclerView recu_commande = dialog.findViewById(R.id.recu_commande);
                                TextView cprice = dialog.findViewById(R.id.cprice);
                                cprice.setText("Prix total: "+price.getText());
                                NeumorphButton okk = dialog.findViewById(R.id.okk);
                                okk.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });

                                LinearLayoutManager linearVertical = new LinearLayoutManager(getContext());
                                linearVertical.setOrientation(RecyclerView.VERTICAL);
                                recu_commande.setLayoutManager(linearVertical);
                                commandeModel = new ArrayList<>();
                                Call<users> calll = apiInterface.recu_commande(iddd);
                                calll.enqueue(new Callback<users>() {
                                    @Override
                                    public void onResponse(Call<users> call, Response<users> response) {
                                        commandeModel = response.body().getCommande_valide();

                                            recu_commandeAdapter recu_commandeAdapter = new recu_commandeAdapter(commandeModel, getContext());
                                        recu_commande.setAdapter(recu_commandeAdapter);
                                        recu_commandeAdapter.notifyDataSetChanged();
                                        Call<users> validerCall = apiInterface.valider(iddd);
                                        Toast.makeText(getContext(), "valider", Toast.LENGTH_SHORT).show();

                                        validerCall.enqueue(new Callback<users>() {
                                            @Override
                                            public void onResponse(Call<users> call, Response<users> response) {

                                            }

                                            @Override
                                            public void onFailure(Call<users> call, Throwable t) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<users> call, Throwable t) {

                                    }
                                });
                                dialog.show();
                            }

                            @Override
                            public void onFailure(Call<users> call, Throwable t) {

                            }
                        });
                        //###########################################################"

                        dialog.dismiss();

                    }


                });
                //////////################""" annuler ###########################
                NeumorphButton neumorphButton_annuler = dialog.findViewById(R.id.annuler);
                neumorphButton_annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Commande annulée", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }





        });
     ////////////////////////////////  vider    /////////////////////////////////////////
        vider_panier= view.findViewById(R.id.vider_panier);
        vider_panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<users> vider_panierCall = apiInterface.getVider(iddd);
                vider_panierCall.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        vider_panier.setVisibility(View.GONE);
                      init();
                        RVPanier = view.findViewById(R.id.rvPanier);
                       panierM=new ArrayList<>();
                        panierAdapter panierAdapter1 = new panierAdapter(panierM, getContext(), PanierFragment.this::onItemClick);
                        panierAdapter1.clearData();
                     //   RVPanier.setAdapter(panierAdapter1);
                        panierAdapter1.notifyDataSetChanged();


                        //onStart();





                                //new PanierFragment();
                      //  Toast.makeText(getContext(), "panier vide", Toast.LENGTH_SHORT).show();
                        //RVPanier = (RecyclerView) view.findViewById(R.id.rvPanier);
                       // RVPanier.setVisibility(View.GONE);
                       // TextView panier_videe=(TextView) view.findViewById(R.id.panier_vide);
                       // panier_videe.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {

                    }
                });


            }
        });
     //##############################################################################"
        init();
        return  view;
    }

    private void init() {


        RVPanier = view.findViewById(R.id.rvPanier);
        lprice = view.findViewById(R.id.lprice);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext());
        linearVertical.setOrientation(RecyclerView.VERTICAL);
        RVPanier.setLayoutManager(linearVertical);
        panierM = new ArrayList<>();
        panierAdapter panierAdapter1 = new panierAdapter(panierM, getContext(), PanierFragment.this::onItemClick);
        panierAdapter1.clearData();
        RVPanier.setAdapter(panierAdapter1);
        panierAdapter1.notifyDataSetChanged();
        Call<users> informatiqueCall = apiInterface.getPanier(iddd);
        informatiqueCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> informatiqueCall, Response<users> response) {

                    panierM = response.body().getCommande();
                if (panierM.size() >0) {
                    Call<users> call = apiInterface.price(iddd);
                    call.enqueue(new Callback<users>() {
                        @Override
                        public void onResponse(Call<users> call, Response<users> response) {
                            lprice.setVisibility(View.VISIBLE);
                            price.setText(response.body().getResponse()+" DA");
                        }

                        @Override
                        public void onFailure(Call<users> call, Throwable t) {

                        }
                    });
                    panierAdapter panierAdapter1 = new panierAdapter(panierM, getContext(), PanierFragment.this::onItemClick);
                    RVPanier.setAdapter(panierAdapter1);
                    panierAdapter1.notifyDataSetChanged();
                }else  {
                   // Toast.makeText(getContext(), "vide", Toast.LENGTH_SHORT).show();
                    TextView panier_vide= view.findViewById(R.id.panier_vide);
                    ImageView panier_image= view.findViewById(R.id.panier_image);
                    LinearLayout pick= view.findViewById(R.id.pick);
                    valider_panier= view.findViewById(R.id.valider_panier);
                    vider_panier= view.findViewById(R.id.vider_panier);
                    panier_vide.setVisibility(View.VISIBLE);
                    panier_image.setVisibility(View.VISIBLE);
                    pick.setVisibility(View.VISIBLE);
                    redirect();
                    valider_panier.setVisibility(View.GONE);
                    vider_panier.setVisibility(View.GONE);
                    lprice.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<users> informatiqueCall, Throwable t) {
                //Toast.makeText(getContext(), "session expiré", Toast.LENGTH_SHORT).show();

            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        if (sessionManager.isLogin()) {

            iddd=sessionManager.login();
            RVPanier = view.findViewById(R.id.rvPanier);
            lprice = view.findViewById(R.id.lprice);

            LinearLayoutManager linearVertical = new LinearLayoutManager(getContext());
            linearVertical.setOrientation(RecyclerView.VERTICAL);
            RVPanier.setLayoutManager(linearVertical);
            panierM = new ArrayList<>();
            Call<users> informatiqueCall = apiInterface.getPanier(iddd);
            informatiqueCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> informatiqueCall, Response<users> response) {

                    panierM = response.body().getCommande();
                    if (panierM.size() >0) {
                        Call<users> call = apiInterface.price(iddd);
                        call.enqueue(new Callback<users>() {
                            @Override
                            public void onResponse(Call<users> call, Response<users> response) {
                                lprice.setVisibility(View.VISIBLE);
                                price.setText(response.body().getResponse()+" DA");
                            }

                            @Override
                            public void onFailure(Call<users> call, Throwable t) {

                            }
                        });
                        //###############################################################################"
                        panierAdapter panierAdapter1 = new panierAdapter(panierM, getContext(), PanierFragment.this::onItemClick);
                        RVPanier.setAdapter(panierAdapter1);
                        panierAdapter1.notifyDataSetChanged();

                        valider_panier= view.findViewById(R.id.valider_panier);
                        vider_panier= view.findViewById(R.id.vider_panier);
                        valider_panier.setVisibility(View.VISIBLE);
                        vider_panier.setVisibility(View.VISIBLE);
                    }else  {
                      //  Toast.makeText(getContext(), "vide", Toast.LENGTH_SHORT).show();
                        TextView panier_vide= view.findViewById(R.id.panier_vide);
                        ImageView panier_image= view.findViewById(R.id.panier_image);
                        LinearLayout pick= view.findViewById(R.id.pick);
                        valider_panier= view.findViewById(R.id.valider_panier);
                        vider_panier= view.findViewById(R.id.vider_panier);
                        panier_vide.setVisibility(View.VISIBLE);
                        pick.setVisibility(View.VISIBLE);
                        panier_image.setVisibility(View.VISIBLE);
                        redirect();
                     //   valider_panier.setVisibility(View.GONE);
                     //   vider_panier.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<users> informatiqueCall, Throwable t) {
                    //Toast.makeText(getContext(), "session expiré", Toast.LENGTH_SHORT).show();

                }
            });





        }
    }

    private void redirect() {
        redirect= view.findViewById(R.id.redirect);
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),acceuilActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemClick(PanierModel panier_model) {
        String pub_id = panier_model.getPub_id();

        Call<users> delete_panier_item = apiInterface.delete_panier_item(iddd,pub_id);
        delete_panier_item.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(response.body().getResponse().equals("ok")){
                    Toast.makeText(getContext(), "supprimé", Toast.LENGTH_SHORT).show();
                    init();
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });

    }

}