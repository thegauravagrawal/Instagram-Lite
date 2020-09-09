package com.inficreations.instagramlite.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.inficreations.instagramlite.R;
import com.inficreations.instagramlite.interfaces.LoginListener;
import com.inficreations.instagramlite.model.FirebaseDB;
import com.inficreations.instagramlite.model.SharedPreferencesLocalDB;
import com.inficreations.instagramlite.viewmodel.InstagramRepo;

import java.io.IOException;

import dev.niekirk.com.instagram4android.Instagram4Android;
import dev.niekirk.com.instagram4android.requests.payload.InstagramLoggedUser;
import dev.niekirk.com.instagram4android.requests.payload.InstagramLoginResult;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    //Declaring XML Views
    TextInputLayout usernameLayout, passwordLayout;
    TextInputEditText username, password;
    TextView forgetPassword;
    Button loginButton;
    ProgressBar progressBar;

    private InstagramRepo instagramRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing XML Views
        usernameLayout = findViewById(R.id.username_layout);
        username = findViewById(R.id.username);
        passwordLayout = findViewById(R.id.password_layout);
        password = findViewById(R.id.password);
        forgetPassword = findViewById(R.id.forget_password);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_circular);

        //Click Listener on Forget Password
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Forwarding to the forget password page in web browser
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/accounts/password/reset/?hl=en")));
            }
        });

        //Click Listener on Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling Function to validate credentials
                credentialValidation();
            }
        });

        instagramRepo = new InstagramRepo(this);

    }

    // Function for validating the credentials
    private void credentialValidation() {
        String userStr = username.getText().toString().trim();
        String passStr = password.getText().toString().trim();

        //Checking if username is empty
        if (userStr.isEmpty()) {
            usernameLayout.setError("Enter Username");
        }
        //Checking if username contains "@"
        else if (userStr.contains("@")) {
            username.setError(null);
            username.setError("Invalid Username");
        }
        //Checking if password is empty
        else if (passStr.length() == 0) {
            usernameLayout.setError(null);
            username.setError(null);
            passwordLayout.setError("Enter Password");
        }
        //send username and password to login()
        else {
            login(userStr, passStr);
        }
    }

    //Function to Login
    private void login(String username, String password) {
        instagramRepo.instagramLogin(username, password);
    }

    private void sendToHomeScreen() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("isComingFromLogin", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onStartLogin() {
        loginButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {

        loginButton.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        Toasty.error(LoginActivity.this, message).show();

    }

    @Override
    public void onSuccess(InstagramLoggedUser loggedUser, String password) {
        SharedPreferencesLocalDB.setUserData(LoginActivity.this, loggedUser, password);
        FirebaseDB.addUserToFirebase();
        sendToHomeScreen();
    }


}
