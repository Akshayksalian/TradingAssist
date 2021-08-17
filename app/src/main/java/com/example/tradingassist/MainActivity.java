package com.example.tradingassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button logout;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);

        // Initializing fire base auth into onCreate method.
        mAuth=FirebaseAuth.getInstance();

        logout.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,Login.class));
        });
    }

    // Overriding on start method.
    @Override
    protected void onStart() {
        super.onStart();

        // checking for user
        FirebaseUser user = mAuth.getCurrentUser();

        // If null taking them to login activity.
        if(user==null){
            startActivity(new Intent(MainActivity.this,Login.class));
        }
    }
}