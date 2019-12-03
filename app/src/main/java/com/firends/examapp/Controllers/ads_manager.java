package com.firends.examapp.Controllers;

import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.firends.examapp.R;



public class ads_manager {
    private final String TAG = "mytagads";

    private NativeAd nativeAd;
    public com.facebook.ads.AdView fbadView;
    public com.facebook.ads.InterstitialAd mInterstitialAdfb;


    public int counterads = 0;
    public static ads_manager Instance;

    public static ads_manager getInstance() {
        if (Instance == null)
            Instance = new ads_manager();
        return Instance;
    }




    public com.facebook.ads.AdView fbLoadBanner(final Context context) {

        try {
            fbadView = new com.facebook.ads.AdView(context,
                    context.getResources().getString(R.string.facebook_banner_id),
                    com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("e2317541-f503-4963-99da-62659eeb36cb");
            fbadView.loadAd();


        } catch (Exception e) {
        }

        return fbadView;
    }





    public com.facebook.ads.InterstitialAd loadFbInterstitial(final Context context) {
        if (mInterstitialAdfb == null) {
            try {
                mInterstitialAdfb = new com.facebook.ads.InterstitialAd(context, context.getResources().getString(R.string.facebook_interstitial));
                AdSettings.addTestDevice("e2317541-f503-4963-99da-62659eeb36cb");
                mInterstitialAdfb.loadAd();
            } catch (Exception e) {
                return mInterstitialAdfb;
            }

        } else if (!mInterstitialAdfb.isAdLoaded())
            mInterstitialAdfb.loadAd();
        mInterstitialAdfb.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                //t.makeText(context, adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        return mInterstitialAdfb;

    }

    public void showFbInterstitial(Context context) {
        try {
            if (mInterstitialAdfb.isAdLoaded())
                mInterstitialAdfb.show();
            else loadFbInterstitial(context);
        }catch (Exception ex){

        }

    }

    public void LoadNative(Context context){
        nativeAd = new NativeAd(context, context.getResources().getString(R.string.fb_native));

        nativeAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        });

        // Request an ad
        nativeAd.loadAd();

    }
    public NativeAd showNative(){
        return nativeAd;
    }

}
