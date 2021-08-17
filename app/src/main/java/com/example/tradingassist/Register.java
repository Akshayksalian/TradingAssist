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

public class Register extends AppCompatActivity {

    EditText email,password;
    TextView login;
    Button register;

    // Using of fireBase Authentication class.
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        login = findViewById(R.id.rlogin);
        register = findViewById(R.id.lloginbutton);

        mAuth = FirebaseAuth.getInstance();

        // Setting a OnClickListener for register button
        register.setOnClickListener(view->{
            // this will call createUser method.
            createUser();
        });

        // Setting a OnClickListener for Login button
        login.setOnClickListener(view->{
            // This will navigate the user to login page
            startActivity(new Intent(Register.this,Login.class));
        });

    }

    private void createUser() {

        // Storing the value in string format.
        String eml = email.getText().toString();
        String pass = password.getText().toString();

        // Setting error if email is empty
        if(TextUtils.isEmpty(eml)){
            email.setError("Email can't be empty");
            email.requestFocus();

            // Setting error if password is empty.
        }else if(TextUtils.isEmpty(pass)) {
            password.setError("Password can't be empty");
            password.requestFocus();

            // Using firebase Authenticaiton class to register using createUserWithEmailAndPassword method.
        } else {
            mAuth.createUserWithEmailAndPassword(eml,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    // when everything is good showing them toast and taking user into the login page.
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"User registered successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Register.this,Login.class));

                        // if something goes wrong ,then we shows the user the error in the toast format.
                    } else {
                        Toast.makeText(Register.this,"Registeration Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}