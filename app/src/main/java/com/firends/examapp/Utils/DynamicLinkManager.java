package com.firends.examapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.util.concurrent.Executor;

public class DynamicLinkManager {

    public static String mydomine = "https://examapp.page.link";
    private static final String TAG = "mytag";
    public Context context;
    public FirebaseDynamicLinks firebaseDynamicLinks=FirebaseDynamicLinks.getInstance();
    public Task<ShortDynamicLink> shortLinkTask;
    public DynamicLinkManager (Context context){
        this.context=context;
    }


    public void buildDynamicLink(String UserId) {

        Task<ShortDynamicLink> shortLinkTask = firebaseDynamicLinks.createDynamicLink()
                .setLink(Uri.parse(mydomine+"="+UserId))
                .setDomainUriPrefix(mydomine)
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder(context.getPackageName()).build())
                // Set parameters
                // ...
                .buildShortDynamicLink().addOnCompleteListener((Activity) context, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                            Uri shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            Toast.makeText(context, shortLink.toString(), Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }



}
