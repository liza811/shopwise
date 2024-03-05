package com.example.myapplicationliza.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplicationliza.Models.CommentModel;
import com.example.myapplicationliza.R;

import java.util.List;




public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentAdapterViewHolder>{
    List<CommentModel> commentModelList;
    Context context;
    private boolean showAllComments = false;


    public CommentAdapter(List<CommentModel> commentModelList, Context context) {
        this.commentModelList = commentModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.commentaire,viewGroup,false);

        return new CommentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapterViewHolder holder, int position) {
        CommentModel commentModel=commentModelList.get(position);
        holder.ccommentaire.setText(commentModel.getComment());
        holder.cdate.setText(commentModel.getDate());
        holder.cnom_utilisateur.setText(commentModel.getNom_utilisateur());

        holder.profile_logo.setText(holder.cnom_utilisateur.getText().toString().substring(0,1));
        if(holder.cnom_utilisateur.getText().toString().charAt(0) == 'l') {
            holder.profile_logo.setBackgroundResource(R.drawable.profile_logo1);



        }else if(holder.cnom_utilisateur.getText().toString().charAt(0) == 'L'){
            holder.profile_logo.setBackgroundResource(R.drawable.round);

        }else if(holder.cnom_utilisateur.getText().toString().charAt(0) == 'N'){
            holder.profile_logo.setBackgroundResource(R.drawable.profile_logo2);

        }else if(holder.cnom_utilisateur.getText().toString().charAt(0) == 'R'){
            holder.profile_logo.setBackgroundResource(R.drawable.profile_logo3);

        }else{
            holder.profile_logo.setBackgroundResource(R.drawable.profile_logo4);
        }

    }





        @Override
    public int getItemCount() {
        return commentModelList.size();

    }


    public static class CommentAdapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView cnom_utilisateur;
        private final TextView cdate;
        private final TextView ccommentaire;
        private final TextView profile_logo;

        @SuppressLint("WrongViewCast")
        public CommentAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            cnom_utilisateur= itemView.findViewById(R.id.cnom_utilisateur);
            cdate= itemView.findViewById(R.id.cdate);
            ccommentaire= itemView.findViewById(R.id.ccommentaire);
            profile_logo= itemView.findViewById(R.id.profile_logo);

        }
    }
}
