package com.example.friend.friendv2;

/**
 * Created by semin on 8/27/17.
 */

public class Friend {

    private String Name;
    private String Surname;
    private String Picture;

    public void Friend(String name, String surname, String picture){
        this.Name = name;
        this.Surname = surname;
        this.Picture = picture;
    }

    public void Friend(){

    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
}
