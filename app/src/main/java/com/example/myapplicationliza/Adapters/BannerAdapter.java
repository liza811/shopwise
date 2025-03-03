package com.example.myapplicationliza.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationliza.Models.BannerModel;

import com.example.myapplicationliza.R;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.PlateViewHolder> {
    private final List<BannerModel> bannerModelList;
    private final Context context;


    public BannerAdapter(List<BannerModel> bannerModelList, Context context) {
        this.bannerModelList = bannerModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_banner,viewGroup,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {
        BannerModel bannerModel=bannerModelList.get(position);

        Glide.with(context).load(bannerModel.getBanner_img()).placeholder(R.drawable.shopwise).into(holder.publicite_image);

    }

    @Override
    public int getItemCount() {
        return bannerModelList.size();
    }
    public static class PlateViewHolder extends RecyclerView.ViewHolder{
        private final ImageView publicite_image;


        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
            publicite_image= itemView.findViewById(R.id.ImageBanner);

        }
    }

}
