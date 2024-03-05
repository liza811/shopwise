package com.example.myapplicationliza.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationliza.Models.CategorieModel;
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.RcCat;
import com.example.myapplicationliza.RecyclerViewInterface;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.PlateViewHolder> {
    private final List<CategorieModel> catModelList;
    private final Context context;
    private final RcCat recyclerViewInterface;

    public CategorieAdapter(List<CategorieModel> catModelList, Context context, RcCat recyclerViewInterface) {
        this.catModelList = catModelList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category,viewGroup,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {
        CategorieModel categorieModel=catModelList.get(position);
        holder.catTitle.setText(categorieModel.getCat_title());
        Glide.with(context).load(categorieModel.getCat_image()).placeholder(R.drawable.shopwise).into(holder.catImg);
        holder.cCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewInterface.onItemClickk(catModelList.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return catModelList.size();
    }
    public class PlateViewHolder extends RecyclerView.ViewHolder{
        private final ImageView catImg;
        private final TextView catTitle;
        ConstraintLayout cCard;

        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg= itemView.findViewById(R.id.imageView);
            catTitle= itemView.findViewById(R.id.title);
            cCard= itemView.findViewById(R.id.cCard);
        }
    }

}
