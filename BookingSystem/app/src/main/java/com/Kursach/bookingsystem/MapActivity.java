package com.Kursach.bookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import android.view.MenuItem;

public class MapActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
    HomeFragment firstFragment = new HomeFragment();
    PersonFragment thirdFragment = new PersonFragment();
    StadiumsFragment StadiumsFragment = new StadiumsFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;

            case R.id.person:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;
            case R.id.stadiums:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, StadiumsFragment).commit();
                return true;
        }
        return false;
    }
}