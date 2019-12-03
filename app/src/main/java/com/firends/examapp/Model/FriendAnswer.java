package com.firends.examapp.Model;

public class FriendAnswer {

    public String _UserName;
    public String  Point;

    public FriendAnswer(String _UserName, String  point) {
        this._UserName = _UserName;
        Point = point;
    }

    public String get_UserName() {
        return _UserName;
    }

    public void set_UserName(String _UserName) {
        this._UserName = _UserName;
    }

    public String  getPoint() {
        return Point;
    }

    public void setPoint(String  point) {
        Point = point;
    }
}
