package com.example.reddit20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText etlEmail;
    private EditText etlPassword;
    private Button SignUp;
    private Button Back;
    private Button Login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignUp = (Button) findViewById(R.id.btnSignUp);
        SignUp.setOnClickListener(this);

        Login = (Button) findViewById(R.id.btnLogIn);
        Login.setOnClickListener(this);

        Back = (Button) findViewById(R.id.btnBack);
        Back.setOnClickListener(this);

        etlEmail = (EditText) findViewById(R.id.etlEmail);
        etlPassword = (EditText) findViewById(R.id.etlPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.btnLogIn:
                UserLogIn();
                break;
            case R.id.btnBack:
                startActivity(new Intent(this, FirstWindow.class));
                break;
        }

    }

    private void UserLogIn() {

        String email = etlEmail.getText().toString().trim();
        String password = etlPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etlEmail.setError("Email is not valid!");
            etlEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etlPassword.setError("Password field cannot be empty!");
            etlPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, Home.class));

                }else{
                    Toast.makeText(Login.this, "Failed to login!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}