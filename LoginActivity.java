package com.example.daskalski.warcraftbookstore;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.daskalski.warcraftbookstore.model.UsersManager;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_REG_USER = 10;
    Button dialButton;
    Button webButton;
    Button loginButton;
    Button registerButton;
    EditText usernameET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dialButton = (Button) findViewById(R.id.dialButton);
        webButton = (Button) findViewById(R.id.websiteButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        usernameET = (EditText) findViewById(R.id.loginUsernameET);
        passwordET = (EditText) findViewById(R.id.loginPasswordET);

        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0878665544"));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ittalents.bg"));
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                usernameET.setError(null);
                startActivityForResult(intent, REQUEST_REG_USER);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString().trim();
                String password = passwordET.getText().toString();
                if(username.isEmpty()){
                    usernameET.setError("Username is compulsory");
                    usernameET.requestFocus();
                    return;
                }
                if(password.length() == 0){
                    passwordET.setError("Password is compulsory");
                    passwordET.requestFocus();
                    return;
                }
                if(!UsersManager.getInstance(LoginActivity.this).validalteLogin(username, password)){
                    usernameET.setError("Invalid credentials");
                    usernameET.setText("");
                    passwordET.setText("");
                    usernameET.requestFocus();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this, MainDrawerActivity.class);
                intent.putExtra("loggedUser", username);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_REG_USER){
            if(resultCode == RegisterActivity.RESULT_REG_SUCCESSFULL){
                String user = data.getStringExtra("username");
                String pass = data.getStringExtra("password");
                usernameET.setText(user);
                passwordET.setText(pass);
            }
        }
    }
}
