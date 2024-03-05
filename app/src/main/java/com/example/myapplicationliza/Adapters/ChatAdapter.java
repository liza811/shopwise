package com.example.myapplicationliza.Adapters;


import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationliza.Models.ChatMessage;
import com.example.myapplicationliza.R;
import com.example.myapplicationliza.Sessions.SessionManager;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int SENT_MESSAGE = 0;
    private static final int RECEIVED_MESSAGE = 1;


    private final List<ChatMessage> mMessages;


    public ChatAdapter(List<ChatMessage> messages) {
        mMessages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = mMessages.get(position);


        // If the message was sent by the current user, return SENT_MESSAGE.
        // Otherwise, return RECEIVED_MESSAGE.

        if (message.getSender_id().equals(message.getS_id())) {
            return SENT_MESSAGE;
        } else {
            return RECEIVED_MESSAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case SENT_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message_item, parent, false);
                return new SentMessageViewHolder(view);
            case RECEIVED_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message_item, parent, false);
                return new ReceivedMessageViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = mMessages.get(position);

        switch (holder.getItemViewType()) {
            case SENT_MESSAGE:
                ((SentMessageViewHolder) holder).bind(message);
                break;
            case RECEIVED_MESSAGE:
                ((ReceivedMessageViewHolder) holder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private TextView messageTime;

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message_text1);
           // messageTime = itemView.findViewById(R.id.sent_message_time);
        }

        public void bind(ChatMessage message) {
            messageText.setText(message.getMessage_content());
           // messageTime.setText(message.getMessageTime());
        }
    }

    public static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private TextView messageTime;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message_text);
            //messageTime = itemView.findViewById(R.id.received_message_time);
        }

        public void bind(ChatMessage message) {
            messageText.setText(message.getMessage_content());
           // messageTime.setText(message.getMessageTime());
        }
    }
}

