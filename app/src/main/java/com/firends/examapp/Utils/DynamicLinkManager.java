package com.firends.examapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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




    }



}
