package com.Kursach.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Kursach.bookingsystem.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private List<String> ids = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Admins");
        Button btnRegister = findViewById(R.id.btnRegistration);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        User user = dataSnapshot.getValue(User.class);
                        ids.add(user.getUid());
                    }
                    Intent intent;
                    if(ids.contains(auth.getCurrentUser().getUid())){
                        intent = new Intent(MainActivity.this, AdminHome.class);
                    }
                    else {
                        intent = new Intent(MainActivity.this, MapActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivityForResult(intent, 1);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1) {
            finish();
        }
    }

}