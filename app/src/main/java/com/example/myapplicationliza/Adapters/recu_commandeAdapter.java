package com.example.myapplicationliza.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.Models.recu_commandeModel;
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.RecyclerViewInterface;

import java.util.List;

public class recu_commandeAdapter extends RecyclerView.Adapter<recu_commandeAdapter.recu_commandeAdapterViewHolder> {
    List<recu_commandeModel> recu_commandeModels;

    Context context;
    //////////////// constructor/////////////


    public recu_commandeAdapter(List<recu_commandeModel> recu_commandeModels, Context context) {
        this.recu_commandeModels = recu_commandeModels;
        this.context = context;
    }

    @NonNull
    @Override
    public recu_commandeAdapter.recu_commandeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_commande_item,viewGroup,false);
        return new recu_commandeAdapter.recu_commandeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recu_commandeAdapter.recu_commandeAdapterViewHolder holder, int position) {
        recu_commandeModel recu_commandeModel=recu_commandeModels.get(position);
        holder.pan_titree.setText(recu_commandeModel.getPub_title());


        holder.pan_prixx.setText(recu_commandeModel.getPrix()+" DA");

    }

    @Override
    public int getItemCount() {
        return recu_commandeModels.size();
    }
    public class recu_commandeAdapterViewHolder extends RecyclerView.ViewHolder{

        private final TextView pan_titree;
        private final TextView pan_prixx;

        public recu_commandeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            pan_titree= itemView.findViewById(R.id.pan_titree);
            pan_prixx= itemView.findViewById(R.id.pan_prixx);
        }
    }}
