package com.example.ecommerce_app.registeruser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.ecommerce_app.MainActivity;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.userdata.user;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    AwesomeValidation awesomeValidation;
    FirebaseAuth mauth;
    String userID;
    private GoogleSignInClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
getSupportActionBar().hide();
        TextView sign=findViewById(R.id.signin);
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putBoolean("activity_executed", true);
        edt.commit();

        final EditText useremail =findViewById(R.id.useremail);
        final EditText password =findViewById(R.id.Password);
        final EditText confirmpass=findViewById(R.id.confirmPassword);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        SignInButton signInButton_google=findViewById(R.id.sign_in_google);
        awesomeValidation.addValidation(this, R.id.useremail, RegexTemplate.NOT_EMPTY, R.string.invalid);
        awesomeValidation.addValidation(this, R.id.Password, ".{6,}", R.string.invalidpassword);
        mauth=FirebaseAuth.getInstance();

        //register user
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {

    String textuseremail = useremail.getText().toString().trim();
    String textpassword = password.getText().toString().trim();
    String confirmpassword=confirmpass.getText().toString().trim();
                    if(textpassword.equals(confirmpassword)) {
    registeruser(textuseremail, textpassword);
}
else {
    Toast.makeText(login.this,"password does not match",Toast.LENGTH_SHORT).show();
}

                }
            }
        });
//google_sign in
        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, options);
        signInButton_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signIntent = client.getSignInIntent();
                startActivityForResult(signIntent, RC_SIGN_IN);
            }
        });

    }
    //firebase storage
            private void registeruser(final String textuseremail, final String textpassword) {
                mauth.createUserWithEmailAndPassword(textuseremail, textpassword)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(login.this, "signed in", Toast.LENGTH_SHORT).show();

                                    userID = mauth.getCurrentUser().getUid();
                                    DatabaseReference root= FirebaseDatabase.getInstance().getReference("users").child(userID).child("info");
                                    user user=new user(textuseremail,textpassword);
                                    root.setValue(user);
                                    startActivity(new Intent(login.this, MainActivity.class));
                                    finish();

                                }
                            }
                        });

            }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseGoogle(account.getIdToken());

            } catch (ApiException e) {
                Toast.makeText(login.this, "error is" + e, Toast.LENGTH_SHORT).show();


            }
        }
    }
    private void firebaseGoogle(String idToken) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
        mauth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mauth.getCurrentUser();
                    assert user != null;
                    userID = mauth.getCurrentUser().getUid();
                    DatabaseReference root= FirebaseDatabase.getInstance().getReference("users").child(userID).child("info");
                    user userinfo=new user(user.getEmail(),"nil");
                    root.setValue(userinfo);

                    Toast.makeText(getApplicationContext(), "Sign in done..", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(login.this, MainActivity.class));
                    finish();


                } else {
                    Toast.makeText(login.this, "google not linked ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    }