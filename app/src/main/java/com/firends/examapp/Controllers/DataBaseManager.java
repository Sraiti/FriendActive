package com.firends.examapp.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firends.examapp.Model.FriendAnswer;
import com.firends.examapp.Model.Notification;
import com.firends.examapp.Model.User;
import com.firends.examapp.Views.FriendsAnswers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseManager {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String TAG = "TAG";
    public HashMap<String, Object> _MyInvitations = new HashMap<>();


    public void AddUser(User user, String IdToken) {


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

        Notification notification = new Notification(IdToken, new HashMap<String, Integer>());

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

    public void UpdateUserQuestions(HashMap<String, Integer> MyQues) {

        db.collection("Users").document(User.currentUser._IdUser)

                .update("_MyQuestion", MyQues)
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


    public void addInvite(String IdUser, String UserInvited) {
        _MyInvitations.put(UserInvited, false);
        //db.collection("Users").document(IdUser).update(_MyInvitations);
    }


    public List<Object> getFriendAnswers(final Context context) {
        final List<Object> friendAnswersList = new ArrayList<>();
        DocumentReference docRef = db.collection("Notifications").document(User.currentUser.get_IdUser());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                HashMap<String, Integer> forms = (HashMap<String, Integer>) documentSnapshot.get("friends");

                for (HashMap.Entry<String, Integer> item : forms.entrySet()) {
                    Toast.makeText(context, item.getKey(), Toast.LENGTH_SHORT).show();
                    friendAnswersList.add(new FriendAnswer(item.getKey(), String.valueOf(item.getValue())));
                }

                FriendsAnswers.adapter.notifyDataSetChanged();
            }
        });

        return friendAnswersList;
    }

    public void UpdatingMyMap(final String UserID, final int Point) {

         DocumentReference docRef = db.collection("Notifications").document(UserID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                HashMap<String, Integer> form = (HashMap<String, Integer>) documentSnapshot.get("friends");

                form.put(User.currentUser.get_UserName(),Point);

                db.collection("Notifications").document(UserID)

                        .update("friends", form)
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
        });





    }
}
