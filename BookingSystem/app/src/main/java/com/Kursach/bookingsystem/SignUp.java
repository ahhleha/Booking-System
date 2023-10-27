package com.Kursach.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Kursach.bookingsystem.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button btn2_signup;
    EditText user_email, pass_word, user_name, passcheck;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference data;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDatabase = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app");
        data = mDatabase.getReference();
        user_name= findViewById(R.id.et_name);
        user_email =findViewById(R.id.et_email);
        pass_word=findViewById(R.id.et_password);
        passcheck = findViewById(R.id.et_repassword);
        btn2_signup=findViewById(R.id.btn_register);
        mAuth=FirebaseAuth.getInstance();
        btn2_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_email.getText().toString().trim();
                String password= pass_word.getText().toString().trim();
                String name = user_name.getText().toString().trim();
                String check = passcheck.getText().toString().trim();

                if(name.isEmpty())
                {
                    user_name.setError("name is empty");
                    user_name.requestFocus();
                    return;
                }
                if(email.isEmpty())
                {
                    user_email.setError("Email is empty");
                    user_email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    user_email.setError("Enter the valid email address");
                    user_email.requestFocus();
                    return;
                }
                String em = email;
                int pos = em.length() - em.indexOf("@gmail.com");
                if (TextUtils.isEmpty(email)||pos !=10) {
                    user_email.setError("Enter your email consists @gmail.com");
                    user_email.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass_word.setError("Enter the password");
                    pass_word.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    pass_word.setError("Length of the password should be more than 6");
                    pass_word.requestFocus();
                    return;
                }
                if(password.equals(check) == false){
                    passcheck.setError("Пароли не совпадают");
                    passcheck.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user1 = new User(user_name.getText().toString().trim(), user_email.getText().toString().trim(), mAuth.getUid().toString().trim());
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            String personUid= user.getUid();
                            data.child("Logins").child(personUid).setValue(user1);
                            Toast.makeText(SignUp.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUp.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
