package com.firends.examapp.Views;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.firends.examapp.Utils.language;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Invite extends AppCompatActivity {
    public static String InvitedUser = null;
    public static String InvitedUserName = null;
    public static User invitedUser;
    public String TAG = "data";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private com.wang.avi.AVLoadingIndicatorView avi;
    private Context mContext;
    private Button startTest;
    private ImageView imageInvitedUser;
    private TextView txtInvite;
    private language language;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        mContext = this;
        language = com.firends.examapp.Utils.language.getInstance();
        avi = findViewById(R.id.avi);
        startTest = findViewById(R.id.bt_invite_start);
        imageInvitedUser = findViewById(R.id.id_image_invitedUser);
        txtInvite = findViewById(R.id.invite_text);
        startTest.setEnabled(false);
        //invitedUser._UserName="Your Friend";
        sharedPreferences = getSharedPreferences("linkInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();

                            InvitedUser = deepLink.toString();
                            InvitedUser = InvitedUser.substring(InvitedUser.lastIndexOf("=") + 1);
                            //st.makeText(mContext, InvitedUser, Toast.LENGTH_SHORT).show();
                            getInfoUserById(InvitedUser);
                        } else {
                            StartActivity();
                        }




                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        avi.hide();
                        Toast.makeText(mContext, "non dynamic link", Toast.LENGTH_SHORT).show();
                        StartActivity();
                    }
                });

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StartActivity();
            }
        });
    }

    public void getInfoUserById(String userId) {

        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                invitedUser = documentSnapshot.toObject(User.class);
                //Toast.makeText(mContext, user.get_Image(), Toast.LENGTH_SHORT).show();
                editor.putString("Language", invitedUser.getLanguageUser());
                editor.commit();
                language.AddLanguage(mContext);
                txtInvite.setText(invitedUser._UserName + language.languageArray.get("txtInvit"));
                InvitedUserName = invitedUser._UserName;
                Picasso.get()
                        .load(invitedUser.get_Image())
                        .placeholder(R.drawable.image_loading)
                        .error(R.mipmap.ic_launcher)
                        .into(imageInvitedUser);
                startTest.setEnabled(true);
                avi.hide();
                //if (invitedUser._UserName.contains("Your Friend"));

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        StartActivity();
                    }
                })
        ;
    }

    public void StartActivity() {
        Intent intent = new Intent(Invite.this, Login.class);
        startActivity(intent);
        Invite.this.finish();
    }


}
