package com.firends.examapp.Model;

import java.util.HashMap;

public class Notification {
    String IdToken;
    HashMap<String,Integer> Friends=new HashMap<>();

    public Notification(String idToken, HashMap<String, Integer> friends) {
        IdToken = idToken;
        Friends = friends;
    }

    public String getIdToken() {
        return IdToken;
    }

    public void setIdToken(String idToken) {
        IdToken = idToken;
    }

    public HashMap<String, Integer> getFriends() {
        return Friends;
    }

    public void setFriends(HashMap<String, Integer> friends) {
        Friends = friends;
    }

    public Notification( ) {
     }
}
