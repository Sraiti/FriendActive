package com.firends.examapp.Model;

public class Question {

    int QuestionID;
    String Question;
    String Answer_00, Answer_01, Answer_02, Answer_03, Answer_Ph_0, Answer_Ph_1, Answer_Ph_2, Answer_Ph_3;
    int Type;

    String Questionfriend;

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer_00() {
        return Answer_00;
    }

    public void setAnswer_00(String answer_00) {
        Answer_00 = answer_00;
    }

    public String getAnswer_01() {
        return Answer_01;
    }

    public void setAnswer_01(String answer_01) {
        Answer_01 = answer_01;
    }

    public String getAnswer_02() {
        return Answer_02;
    }

    public void setAnswer_02(String answer_02) {
        Answer_02 = answer_02;
    }

    public String getAnswer_03() {
        return Answer_03;
    }

    public void setAnswer_03(String answer_03) {
        Answer_03 = answer_03;
    }

    public String getAnswer_Ph_0() {
        return Answer_Ph_0;
    }

    public void setAnswer_Ph_0(String answer_Ph_0) {
        Answer_Ph_0 = answer_Ph_0;
    }

    public String getAnswer_Ph_1() {
        return Answer_Ph_1;
    }

    public void setAnswer_Ph_1(String answer_Ph_1) {
        Answer_Ph_1 = answer_Ph_1;
    }

    public String getAnswer_Ph_2() {
        return Answer_Ph_2;
    }

    public void setAnswer_Ph_2(String answer_Ph_2) {
        Answer_Ph_2 = answer_Ph_2;
    }

    public String getAnswer_Ph_3() {
        return Answer_Ph_3;
    }

    public void setAnswer_Ph_3(String answer_Ph_3) {
        Answer_Ph_3 = answer_Ph_3;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getQuestionfriend() {
        return Questionfriend;
    }

    public void setQuestionfriend(String questionfriend) {
        Questionfriend = questionfriend;
    }

    public Question() {
    }

    public Question(int questionID, String question, String answer_00, String answer_01, String answer_02, String answer_03, String answer_Ph_0, String answer_Ph_1, String answer_Ph_2, String answer_Ph_3, int type, String questionfriend) {
        QuestionID = questionID;
        Question = question;
        Answer_00 = answer_00;
        Answer_01 = answer_01;
        Answer_02 = answer_02;
        Answer_03 = answer_03;
        Answer_Ph_0 = answer_Ph_0;
        Answer_Ph_1 = answer_Ph_1;
        Answer_Ph_2 = answer_Ph_2;
        Answer_Ph_3 = answer_Ph_3;
        Type = type;
        Questionfriend = questionfriend;
    }
}
