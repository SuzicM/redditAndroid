package com.example.reddit20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private TextView registration;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etDisplayName;
    private EditText etDescription;

    private Button btnSignUp;
    private Button btnLogIn;
    private Button btnBack;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        etUsername = (EditText) findViewById(R.id.username);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etDisplayName = (EditText) findViewById(R.id.displayName);
        etDescription = (EditText) findViewById(R.id.description);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                signUp();
                break;
            case R.id.btnLogIn:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnBack:
                startActivity(new Intent(this, FirstWindow.class));
                break;
        }
    }

    private void signUp() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String displayName = etDisplayName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        //validations

        if(username.isEmpty()){
            etUsername.setError("Username field cannot be empty!");
            etUsername.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Email is not valid!");
            etEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPassword.setError("Password field cannot be empty!");
            etPassword.requestFocus();
            return;
        }

        if(displayName.isEmpty()){
            etDisplayName.setError("Display name field cannot be empty!");
            etDisplayName.requestFocus();
            return;
        }

        if(description.isEmpty()){
            etDescription.setError("Description field cannot be empty!");
            etDescription.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(username, email, password, displayName, description);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "Registered successfully.",Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(SignUp.this, "Registration failed!",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUp.this, "Registration failed!",Toast.LENGTH_LONG).show();

                        }

                    }
                });
    }
}