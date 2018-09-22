package com.example.friend.friendv2;

/**
 * Created by semin on 8/7/17.
 */

public class User {

    private String IdChat;
    private String Picture;
    private String SurnameName;

    public User(){

    }

    public User(String IdChat, String Picture, String SurnameName){
        this.IdChat = IdChat;
        this.Picture = Picture;
        this.SurnameName = SurnameName;
    }

    public String getIdChat() {
        return IdChat;
    }

    public void setIdChat(String idChat) {
        IdChat = idChat;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getSurnameName() {
        return SurnameName;
    }

    public void setSurnameName(String surnameName) {
        SurnameName = surnameName;
    }
}
