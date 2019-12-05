package com.firends.examapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.firends.examapp.Controllers.DataBaseManager;
import com.firends.examapp.Model.Question;
import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendGameplay extends AppCompatActivity implements  View.OnClickListener {
    ImageView Img00, Img01, Img02, Img03;
    TextView Txt_00, Txt_01, Txt_02, Txt_03, Txt_Question;
    CardView Card_00, Card_01, Card_02, Card_03;

    LinearLayout Linear_00, Linear_01;
    FirebaseFirestore db;

    String UserID;

    List<Question> mQuestions = new ArrayList<>();
    List<LinearLayout> ClickAbles = new ArrayList<>();
    int totalQues;
    int index = 0;
    boolean IsImage;

    DataBaseManager DataBaseM = new DataBaseManager();

    HashMap<String, Integer> Answers = new HashMap<>();

    User user;
    int Point;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_gameplay);



        //Views
        Img00 = findViewById(R.id.id_image1);
        Img01 = findViewById(R.id.id_image2);
        Img02 = findViewById(R.id.id_image3);
        Img03 = findViewById(R.id.id_image4);

        Txt_00 = findViewById(R.id.id_txt1);
        Txt_01 = findViewById(R.id.id_txt2);
        Txt_02 = findViewById(R.id.id_txt3);
        Txt_03 = findViewById(R.id.id_txt4);

        Card_00 = findViewById(R.id.Card_00);
        Card_01 = findViewById(R.id.Card_01);
        Card_02 = findViewById(R.id.Card_02);
        Card_03 = findViewById(R.id.Card_03);

        Linear_00 = findViewById(R.id.Linear_00);
        Linear_01 = findViewById(R.id.Linear_01);


        Linear_00.setVisibility(View.GONE);
        Linear_01.setVisibility(View.GONE);

        Txt_Question = findViewById(R.id.txt_Ques);

        Intent a = getIntent();
        UserID = a.getStringExtra("UserID");

        Point =0;
        //DataBase
        db = FirebaseFirestore.getInstance();

        GetData(new  FireBaseCallBack() {
            @Override
            public void OnCallback(List<Question> QuestionList) {

                NextQuestion(index);



            }
        });
        db.collection("Users").document(UserID)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                Answers=user.get_MyQuestion();
            }
        });

        Card_00.setOnClickListener(this);
        Card_01.setOnClickListener(this);
        Card_02.setOnClickListener(this);
        Card_03.setOnClickListener(this);

        ClickAbles.add(Linear_00);
        ClickAbles.add(Linear_01);

    }
    private void NextQuestion(int index) {
        if (index < totalQues) {
            Question question = mQuestions.get(index);

            if (question.getType() == 2) {
                CardStatue(2);
                // Set Images With Picasso.
                String imagePath00 = question.getAnswer_Ph_0();
                String imagePath01 = question.getAnswer_Ph_1();

                Log.d("TAG", "ID: " +question.getQuestionID() );

                if (imagePath00.trim().isEmpty()) {
                    imagePath00 = "https://firebasestorage.googleapis.com/v0/b/friendsexam-f39db.appspot.com/o/4-Unique-Placeholder-Image-Services-for-Designers.png?alt=media&token=6e8be0a4-cc7b-4f52-8053-f71e187b2597";

                }
                if (imagePath01.trim().isEmpty()) {
                    imagePath01 = "https://firebasestorage.googleapis.com/v0/b/friendsexam-f39db.appspot.com/o/4-Unique-Placeholder-Image-Services-for-Designers.png?alt=media&token=6e8be0a4-cc7b-4f52-8053-f71e187b2597";

                }


                Picasso.get()
                        .load(imagePath00)
                        .error(R.drawable.image_loading)
                        .placeholder(getResources().getDrawable(R.drawable.image_loading))
                        .fit()
                        .into(Img00);
                Picasso.get()
                        .load(imagePath01)
                        .error(R.drawable.image_loading)
                        .placeholder(getResources().getDrawable(R.drawable.image_loading))
                        .fit()
                        .into(Img01);

                Txt_00.setText(question.getAnswer_00());
                Txt_01.setText(question.getAnswer_01());


                Txt_Question.setText(question.getQuestion());

            } else {
                CardStatue(4);

                String imagePath00 = question.getAnswer_Ph_0();
                String imagePath01 = question.getAnswer_Ph_1();
                String imagePath02 = question.getAnswer_Ph_2();
                String imagePath03 = question.getAnswer_Ph_3();


                Log.d("TAG", "ID: " +question.getQuestionID() );

                if (imagePath00.trim().isEmpty()) {
                    imagePath00 = "https://firebasestorage.googleapis.com/v0/b/friendsexam-f39db.appspot.com/o/4-Unique-Placeholder-Image-Services-for-Designers.png?alt=media&token=6e8be0a4-cc7b-4f52-8053-f71e187b2597";

                }
                if (imagePath01.trim().isEmpty()) {
                    imagePath01 = "https://firebasestorage.googleapis.com/v0/b/friendsexam-f39db.appspot.com/o/4-Unique-Placeholder-Image-Services-for-Designers.png?alt=media&token=6e8be0a4-cc7b-4f52-8053-f71e187b2597";

                }
                if (imagePath02.trim().isEmpty()) {
                    imagePath02 = "https://firebasestorage.googleapis.com/v0/b/friendsexam-f39db.appspot.com/o/4-Unique-Placeholder-Image-Services-for-Designers.png?alt=media&token=6e8be0a4-cc7b-4f52-8053-f71e187b2597";

//                    Log.d("TAG", "NextQuestion: " + 22);
                }
                if (imagePath03.trim().isEmpty()) {
                    imagePath03 = "https://firebasestorage.googleapis.com/v0/b/friendsexam-f39db.appspot.com/o/4-Unique-Placeholder-Image-Services-for-Designers.png?alt=media&token=6e8be0a4-cc7b-4f52-8053-f71e187b2597";
//                    Log.d("TAG", "NextQuestion: " + 232);
                }
                Picasso.get()
                        .load(imagePath00)
                        .error(R.drawable.image_loading)
                        .placeholder(getResources().getDrawable(R.drawable.image_loading))
                        .fit()
                        .into(Img00);
                Picasso.get()
                        .load(imagePath01)
                        .error(R.drawable.image_loading)
                        .placeholder(getResources().getDrawable(R.drawable.image_loading))
                        .fit()
                        .into(Img01);
                Picasso.get()
                        .load(imagePath02)
                        .error(R.drawable.image_loading)
                        .placeholder(getResources().getDrawable(R.drawable.image_loading))
                        .fit()
                        .into(Img02);
                Picasso.get()
                        .load(imagePath03)
                        .error(R.drawable.image_loading)
                        .placeholder(getResources().getDrawable(R.drawable.image_loading))
                        .fit()
                        .into(Img03);

                Txt_00.setText(question.getAnswer_00());
                Txt_01.setText(question.getAnswer_01());
                Txt_02.setText(question.getAnswer_02());
                Txt_03.setText(question.getAnswer_03());

                Txt_Question.setText(question.getQuestion());

            }

        } else {

            DataBaseM.UpdatingMyMap(UserID, Point, this);
            Toast.makeText(this, "Questions Done " + Point, Toast.LENGTH_SHORT).show();
            Intent a = new Intent(FriendGameplay.this, Done.class);
            a.putExtra("Points", Point);
            startActivity(a);
            finish();
        }

    }

    private void CardStatue(int i) {

        if (i == 2) {

            Linear_00.setVisibility(View.VISIBLE);
            Linear_01.setVisibility(View.GONE);
        } else {

            Linear_00.setVisibility(View.VISIBLE);
            Linear_01.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {

        CardView Card = (CardView) v;
        int FriendAnswer = GetItemId(Card.getTag().toString());
        Log.d("TAG", mQuestions.get(index).getQuestionID() + " : " + FriendAnswer);

        int MyAnswer=Answers.get(mQuestions.get(index).getQuestionID()+"");
        if (FriendAnswer==MyAnswer){
            Point=Point+10;
        }



        NextQuestion(++index);
    }

    private int GetItemId(String id) {
        switch (id) {
            case "Card_00":
                return 0;
            case "Card_01":
                return 1;
            case "Card_02":
                return 2;
            case "Card_03":
                return 3;
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
//                                Log.d("TAG", document.getId() + " => " + document.getData());
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
