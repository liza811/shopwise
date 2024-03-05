package com.example.myapplicationliza.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationliza.Models.CommentModel;
import com.example.myapplicationliza.Models.MessagerieModel;
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.RcCat;
import com.example.myapplicationliza.RcDiscussion;

import java.util.List;

public class MessagerieAdapter extends RecyclerView.Adapter<MessagerieAdapter.MessagerieAdapterViewHolder>{
    List<MessagerieModel> commentModelList;
    Context context;
    private RcDiscussion recyclerViewInterface;

    public MessagerieAdapter(List<MessagerieModel> commentModelList, Context context) {
        this.commentModelList = commentModelList;
        this.context = context;
    }

    public MessagerieAdapter(List<MessagerieModel> commentModelList, Context context, RcDiscussion recyclerViewInterface) {
        this.commentModelList = commentModelList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MessagerieAdapter.MessagerieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_messagerie,viewGroup,false);

        return new MessagerieAdapter.MessagerieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagerieAdapter.MessagerieAdapterViewHolder holder, int position) {
        MessagerieModel commentModel=commentModelList.get(position);
        holder.mcommentaire.setText(commentModel.getMessage());
        holder.mdate.setText(commentModel.getTime());
        holder.mnom_utilisateur.setText(commentModel.getNom());
        holder.layout_messagerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewInterface.onItemClickk(commentModelList.get(position));
            }
        });
        char p=commentModel.getNom().charAt(0);
        holder.mprofile_logo.setText(holder.mnom_utilisateur.getText().toString().substring(0,1));
        if(holder.mnom_utilisateur.getText().toString().charAt(0) == 'l') {
            holder.mprofile_logo.setBackgroundResource(R.drawable.profile_logo1);



        }else if(holder.mnom_utilisateur.getText().toString().charAt(0) == 'L'){
            holder.mprofile_logo.setBackgroundResource(R.drawable.round);

        }else if(holder.mnom_utilisateur.getText().toString().charAt(0) == 'N'){
            holder.mprofile_logo.setBackgroundResource(R.drawable.profile_logo2);

        }else if(holder.mnom_utilisateur.getText().toString().charAt(0) == 'R'){
            holder.mprofile_logo.setBackgroundResource(R.drawable.profile_logo3);

        }else{
            holder.mprofile_logo.setBackgroundResource(R.drawable.profile_logo4);
        }

    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }
    public class MessagerieAdapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView mnom_utilisateur;
        private final TextView mdate;
        private final TextView mcommentaire;
        private final TextView mprofile_logo;
        private final CardView layout_messagerie;

        public MessagerieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mnom_utilisateur= itemView.findViewById(R.id.mnom_utilisateur);
            mdate= itemView.findViewById(R.id.mdate);
            mcommentaire= itemView.findViewById(R.id.mcommentaire);
            mprofile_logo= itemView.findViewById(R.id.mprofile_logo);
            layout_messagerie= itemView.findViewById(R.id.layout_messagerie);
        }
    }
}
