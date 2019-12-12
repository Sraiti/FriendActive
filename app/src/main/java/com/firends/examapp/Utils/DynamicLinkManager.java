package com.firends.examapp.Utils;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class DynamicLinkManager {

    private static final String TAG = "mytag";
    public static String mydomine = "https://examapp.page.link";
    public Context context;
    public FirebaseDynamicLinks firebaseDynamicLinks = FirebaseDynamicLinks.getInstance();
    public Task<ShortDynamicLink> shortLinkTask;

    public DynamicLinkManager(Context context) {
        this.context = context;
    }


    public void buildDynamicLink(String UserId) {


    }


}
