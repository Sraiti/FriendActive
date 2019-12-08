package com.firends.examapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.BuildConfig;
import com.firends.examapp.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class Done extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView Txt_point;
    Button Btn_Share, Btn_Home;

    int Point;
    Intent intent = new Intent(Done.this, MainActivity.class);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        Txt_point = findViewById(R.id.txt_point);
        Intent a = getIntent();
        Point = a.getIntExtra("Points", 0);
        Txt_point.setText("Your score is : " + Point);
        final TickerView tickerView = findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setAnimationDuration(2500);
        tickerView.setText(Txt_point.getText().toString(), true);


        Btn_Home = findViewById(R.id.btn_home);
        Btn_Share = findViewById(R.id.btn_share);


        Btn_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "\nMy Score Is :" + Point;
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n" +
                        "Challenge You friends And Find out how much they really know about you  \n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));

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
