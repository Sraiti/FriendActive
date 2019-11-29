package com.firends.examapp.Model;

import android.net.Uri;

import java.util.HashMap;

public class User {
    public String _IdUser;
    public String _UserName;
    public String _Image;
    public HashMap<Integer,Integer> _MyQuestion=new HashMap<>();
    public HashMap<String ,Object> _FriendAnswer=new HashMap<>();
    public HashMap<String ,Object> _MyInvitations=new HashMap<>();

    public static User currentUser;



    public User() {
    }

    public String get_IdUser() {
        return _IdUser;
    }

    public void set_IdUser(String _IdUser) {
        this._IdUser = _IdUser;
    }

    public String get_UserName() {
        return _UserName;
    }

    public void set_UserName(String _UserName) {
        this._UserName = _UserName;
    }

    public String get_Image() {
        return _Image;
    }

    public void set_Image(String _Image) {
        this._Image = _Image;
    }

    public HashMap<Integer, Integer> get_MyQuestion() {
        return _MyQuestion;
    }

    public void set_MyQuestion(HashMap<Integer, Integer> _MyQuestion) {
        this._MyQuestion = _MyQuestion;
    }

    public HashMap<String, Object> get_FriendAnswer() {
        return _FriendAnswer;
    }

    public void set_FriendAnswer(HashMap<String, Object> _FriendAnswer) {
        this._FriendAnswer = _FriendAnswer;
    }

    public HashMap<String, Object> get_MyInvitations() {
        return _MyInvitations;
    }

    public void set_MyInvitations(HashMap<String, Object> _MyInvitations) {
        this._MyInvitations = _MyInvitations;
    }
}
