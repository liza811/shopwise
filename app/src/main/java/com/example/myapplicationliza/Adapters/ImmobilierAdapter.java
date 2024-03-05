package com.example.myapplicationliza.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.RecyclerViewInterface;

import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class ImmobilierAdapter extends RecyclerView.Adapter<ImmobilierAdapter.ImmobilierAdapterViewHolder>{
    List<ArticleOccasionModel> articleModelList;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public ImmobilierAdapter(List<ArticleOccasionModel> articleModelList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.articleModelList = articleModelList;
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public ImmobilierAdapter.ImmobilierAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article,viewGroup,false);
        return new ImmobilierAdapter.ImmobilierAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImmobilierAdapter.ImmobilierAdapterViewHolder holder, int position) {
        ArticleOccasionModel articleModel=articleModelList.get(position);
        holder.article_id.setText(articleModel.getPub_id());
        holder.article_titre.setText(articleModel.getPub_title());
        Glide.with(context).load(articleModel.getPub_image()).placeholder(R.drawable.shopwise).into(holder.article_image);

        holder.article_prix.setText(articleModel.getPrix()+" DA");
        holder.main_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewInterface.onItemClick(articleModelList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleModelList.size();
    }

    public class ImmobilierAdapterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView article_image;
        private final TextView article_titre;
        private final TextView article_prix;
        private final TextView article_id;
        NeumorphCardView main_card;
        public ImmobilierAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            article_image= itemView.findViewById(R.id.article_image);
            article_titre= itemView.findViewById(R.id.article_titre);
            article_prix= itemView.findViewById(R.id.particle_prix);
            article_id= itemView.findViewById(R.id.article_id);
            main_card= itemView.findViewById(R.id.main_card);

        }
    }
}
