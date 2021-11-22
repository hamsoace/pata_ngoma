package com.hamsoace.patangoma.accounts;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hamsoace.patangoma.MainActivity;
import com.hamsoace.patangoma.R;
import com.hamsoace.patangoma.accounts.SignUp.SignUp;
import com.hamsoace.patangoma.accounts.utils.AccountUtils;
import com.hamsoace.patangoma.aob.utils.Utils;

public class Login extends AppCompatActivity {

    private static final String TAG = Login.class.getSimpleName();
    public static final String userEmail = "";


    private FirebaseAuth mAuth;

    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;
    private Context context;
    private GoogleSignInClient mGoogleSignInClient;;

    private AccountUtils accountUtils;

    private LayoutInflater globalLayout;
    ViewGroup parentLayout;

    EditText EmailAddress, Password;
    Button LoginButton, RegisterButton;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser mUser;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton = (Button) findViewById(R.id.button_login);

        RegisterButton = (Button) findViewById(R.id.button_register);
        EmailAddress = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mUser != null){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Log.d(TAG, "AuthStateChanged:Logout");
                }
            }
        };

//        context = this;
//
//        accountUtils = new AccountUtils(context);
//
//        alertDialog = Utils.getSimpleDialog(this, "");
//        progressDialog = Utils.getProgressDialog(this, "", false);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(getString(R.string.default_web_client_id))
//        .requestEmail().build();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignIn();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void userSignIn() {

        email = EmailAddress.getText().toString().trim();
        password = Password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(Login.this, "Enter the correct Email", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(Login.this, "Enter the correct Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in, Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (!task.isSuccessful()){
                   progressDialog.dismiss();
                   Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
               }else{
                   progressDialog.dismiss();
                   checkIfEmailIsVerified();
               }
           }
        });
    }

    private void checkIfEmailIsVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = user.isEmailVerified();
        if(!emailVerified){
            Toast.makeText(this, "Verify the Email Address", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            finish();
        }else{
            EmailAddress.getText().clear();
            Password.getText().clear();
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra(userEmail, email);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {

        super.onStart();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        Login.super.finish();
    }


}
