package com.firends.examapp.Model;

public class Invitastin {

    public String _IdUser;
    public String _Name;

    public Invitastin(String _IdUser, String _Name) {

        this._IdUser = _IdUser;
        this._Name = _Name;
    }

    public String get_IdUser() {
        return _IdUser;
    }

    public void set_IdUser(String _IdUser) {
        this._IdUser = _IdUser;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }
}
