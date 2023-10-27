package com.Kursach.bookingsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import com.Kursach.bookingsystem.models.Matches;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class AdminHome extends AppCompatActivity {
    public static ArrayList<Matches> matchesArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    DatabaseReference ref;
    private MatchAdapter matchAdapter;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Button btnAddMatch = findViewById(R.id.btn_addMatch);
        Button btnLogOut = findViewById(R.id.btn_logout_admin);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_admin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Matches");
        mAuth = FirebaseAuth.getInstance();
        MatchAdapter.OnMatchesClickListener matchClickListener = new MatchAdapter.OnMatchesClickListener() {
            @Override
            public void onMatchesClick(Matches matches, int position) {
                Intent intent = new Intent(AdminHome.this, EditingMatch.class);
                intent.putExtra("IdOfItem", matchesArrayList.get(position).getId());
                intent.putExtra("nameFirst", matchesArrayList.get(position).getNameFirstTeam());
                intent.putExtra("nameSecond", matchesArrayList.get(position).getNameSecondTeam());
                intent.putExtra("nameStadium", matchesArrayList.get(position).getStadium());
                intent.putExtra("dateGame", matchesArrayList.get(position).getDateOfGame());
                intent.putExtra("timeGame", matchesArrayList.get(position).getTimeOfGame());
                startActivity(intent);
            }
        };
        matchAdapter = new MatchAdapter(AdminHome.this, matchesArrayList, matchClickListener);
        recyclerView.setAdapter(matchAdapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(matchesArrayList.size() > 0 )
                    matchesArrayList.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Matches match = ds.getValue(Matches.class);
                    assert match != null;
                    matchesArrayList.add(match);
                }
                matchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(AdminHome.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure to Exit")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                Intent intent = new Intent(AdminHome.this, MainActivity.class);
                                startActivity(intent);
                                finish();
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
        btnAddMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, AddMatch.class);
                startActivity(intent);
            }
        });
    }



}