package com.firends.examapp.Views;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.Model.Question;
import com.firends.examapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gameplay extends AppCompatActivity implements View.OnClickListener {


    ImageView Img00, Img01, Img02, Img03;
    TextView Txt_00, Txt_01, Txt_02, Txt_03, Txt_Question;

    FirebaseFirestore db;

    List<Question> mQuestions = new ArrayList<>();
    List<Object> ClickAbles = new ArrayList<>();
    int totalQues;
    int index = 0;
    boolean IsImage;

    Map Answers = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //Views
        Img00 = findViewById(R.id.Img_00);
        Img01 = findViewById(R.id.Img_01);
        Img02 = findViewById(R.id.Img_02);
        Img03 = findViewById(R.id.Img_03);

  //
        Txt_Question = findViewById(R.id.txt_Ques);

        //DataBase
        db = FirebaseFirestore.getInstance();

        GetData(new FireBaseCallBack() {
            @Override
            public void OnCallback(List<Question> QuestionList) {

                NextQuestion(index);
            }
        });

        //ClickListeners
        Img00.setOnClickListener(this);
        Img01.setOnClickListener(this);
        Img02.setOnClickListener(this);
        Img03.setOnClickListener(this);
//        Txt_00.setOnClickListener(this);
//        Txt_01.setOnClickListener(this);
//        Txt_02.setOnClickListener(this);
//        Txt_03.setOnClickListener(this);

        ClickAbles.add(Img00);
        ClickAbles.add(Img01);
        ClickAbles.add(Img02);
        ClickAbles.add(Img03);

//        ClickAbles.add(Txt_00);
//        ClickAbles.add(Txt_01);
//        ClickAbles.add(Txt_02);
//        ClickAbles.add(Txt_03);


    }

    private void NextQuestion(int index) {

        if (index < mQuestions.size()) {

            Question question = mQuestions.get(index);
            Log.d("TAG", "question.getImage() =" + question.getImage());
            String QuestionType = question.getImage();
            if (QuestionType.equals("Y")) {
                //Make All TextViews UnClickAble
                ButtonsStatue(true);

                Log.d("TAG", "IMAGEVIEWS TEAM");
                // Set Images With Picasso.
                String imagePath00 = question.getAnswer_00();
                String imagePath01 = question.getAnswer_00();
                String imagePath02 = question.getAnswer_00();
                String imagePath03 = question.getAnswer_00();

                Picasso.get()
                        .load(imagePath00)
                        .fit()
                        .into(Img00);
                Picasso.get()
                        .load(imagePath01)
                        .fit()
                        .into(Img01);
                Picasso.get()
                        .load(imagePath02)
                        .fit()
                        .into(Img02);
                Picasso.get()
                        .load(imagePath03)
                        .fit()
                        .into(Img03);


                Txt_Question.setText(question.getQuestion());

            } else {
                //Make All ImageViews UnClickAble
                ButtonsStatue(false);
                Log.d("TAG", "TextViews TEAM");

                Txt_00.setText(question.getAnswer_00());
                Txt_01.setText(question.getAnswer_01());
                Txt_02.setText(question.getAnswer_02());
                Txt_03.setText(question.getAnswer_03());

                Txt_Question.setText(question.getQuestion());

            }

        } else {
            Toast.makeText(this, "Questions Done", Toast.LENGTH_SHORT).show();
        }

    }

    private void ButtonsStatue(boolean Statue) {

        //The same thing with foreach


        if (Statue) {

            Img00.setClickable(true);
            Img01.setClickable(true);
            Img02.setClickable(true);
            Img03.setClickable(true);


            IsImage = true;
        } else {

            Img00.setClickable(false);
            Img01.setClickable(false);
            Img02.setClickable(false);
            Img03.setClickable(false);
            Txt_00.setClickable(true);
            Txt_01.setClickable(true);
            Txt_02.setClickable(true);
            Txt_03.setClickable(true);

                IsImage = false;

            }
        }



        @Override
        public void onClick (View v){


            NextQuestion(++index);


        }

        private void GetData ( final FireBaseCallBack fireBaseCallBack){

            db.collection("Questions").orderBy("QuestionID")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                    Question Qu = document.toObject(Question.class);
                                    mQuestions.add(Qu);
                                }
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                            fireBaseCallBack.OnCallback(mQuestions);
                            totalQues = mQuestions.size();
                        }
                    });

        }

        private interface FireBaseCallBack {
            void OnCallback(List<Question> QuestionList);
        }


    }
