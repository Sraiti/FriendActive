package com.firends.examapp.Views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.firends.examapp.Utils.DynamicLinkManager;
import com.firends.examapp.Utils.Language;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class ShareLink extends AppCompatActivity {
    private static final String TAG = "mytag";
    public static String mydomine = "https://examapp.page.link";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    DynamicLinkManager dynamicLinkManager;
    Button btn_copy, btn_share;
    String ShareLinkText;
    private Uri shortLink;
    private TextView dynamicLink, Txt_Share;
    private Language language;

    public static String getLinkFromShered(Context context, String Object) {
        String link = null;
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("linkInfo", MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }

        link = sharedPreferences.getString(Object, "Share Link With your Friends");
        return link;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_link);
        dynamicLink = findViewById(R.id.mydynamiclink);
        sharedPreferences = getSharedPreferences("linkInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        language = Language.getInstance();

        Txt_Share = findViewById(R.id.txt_share);
        btn_copy = findViewById(R.id.btn_copy);
        btn_share = findViewById(R.id.btn_share);
        String Lang = ShareLink.getLinkFromShered(getApplicationContext(), "Language");

        ShareLinkText = " wants to test friendship with you, Download This App and Start The Test ";
        btn_copy.setText(language.languageArray.get("copy"));
        btn_share.setText(language.languageArray.get("buttonshare"));
        Txt_Share.setText(language.languageArray.get("share"));

        buildDynamiclink();
    }

    public void buildDynamiclink() {
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://exe=" + User.currentUser.get_IdUser()))
                .setDomainUriPrefix(mydomine)
                .setSocialMetaTagParameters(new DynamicLink.SocialMetaTagParameters.Builder().build())
                .setGoogleAnalyticsParameters(new DynamicLink.GoogleAnalyticsParameters.Builder().build())
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder(getPackageName()).build())
                // Set parameters
                // ...
                .buildShortDynamicLink().addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            dynamicLink.setText(shortLink.toString());
                            editor.putString("link", shortLink.toString());
                            editor.commit();


                        } else {

                            Toast.makeText(ShareLink.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

        String Mylink = "";

    }

    public void shareLink(View view) {

        if (shortLink == null) {
            Toast.makeText(this, "please Try Again", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, ShareLink.getLinkFromShered(ShareLink.this, "name") + language.languageArray.get("shareText") + shortLink.toString());
        startActivity(Intent.createChooser(intent2, "Share via"));


    }

    public void copylink(View view) {
        if (shortLink == null) {
            Toast.makeText(this, "please Try Again", Toast.LENGTH_SHORT).show();
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("mylink", shortLink.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(ShareLink.this, shortLink.toString() + " is Copied ", Toast.LENGTH_LONG).show();
    }


}
