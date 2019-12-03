package com.firends.examapp.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firends.examapp.Controllers.DataBaseManager;
import com.firends.examapp.Model.User;
import com.firends.examapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {

    private Context mContext;
    static final int RC_SIGN_IN = 123;
    private static final String TAG = "mytag";
    FirebaseAuth mAuth;
    //GoogleSignInClient mGoogleSignInClient;
    private SignInButton ButtonLogin;
    private DataBaseManager manager;
    private View dialogName;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = new DataBaseManager();

        mContext = this;
        ButtonLogin = findViewById(R.id.bt_login);
        mAuth = FirebaseAuth.getInstance();

        inflater = this.getLayoutInflater();
        dialogName = inflater.inflate(R.layout.dialog_getname, null);

        //get invitation link


        //-------------------------

        /*GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);


            if (resultCode == RESULT_OK) {

                // Successfully signed in

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final User NewUser = new User();
                NewUser.set_IdUser(user.getUid());
                NewUser.set_Image(user.getPhotoUrl().toString());
                if (user.getDisplayName() == null) {
                    showdialogname();
                    dialogName.findViewById(R.id.bt_getname).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EditText name=dialogName.findViewById(R.id.txt_name);
                            if (!name.equals("")){
                                NewUser.set_UserName(name.getText().toString());
                                manager.AddUser(NewUser, FirebaseInstanceId.getInstance().getToken());
                                User.currentUser = NewUser;
                                if (Invite.InvitedUser != null)
                                    manager.addInvite(Invite.InvitedUser, NewUser.get_IdUser());
                                startActivity(new Intent(Login.this, MainActivity.class));
                                Login.this.finish();
                            }else {
                                Toast.makeText(mContext, "Please Entry Your Name", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else {

                    NewUser.set_UserName(user.getDisplayName());
                    User.currentUser = NewUser;
                    manager.AddUser(NewUser, FirebaseInstanceId.getInstance().getToken());
                    if (Invite.InvitedUser != null)
                        manager.addInvite(Invite.InvitedUser, NewUser.get_IdUser());
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Login.this.finish();
                }


                // ...
            } else {


                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Toast.makeText(mContext, user.getDisplayName(), Toast.LENGTH_SHORT).show();

                            User NewUser = new User();
                            NewUser.set_IdUser(user.getUid());
                            NewUser.set_Image(user.getPhotoUrl().toString());
                            if (user.getDisplayName() == null) {
                                showdialogname();

                            } else {
                                NewUser.set_UserName(user.getDisplayName());
                                User.currentUser = NewUser;
                                manager.AddUser(NewUser, "");
                                if (Invite.InvitedUser != null)
                                    manager.addInvite(Invite.InvitedUser, NewUser.get_IdUser());
                                startActivity(new Intent(Login.this, MainActivity.class));
                            }


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.",
                            // Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

            User NewUser = new User();
            NewUser.set_IdUser(currentUser.getUid());
            NewUser.set_UserName(currentUser.getDisplayName());
            NewUser.set_Image(currentUser.getPhotoUrl().toString());
            User.currentUser = NewUser;
            if (Invite.InvitedUser != null)
                manager.addInvite(Invite.InvitedUser, NewUser.get_IdUser());
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
            //Toast.makeText(mContext, currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();
        }


    }

    public void showdialogname() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setView(dialogName)
                .setCancelable(false);

        builder.show();
    }
}
