package com.firends.examapp.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.facebook.FacebookSdk.getCacheDir;

public class Screenshot  {
    private File imagePath;


    public static Bitmap TakeScreenshot(View view){

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return b ;
    }

    public static  Bitmap TakeScreenshotRootView(View view){
        return TakeScreenshot(view);
    }

    public static Uri saveImageExternal(Bitmap image, Context context) {
        //TODO - Should be processed in another thread
        File imagesFolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagesFolder.mkdirs();
            File file = new File(imagesFolder, "shared_image.png");

            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.flush();
            stream.close();
            uri = FileProvider.getUriForFile(context, "com.mydomain.fileprovider", file);

        } catch (IOException e) {
            Log.d("TAG", "ScreenShot:IOException while trying to write file for sharing: " + e.getMessage());
        }
        return uri;
    }
}
