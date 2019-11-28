package com.firends.examapp.Model;

import android.net.Uri;

public class User {
    public String IdUser;
    public String UserName;

    public Uri Image;
    public static User CurrentUser;



    public User() {
    }

    public User(String idUser, String userName, Uri image) {
        IdUser = idUser;
        UserName = userName;
        Image = image;
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



    public Uri getImage() {
        return Image;
    }

    public void setImage(Uri image) {
        Image = image;
    }
}
