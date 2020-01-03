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
    private Uri shortLink;
    private TextView dynamicLink, Txt_Share;
    Button btn_copy, btn_share;

    String ShareLinkText;


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

        Txt_Share = findViewById(R.id.txt_share);
        btn_copy = findViewById(R.id.btn_copy);
        btn_share = findViewById(R.id.btn_share);
        String Lang = ShareLink.getLinkFromShered(getApplicationContext(), "Language");
        switch (Lang) {
            case "en":
                ShareLinkText = " wants to test friendship with you, Download This App and Start The Test ";
                btn_copy.setText("Copy");
                btn_share.setText("Share");
                Txt_Share.setText("Share This Link With Your Friends");
                break;
            case "fr":
                ShareLinkText = " vous invite à tester  l'amitié avec vous, téléchargez cette application et lancez le test  ";
                btn_copy.setText("copie");
                btn_share.setText("Partager");
                Txt_Share.setText("Partagez votre lien avec vous amies");


                break;
            case "ar":
                ShareLinkText = " يدعوك لإكتشاف مدي قوة صداقتكم حمل هذا التطبيق و إقبل التحدي ";
                btn_copy.setText("أنسخ");
                btn_share.setText("أنشر");
                Txt_Share.setText("شارك رابطك مع الاصدقاء");

                break;
        }

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
        intent2.putExtra(Intent.EXTRA_TEXT, ShareLink.getLinkFromShered(ShareLink.this, "name") + ShareLinkText + shortLink.toString());
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
