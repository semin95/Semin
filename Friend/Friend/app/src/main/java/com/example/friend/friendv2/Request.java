package com.example.friend.friendv2;

/**
 * Created by semin on 8/5/17.
 */

public class Request {

    private String SenderSurnameName;
    private String SenderPicture;

    public Request(){

    }

    public Request(String imeIPrezime, String slika){
        this.SenderSurnameName = imeIPrezime;
        this.SenderPicture = slika;
    }


    public String getSenderSurnameName() {
        return SenderSurnameName;
    }

    public void setSenderSurnameName(String senderSurnameName) {
        SenderSurnameName = senderSurnameName;
    }

    public String getSenderPicture() {
        return SenderPicture;
    }

    public void setSenderPicture(String senderPicture) {
        SenderPicture = senderPicture;
    }
}
