package com.Kursach.bookingsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Kursach.bookingsystem.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.*;
import java.util.List;
import java.util.Objects;


public class PersonFragment extends Fragment {
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView NickName;
    private TextView Email;
    public PersonFragment(){
        // require a empty public constructor
    }

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment_person = inflater.inflate(R.layout.fragment_person, container, false);
        Button LogOut = fragment_person.findViewById(R.id.btnLogOut);
        Button target = fragment_person.findViewById(R.id.btn_shop);
        NickName =(TextView) fragment_person.findViewById(R.id.nick);
        Email = (TextView) fragment_person.findViewById(R.id.email_profil);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference data = mDatabase.getReference("Logins/" + FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        mAuth = FirebaseAuth.getInstance();

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                NickName.setText(user.getName());
                Email.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//TODO
            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure to Exit")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                startActivity(intent);
            }
        });

        return fragment_person;
    }

}