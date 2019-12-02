package com.firends.examapp.Controllers;

import android.renderscript.Sampler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firends.examapp.Model.Notification;
import com.firends.examapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String TAG = "data";
    public HashMap<String ,Object> _MyInvitations=new HashMap<>();


    public void AddUser(User user,String IdToken) {


        db.collection("Users").document(user.get_IdUser())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        Notification notification = new Notification(IdToken,new HashMap<String, Integer>());

        db.collection("Notifications").document(user.get_IdUser())
                .set(notification).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Notification DocumentSnapshot successfully written!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Notification Error writing document", e);
            }
        });
    }
    public void UpdateUserQuestions(HashMap< String, Integer> MyQues){

        db.collection("Users").document(User.currentUser._IdUser)

                .update("_MyQuestion",MyQues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully Updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });



    }


    public void addInvite(String IdUser,String UserInvited){
        _MyInvitations.put(UserInvited,false);
        //db.collection("Users").document(IdUser).update(_MyInvitations);
    }

}
