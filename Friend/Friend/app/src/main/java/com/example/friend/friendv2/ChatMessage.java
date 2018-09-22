package com.example.friend.friendv2;

import java.util.Date;

/**
 * Created by semin on 8/4/17.
 */

public class ChatMessage {

    private String messageText;
    private String senderId;
    private long messageTime;
    private String seen;

    public ChatMessage(String messageText, String messageUser, String seen){

        this.messageText = messageText;
        this.senderId = messageUser;
        this.messageTime = new Date().getTime();
        this.seen = seen;

    }

    public ChatMessage(){

    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }
}
