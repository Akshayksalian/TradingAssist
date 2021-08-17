package com.example.tradingassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email,password;
    TextView register;
    Button login;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        login = findViewById(R.id.lloginbutton);
        register = findViewById(R.id.lregister);

        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(view->{
            loginUser();
        });

        register.setOnClickListener(view->{
            startActivity(new Intent(Login.this,Register.class));
        });
    }

    private void loginUser() {
        String eml = email.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(eml)){
            email.setError("Email can't be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(pass)) {
            password.setError("Password can't be empty");
            password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(eml,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"User login successfull",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    } else {
                        Toast.makeText(Login.this,"Login Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}