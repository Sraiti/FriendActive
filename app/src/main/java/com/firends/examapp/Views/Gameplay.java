package com.firends.examapp.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.Controllers.DataBaseManager;
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

    DataBaseManager DataBaseM = new DataBaseManager();

    HashMap<String,Integer> Answers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //Views
        Img00 = findViewById(R.id.Img_00);
        Img01 = findViewById(R.id.Img_01);
        Img02 = findViewById(R.id.Img_02);
        Img03 = findViewById(R.id.Img_03);

        Txt_00 = findViewById(R.id.Txt_00);
        Txt_01 = findViewById(R.id.Txt_01);
        Txt_02 = findViewById(R.id.Txt_02);
        Txt_03 = findViewById(R.id.Txt_03);


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
        Txt_00.setOnClickListener(this);
        Txt_01.setOnClickListener(this);
        Txt_02.setOnClickListener(this);
        Txt_03.setOnClickListener(this);

        ClickAbles.add(Img00);
        ClickAbles.add(Img01);
        ClickAbles.add(Img02);
        ClickAbles.add(Img03);

        ClickAbles.add(Txt_00);
        ClickAbles.add(Txt_01);
        ClickAbles.add(Txt_02);
        ClickAbles.add(Txt_03);


    }

    private void NextQuestion(int index) {

        if (index < totalQues) {

            Question question = mQuestions.get(index);
            Log.d("TAG", "question.getImage() =" + question.getImage());
            String QuestionType = question.getImage();
            if (QuestionType.equals("Y")) {
                //Make All TextViews UnClickAble
                ButtonsStatue(true);

                Log.d("TAG", "IMAGEVIEWS TEAM");
                // Set Images With Picasso.
                String imagePath00 = question.getAnswer_00();
                String imagePath01 = question.getAnswer_01();
                String imagePath02 = question.getAnswer_02();
                String imagePath03 = question.getAnswer_03();

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

            DataBaseM.UpdateUserQuestions(Answers);

        }

    }

    private void ButtonsStatue(boolean Statue) {

        if (Statue) {
            for (Object item : ClickAbles) {
                if (item instanceof ImageView) {
                    ((ImageView) item).setClickable(true);
                    ((ImageView) item).setVisibility(View.VISIBLE);
                } else {
                    ((TextView) item).setClickable(false);
                    ((TextView) item).setVisibility(View.GONE);
                }
            }
            IsImage = true;
        } else {
            for (Object item : ClickAbles) {
                if (item instanceof ImageView) {
                    ((ImageView) item).setClickable(false);
                    ((ImageView) item).setVisibility(View.GONE);
                } else {
                    ((TextView) item).setClickable(true);
                    ((TextView) item).setVisibility(View.VISIBLE);
                }
            }
            IsImage = false;
        }
    }


    @Override
    public void onClick(View v) {
        if (IsImage) {
            ImageView image = (ImageView) v;

            int Answer = GetItemId(image.getTag().toString(), true);
            Log.d("TAG", mQuestions.get(index).getQuestionID() + " : " + Answer);
            Answers.put(mQuestions.get(index).getQuestionID()+"", Answer);
        } else {
            TextView text = (TextView) v;
            int Answer = GetItemId(text.getTag().toString(), false);
            Log.d("TAG", mQuestions.get(index).getQuestionID() + " : " +Answer);
            Answers.put(mQuestions.get(index).getQuestionID()+"", Answer);
        }
        NextQuestion(++index);
    }

    private int GetItemId(String id, boolean a) {

        if (a) {
            switch (id) {
                case "Img_00":
                    return 0;
                case "Img_01":
                    return 1;
                case "Img_02":
                    return 2;
                case "Img_03":
                    return 3;
            }
        } else {
            switch (id) {
                case "Txt_00":
                    return 0;
                case "Txt_01":
                    return 1;
                case "Txt_02":
                    return 2;
                case "Txt_03":
                    return 3;
            }
        }
        return 0;
    }

    private void GetData(final FireBaseCallBack fireBaseCallBack) {

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
                        totalQues = mQuestions.size();
                        fireBaseCallBack.OnCallback(mQuestions);
                    }
                });

    }

    private interface FireBaseCallBack {
        void OnCallback(List<Question> QuestionList);
    }


}
