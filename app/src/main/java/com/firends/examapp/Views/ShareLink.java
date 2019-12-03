package com.firends.examapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.firends.examapp.Utils.DynamicLinkManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class ShareLink extends AppCompatActivity {
    public static String mydomine = "https://examapp.page.link";
    DynamicLinkManager dynamicLinkManager;
    private static final String TAG = "mytag";
    private Uri shortLink;
    private TextView dynamicLink;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_link);
        dynamicLink=findViewById(R.id.mydynamiclink);
        sharedPreferences=getSharedPreferences("linkInfo",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        buildDynamiclink();
    }



    public void buildDynamiclink(){
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(mydomine+"="+ User.currentUser.get_IdUser()))
                .setDomainUriPrefix(mydomine)
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder(getPackageName()).build())
                // Set parameters
                // ...
                .buildShortDynamicLink().addOnCompleteListener((Activity) this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                           dynamicLink.setText(shortLink.toString());
                           editor.putString("link",shortLink.toString());
                           editor.commit();


                        } else {

                            Toast.makeText(ShareLink.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    public void shareLink(View view) {

        if (shortLink==null){
            Toast.makeText(this, "please Try Again", Toast.LENGTH_SHORT).show();
            return;
        }


            Intent intent2 = new Intent();
            intent2.setAction(Intent.ACTION_SEND);
            intent2.setType("text/plain");
            intent2.putExtra(Intent.EXTRA_TEXT,User.currentUser._UserName+ " Wants To Test Friendship With You Download App and Start Test "+ shortLink.toString() );
            startActivity(Intent.createChooser(intent2, "Share via"));


    }

    public void copylink(View view) {
        if (shortLink==null){
            Toast.makeText(this, "please Try Again", Toast.LENGTH_SHORT).show();
            return;
        }
        
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("mylink", shortLink.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(ShareLink.this, shortLink.toString()+" is Copyed ", Toast.LENGTH_LONG).show();
    }

    public static String getLinkFromShered(Context context,String Object){
        String link=null;
        if (sharedPreferences==null){
            sharedPreferences=context.getSharedPreferences("linkInfo",MODE_PRIVATE);
            editor=sharedPreferences.edit();
        }

        link=sharedPreferences.getString(Object,"Share Link With your Friends");
        return link;
    }


}