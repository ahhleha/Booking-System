package com.Kursach.bookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    EditText username, password;
    Button loginbutton;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        username = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        loginbutton = findViewById(R.id.btn_login);



        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if(TextUtils.isEmpty(username.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Введите почту", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().length() <= 5 ){
                    Toast.makeText(getApplicationContext(), "Введите пароль, содержащий более 5 символов", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                setResult(1);
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            finish();
    }
}