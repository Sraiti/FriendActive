package com.firends.examapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.firends.examapp.Utils.DynamicLinkManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mytag";
    Button gamPlay,ResutlsButton,InvitationsButton;
    public static String mydomine = "https://examapp.page.link";
    DynamicLinkManager dynamicLinkManager;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        dynamicLinkManager = new DynamicLinkManager(this);

        gamPlay = findViewById(R.id.GamePlay);
        ResutlsButton=findViewById(R.id.Bt_Results);
        InvitationsButton=findViewById(R.id.bt_Invitations);
    }

    public void playGame(View view) {
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(mydomine+"="+User.currentUser.get_IdUser()))
                .setDomainUriPrefix(mydomine)
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder(getPackageName()).build())
                // Set parameters
                // ...
                .buildShortDynamicLink().addOnCompleteListener((Activity) this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            Intent intent2 = new Intent();
                            intent2.setAction(Intent.ACTION_SEND);
                            intent2.setType("text/plain");
                            intent2.putExtra(Intent.EXTRA_TEXT, shortLink.toString() );
                            startActivity(Intent.createChooser(intent2, "Share via"));

                            Toast.makeText(MainActivity.this, shortLink.toString(), Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });




    }


}
