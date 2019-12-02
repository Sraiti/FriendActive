package com.firends.examapp.Model;

public class FriendAnswer {

    public String _UserName;
    public int Point;

    public FriendAnswer(String _UserName, int point) {
        this._UserName = _UserName;
        Point = point;
    }

    public String get_UserName() {
        return _UserName;
    }

    public void set_UserName(String _UserName) {
        this._UserName = _UserName;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }
}
