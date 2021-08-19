package com.example.tradingassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    // Using of fireBase Authentication class.
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;

                switch (item.getItemId()){

                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nav_news:
                        fragment = new NewsFragment();
                        break;

                    case R.id.nav_socialmedia:
                        fragment = new SocialFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body,fragment).commit();

                return true;
            }
        });

        // Initializing fire base auth into onCreate method.
        mAuth=FirebaseAuth.getInstance();
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