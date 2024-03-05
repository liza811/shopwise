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
import com.example.myapplicationliza.Models.PanierModel;
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.RecyclerViewPanier;

import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class panierAdapter extends RecyclerView.Adapter<panierAdapter.panierAdapterViewHolder>{
    List<PanierModel> paniereModelList;
    Context context;
    private final RecyclerViewPanier recyclerViewInterface;

    public panierAdapter(List<PanierModel> paniereModelList, Context context, RecyclerViewPanier recyclerViewInterface) {
        this.paniereModelList = paniereModelList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    public void clearData() {
        paniereModelList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public panierAdapter.panierAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.panier_item,viewGroup,false);
        return new panierAdapter.panierAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull panierAdapterViewHolder holder, int position) {
        PanierModel articleModel=paniereModelList.get(position);
        holder.article_id.setText(articleModel.getPub_id());
        holder.article_titre.setText(articleModel.getPub_title());
        Glide.with(context).load(articleModel.getPub_image()).placeholder(R.drawable.shopwise).into(holder.article_image);

        holder.article_prix.setText(articleModel.getPrix()+" DA");
        holder.utilisat_id.setText(articleModel.getVendeur_id());
        holder.croix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               recyclerViewInterface.onItemClick(paniereModelList.get(position));
            }
        });


    }





    @Override
    public int getItemCount() {
        return paniereModelList.size();
    }

    public class panierAdapterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView article_image;
        private final TextView article_titre;
        private final TextView article_prix;
        private final TextView article_id;
        private final TextView utilisat_id;
        private final TextView croix;
        NeumorphCardView main_card;
        public panierAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            article_image= itemView.findViewById(R.id.pan_image);
            article_titre= itemView.findViewById(R.id.pan_titre);
            article_prix= itemView.findViewById(R.id.pan_prix);
            article_id= itemView.findViewById(R.id.pan_id_publication);
            utilisat_id= itemView.findViewById(R.id.pan_id_utilisateur);
            main_card= itemView.findViewById(R.id.panier_card);
            croix= itemView.findViewById(R.id.croix);
        }
    }
}
