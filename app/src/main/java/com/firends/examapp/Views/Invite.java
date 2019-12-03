package com.firends.examapp.Views;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Invite extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private com.wang.avi.AVLoadingIndicatorView avi;
    private Context mContext;
    private Button startTest;
    public String TAG = "data";
    public static String InvitedUser=null;

    private ImageView imageInvitedUser;
    private TextView txtInvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        mContext = this;
        avi = findViewById(R.id.avi);
        startTest=findViewById(R.id.bt_invite_start);
        imageInvitedUser=findViewById(R.id.id_image_invitedUser);
        txtInvite=findViewById(R.id.invite_text);
        startTest.setVisibility(View.GONE);

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();

                            InvitedUser=deepLink.toString();
                            InvitedUser=InvitedUser.substring(InvitedUser.lastIndexOf("=")+1);
                            //Toast.makeText(mContext, InvitedUser, Toast.LENGTH_SHORT).show();
                            getInfoUserById(InvitedUser);
                        }
                        avi.hide();
                        startTest.setVisibility(View.VISIBLE);


                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        avi.hide();
                        startActivity(new Intent(Invite.this,Login.class));
                        Invite.this.finish();
                    }
                });

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =new Intent(Invite.this,FriendGameplay.class);
                intent.putExtra("UserID",InvitedUser);
                startActivity(intent);
                Invite.this.finish();
            }
        });
    }

    public void getInfoUserById(String userId){

        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                //Toast.makeText(mContext, user.get_Image(), Toast.LENGTH_SHORT).show();
                txtInvite.setText(user._UserName+ " wants to test friendship with you");
                Picasso.get()
                        .load(user.get_Image())
                        .placeholder(R.drawable.image_loading)
                        .error(R.mipmap.ic_launcher)
                        .into(imageInvitedUser);

            }
        });
    }
}
