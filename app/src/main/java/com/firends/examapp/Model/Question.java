package com.firends.examapp.Model;

public class Question {

    int QuestionID;
    String Question;
    String Answer_00,Answer_01,Answer_02,Answer_03;
    String Image;

    public Question() {
    }

    public Question(int questionID, String question, String answer_00, String answer_01, String answer_02, String answer_03, String image) {
        QuestionID = questionID;
        Question = question;
        Answer_00 = answer_00;
        Answer_01 = answer_01;
        Answer_02 = answer_02;
        Answer_03 = answer_03;
        Image = image;
    }

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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
