package com.firends.examapp.Model;

import android.net.Uri;

public class User {
    public String IdUser;
    public String UserName;
    public String Email;
    public Uri Image;


    public User() {
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Uri getImage() {
        return Image;
    }

    public void setImage(Uri image) {
        Image = image;
    }
}
