package com.example.myapplicationliza.Models;


public class ChatMessage {

    private String message_content;
    private String timestamp;
    private String sender_id;
    private String receiver_id;
    private String s_id;




    public ChatMessage(String message_content, String timestamp, String sender_id, String receiver_id,String s_id) {
        this.message_content = message_content;
        this.timestamp = timestamp;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.s_id = s_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }
}