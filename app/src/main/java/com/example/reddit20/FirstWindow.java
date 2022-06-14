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

public class FirstWindow extends AppCompatActivity implements View.OnClickListener{

    private Button signUp;
    private Button blogIn;
    private Button guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_window);

        signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

        blogIn = (Button) findViewById(R.id.btnLogIn);
        blogIn.setOnClickListener(this);

        guest = (Button) findViewById(R.id.btnGuest);
        guest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUp:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.btnLogIn:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnGuest:
                startActivity(new Intent(this, Home.class));
                break;
        }

    }

}