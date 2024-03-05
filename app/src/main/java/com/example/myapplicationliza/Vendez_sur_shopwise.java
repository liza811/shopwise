package com.example.myapplicationliza;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soup.neumorphism.NeumorphImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Vendez_sur_shopwise#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Vendez_sur_shopwise extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NeumorphImageView exit1;
    View view;

    public Vendez_sur_shopwise() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Vendez_sur_shopwise.
     */
    // TODO: Rename and change types and number of parameters
    public static Vendez_sur_shopwise newInstance(String param1, String param2) {
        Vendez_sur_shopwise fragment = new Vendez_sur_shopwise();
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
        view= inflater.inflate(R.layout.fragment_vendez_sur_shopwise, container, false);
        exit1= view.findViewById(R.id.vexit);
        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(),acceuilActivity.class);
                startActivity(intent);

            }
        });
        return view;


    }
}