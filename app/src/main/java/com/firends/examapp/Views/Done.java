package com.firends.examapp.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.BuildConfig;
import com.firends.examapp.Controllers.Screenshot;
import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.firends.examapp.Utils.Language;
import com.squareup.picasso.Picasso;

public class Done extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView Txt_point, nameInvitedUser, nameCurrentUser, txtFriendship;
    Button Btn_Share, Btn_Home;
    int TotalQuestions;
    int Point;
    Intent intent;
    ImageView imageView;
    LinearLayout linearLayout;
    private ImageView imgInvitedUser, imgCurrentUser;
    private SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(intent);
        finish();
    }
    private Language language;
    String ShareLinkText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        sharedPreferences = getSharedPreferences("linkInfo", MODE_PRIVATE);
        intent = new Intent(Done.this, MainActivity.class);
        Btn_Home = findViewById(R.id.btn_home);
        Btn_Share = findViewById(R.id.btn_share);
        Txt_point = findViewById(R.id.txt_point);
        imgCurrentUser = findViewById(R.id.id_currentUser);
        imgInvitedUser = findViewById(R.id.id_image_invitedUser);
        nameCurrentUser = findViewById(R.id.txt_currentUser);
        nameInvitedUser = findViewById(R.id.txt_invitedUser);
        txtFriendship = findViewById(R.id.txt_friendship);
        linearLayout = findViewById(R.id.Screen_layout);

        language = Language.getInstance();
        language.AddLanguage(this);

          ShareLinkText = language.languageArray.get("shareText");

        imageView = findViewById(R.id.image);
        Intent a = getIntent();
        Point = a.getIntExtra("Points", 0);
        TotalQuestions = a.getIntExtra("TotalQuestion", 20);
        Txt_point.setText(R.string.Yourscoor + Point);
        nameInvitedUser.setText(Invite.invitedUser.get_UserName());
        nameCurrentUser.setText(sharedPreferences.getString("name", "You"));
        txtFriendship.setText((Point * 100) / TotalQuestions + "%");
        Picasso.get()
                .load(User.currentUser._Image)
                .fit()
                .into(imgCurrentUser);
        Picasso.get()
                .load(Invite.invitedUser._Image)
                .fit()
                .into(imgInvitedUser);


        Btn_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "My Score Is :" + Point;
                shareMessage = shareMessage + "\nhttps://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n" +
                        ShareLinkText;
//                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
//                startActivity(Intent.createChooser(shareIntent, "choose one"));


                Bitmap b = Screenshot.TakeScreenshotRootView(linearLayout);
                Uri uri = Screenshot.saveImageExternal(b, Done.this);
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("image/png");
                startActivity(intent);

            }
        });
        Btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

                finish();

            }
        });

    }


}
