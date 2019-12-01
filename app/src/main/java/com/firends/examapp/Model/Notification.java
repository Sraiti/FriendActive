package com.firends.examapp.Model;

import java.util.HashMap;

public class Notification {

    String _TokenId;
    HashMap<String,Integer> Friends;


    public Notification() {
    }

    public Notification(String _TokenId, HashMap<String, Integer> friends) {
        this._TokenId = _TokenId;
        Friends = friends;
    }

    public String get_TokenId() {
        return _TokenId;
    }

    public void set_TokenId(String _TokenId) {
        this._TokenId = _TokenId;
    }

    public HashMap<String, Integer> getFriends() {
        return Friends;
    }

    public void setFriends(HashMap<String, Integer> friends) {
        Friends = friends;
    }
}
