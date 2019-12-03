package com.firends.examapp.Views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.firends.examapp.Controllers.ads_manager;
import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.firends.examapp.Utils.DynamicLinkManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mytag";
    Button gamPlay, ResutlsButton, InvitationsButton,btLink;
    public static String mydomine = "https://examapp.page.link";
    DynamicLinkManager dynamicLinkManager;
    Context mContext;
    private String link;
    private LinearLayout adView;
    private ads_manager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        dynamicLinkManager = new DynamicLinkManager(this);
        link=ShareLink.getLinkFromShered(this,"link");
        gamPlay = findViewById(R.id.GamePlay);
        ResutlsButton = findViewById(R.id.Bt_Results);
        InvitationsButton = findViewById(R.id.bt_Invitations);
        btLink=findViewById(R.id.bt_link);
        adView=findViewById(R.id.adView);
        adsManager=ads_manager.getInstance();
        adsManager.fbLoadBanner(this)
                .setAdListener(new AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        Toast.makeText(mContext, adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        Toast.makeText(mContext, "load", Toast.LENGTH_SHORT).show();
                        adView.addView(adsManager.fbadView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                });


        gamPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Gameplay.class));

            }
        });

        ResutlsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, FriendsAnswers.class));
            }
        });
        btLink.setText("Share Link With Your Friends");
        btLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (link.contains("Share Link With your Friends"))
                {
                    Toast.makeText(mContext, "Please Create Your Quiz", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, User.currentUser._UserName+ " Wants To Test Friendship With You Download App and Start Test "+ link );
                startActivity(Intent.createChooser(intent2, "Share via"));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(mContext, "Rate Us ⭐ ⭐ ⭐ ⭐ ⭐", Toast.LENGTH_LONG);
                View toastView = toast.getView();
                toastView.setBackgroundColor(getResources().getColor(R.color.colordark2));
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(getResources().getColor(R.color.white));

                toast.show();
                startRate();
            }
        });
        InvitationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void playGame(View view) {
        startActivity(new Intent(MainActivity.this, Gameplay.class));
    }


    private void startRate() {
        Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }



}
