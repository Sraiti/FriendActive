package com.firends.examapp.Utils;

import android.content.Context;

import com.firends.examapp.Views.ShareLink;

import java.util.HashMap;

public class language {

    public static language Instance;

    public static language getInstance() {
        if (Instance == null)
            Instance = new language();
        return Instance;
    }

    public HashMap<String, String> languageArray = new HashMap<>();

    public void AddLanguage(Context context) {
        String Lang = ShareLink.getLinkFromShered(context, "Language");
        if (Lang.equals("ar")) {
            languageArray.put("shareText", " يدعوك لإكتشاف مدي قوة صداقتكم حمل التطبيق و إقبل التحدي ");
            languageArray.put("Create", "إنشاء الاختبار");
            languageArray.put("Results", "نتائج الأصدقاء");
            languageArray.put("btShare", "مشاركة رابطك مع الاصدقاء");
            languageArray.put("loginText", "سجّل الدخول وابدأ الاختبار مع أصدقائك الآن");

        } else if (Lang.equals("fr")) {
            languageArray.put("shareText", " vous invite à tester  l'amitié avec vous, téléchargez  l'application et lancez le test ");
            languageArray.put("Create", "Créer un test");
            languageArray.put("Results", "Résultats des amis");
            languageArray.put("btShare", "Partagez votre lien avec des amis");
            languageArray.put("loginText", "Connectez-vous et commencez à tester avec vos amis maintenant");
        } else {
            languageArray.put("shareText", " wants to test friendship with you, Download This App and Start The Test ");
            languageArray.put("Create", "Create  Test");
            languageArray.put("Results", "Results Of My Friends");
            languageArray.put("btShare", "Share Your Link With Friends");
            languageArray.put("loginText", "Sign In  And Start Test With Your Friends Now");
        }

    }

}
