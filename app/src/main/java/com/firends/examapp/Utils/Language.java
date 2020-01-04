package com.firends.examapp.Utils;

import android.content.Context;

import com.firends.examapp.Views.ShareLink;

import java.util.HashMap;

public class Language {

    public static Language Instance;
    public HashMap<String, String> languageArray = new HashMap<>();

    public static Language getInstance() {
        if (Instance == null)
            Instance = new Language();
        return Instance;
    }

    public void AddLanguage(Context context) {
        String Lang = ShareLink.getLinkFromShered(context, "Language");
        if (Lang.equals("ar")) {
            languageArray.put("shareText", " يدعوك لإكتشاف مدى قوة صداقتكم حمل التطبيق واقبل التحدي ");
            languageArray.put("InviteText", " يدعوك لإكتشاف مدي قوة صداقتكم");
            languageArray.put("Create", "إنشاء الاختبار");
            languageArray.put("Results", "نتائج الأصدقاء");
            languageArray.put("btShare", "شارك رابطك مع الاصدقاء");
            languageArray.put("loginText", "سجّل الدخول وابدأ الاختبار مع أصدقائك الآن");
            languageArray.put("FriendReact", "ما رأيك في نتيجة ؟ إختبر أصدقائك و إكتشف ما مدي معرفتهم عنك.");
            languageArray.put("buttonHome", "الصفحة الرئيسية");
            languageArray.put("buttonshare", "شارك");
            languageArray.put("copy", "نسخ");
            languageArray.put("share", "شارك رابطك مع الأصدقاء");

        } else if (Lang.equals("fr")) {
            languageArray.put("shareText", " vous invite à tester Votre amitié , téléchargez  l'application et lancez le test ");
            languageArray.put("InviteText", " vous invite à tester Votre amitié");
            languageArray.put("Create", "Créer un test");
            languageArray.put("Results", "Résultats des amis");
            languageArray.put("btShare", "Partagez votre lien avec vous amies");
            languageArray.put("loginText", "Connectez-vous et commencez à tester avec vos amis maintenant");
            languageArray.put("FriendReact", "Que pensez-vous du résultat?\n" +
                    "Défiez vos amis et découvrez ce qu'ils savent vraiment de vous.");
            languageArray.put("buttonHome", "Accueil");
            languageArray.put("buttonshare", "partager");
            languageArray.put("copy", "copie");
            languageArray.put("share", "partagez votre lien avec vos amis");

        } else {
            languageArray.put("shareText", " wants to discover the strength of your friendship, Download This App and Start The Test ");
            languageArray.put("InviteText", "Challenges you ");
            languageArray.put("Create", "Create  Test");
            languageArray.put("Results", "Results Of My Friends");
            languageArray.put("btShare", "Share Your Link With Friends");
            languageArray.put("loginText", "Sign In  And Start Test With Your Friends Now");
            languageArray.put("FriendReact", "What do you think about the result ?" +
                    " Challenge Your friends and Figure out how much they really know about you .");
            languageArray.put("buttonHome", "home");
            languageArray.put("buttonshare", "share");
            languageArray.put("copy", "copy");
            languageArray.put("share", "share your link with friends");

        }

    }

}