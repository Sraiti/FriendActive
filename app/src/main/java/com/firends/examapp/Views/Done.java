package com.firends.examapp.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.R;
import com.firends.examapp.Utils.Screenshot;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Done extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView Txt_point;
    Button btn_Share;
    String path;
    ImageView imageView;
    Intent share = new Intent(Intent.ACTION_SEND);
    int Point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        Txt_point=findViewById(R.id.txt_point);
        btn_Share=findViewById(R.id.btn_Share);
        Intent a =getIntent();
        Point=a.getIntExtra("Points",0);
        Txt_point.setText("Your score is : "+Point);
        final TickerView tickerView = findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setAnimationDuration(2500);
        imageView=findViewById(R.id.imageView5);
        share.setType("image/jpeg");
        tickerView.setText(Txt_point.getText().toString(),true);

        btn_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
                imageView.setImageBitmap(b);
                btn_Share.setBackgroundColor(Color.parseColor("#999999"));
                storeScreenshot(b,"Image");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

    }
    public void storeScreenshot(Bitmap bitmap, String filename) {
          path = Environment.getExternalStorageDirectory().toString() + "/" + filename;
        Log.d(TAG, "storeScreenshot: "+path);
        OutputStream out = null;
        File imageFile = new File(path);

        try {
            out = new FileOutputStream(imageFile);
            // choose JPEG format
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();
        } catch (FileNotFoundException e) {
            // manage exception ...
        } catch (IOException e) {
            // manage exception ...
        } finally {

            try {
                if (out != null) {
                    out.close();
                }

            } catch (Exception exc) {
            }

        }
    }
}
