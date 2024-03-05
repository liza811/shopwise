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
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.RecyclerViewInterface;

import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class ArticleOccasionAdapter extends RecyclerView.Adapter<ArticleOccasionAdapter.ArticleOccasionViewHolder>{
    List<ArticleOccasionModel> articleOccasionModelList;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    //////////////// constructor/////////////

    public ArticleOccasionAdapter(List<ArticleOccasionModel> articleOccasionModelList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.articleOccasionModelList = articleOccasionModelList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }




    @NonNull
    @Override
    public ArticleOccasionAdapter.ArticleOccasionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_banner,viewGroup,false);

        return new ArticleOccasionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArticleOccasionAdapter.ArticleOccasionViewHolder holder, int position) {
       ArticleOccasionModel articleOccasionModel=articleOccasionModelList.get(position);
        holder.pub_id.setText(articleOccasionModel.getPub_id());
        holder.publication_titre.setText(articleOccasionModel.getPub_title());
        Glide.with(context).load(articleOccasionModel.getPub_image()).placeholder(R.drawable.shopwise).into(holder.publication_image);

        holder.publication_prix.setText(articleOccasionModel.getPrix()+" DA");
        holder.main_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewInterface.onItemClick(articleOccasionModelList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return articleOccasionModelList.size();
    }

    public class ArticleOccasionViewHolder extends RecyclerView.ViewHolder {
        private final ImageView publication_image;
        private final TextView publication_titre;
        private final TextView publication_prix;
        private final TextView pub_id;
        NeumorphCardView main_card;
        public ArticleOccasionViewHolder(@NonNull View itemView) {
            super(itemView);
            publication_image= itemView.findViewById(R.id.banner_img);
            publication_titre= itemView.findViewById(R.id.titre);
            publication_prix= itemView.findViewById(R.id.prix_pub);
            pub_id= itemView.findViewById(R.id.pub_id);
            main_card= itemView.findViewById(R.id.main_cardd);
        }
    }
}
