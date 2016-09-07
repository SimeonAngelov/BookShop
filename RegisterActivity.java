package com.example.daskalski.warcraftbookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.daskalski.warcraftbookstore.model.UsersManager;

public class RegisterActivity extends AppCompatActivity {

    public static final int RESULT_REG_SUCCESSFULL = 10;

    EditText usernameET;
    EditText passwordET;
    EditText password2ET;
    EditText emailET;
    EditText addressET;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = (EditText) findViewById(R.id.regUserET);
        passwordET = (EditText) findViewById(R.id.regPassET);
        password2ET = (EditText) findViewById(R.id.regPass2ET);
        emailET = (EditText) findViewById(R.id.regemailET);
        addressET = (EditText) findViewById(R.id.regAddrET);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameET.getText().toString().trim();
                Editable pass1 = passwordET.getText();
                Editable pass2 = password2ET.getText();
                String email = emailET.getText().toString().trim();
                String addr = addressET.getText().toString().trim();
                if(username.isEmpty()){
                    usernameET.setError("Username is compulsory");
                    usernameET.requestFocus();
                    return;
                }
                if(pass1.length() == 0){
                    passwordET.setError("Password is compulsory");
                    passwordET.requestFocus();
                    return;
                }
                if(pass2.length() == 0){
                    password2ET.setError("Second password is compulsory");
                    password2ET.requestFocus();
                    return;
                }
                if(addr.isEmpty()){
                    addressET.setError("Address is compulsory");
                    addressET.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    emailET.setError("Email is compulsory");
                    emailET.requestFocus();
                    return;
                }

                if(passwordsMissmatch(pass1, pass2)){
                    passwordET.setError("Passwords missmatch");
                    passwordET.setText("");
                    password2ET.setText("");
                    passwordET.requestFocus();
                    return;
                }

                if(UsersManager.getInstance(RegisterActivity.this).existsUser(username)) {
                    usernameET.setError("User already exists");
                    usernameET.setText("");
                    usernameET.requestFocus();
                    return;
                }

                UsersManager.getInstance(RegisterActivity.this).regUser(RegisterActivity.this, username, pass1.toString(), email, addr);



                Intent intent = new Intent();
                intent.putExtra("username", usernameET.getText().toString());
                intent.putExtra("password", passwordET.getText().toString());
                setResult(RESULT_REG_SUCCESSFULL, intent);
                finish();
            }
        });

    }

    private boolean passwordsMissmatch(Editable pass1, Editable pass2) {
        if(pass1.length() != pass2.length()){
            return true;
        }
        for(int i = 0 ; i < pass1.length(); i++){
            if(pass1.charAt(i) != pass2.charAt(i)){
                return true;
            }
        }
        return false;
    }
}
