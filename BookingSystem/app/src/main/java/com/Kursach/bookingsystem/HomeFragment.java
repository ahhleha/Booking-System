package com.Kursach.bookingsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Kursach.bookingsystem.models.Matches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private final ArrayList<Matches> matchesArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference ref;
    private MatchAdapter matchAdapter;
    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        // определяем слушателя нажатия элемента в списке
        MatchAdapter.OnMatchesClickListener matchClickListener = new MatchAdapter.OnMatchesClickListener() {
            @Override
            public void onMatchesClick(Matches matches, int position) {
                Intent intent = new Intent(getContext(), ChoosingSector.class);
                intent.putExtra("SeatsOfSectorA1", matches.getSectorA1());
                intent.putExtra("SeatsOfSectorA2", matches.getSectorA2());
                intent.putExtra("SeatsOfSectorC1", matches.getSectorC1());
                intent.putExtra("SeatsOfSectorB1", matches.getSectorB1());
                intent.putExtra("SeatsOfSectorB2", matches.getSectorB2());
                intent.putExtra("SeatsOfSectorB3", matches.getSectorB3());
                intent.putExtra("IdMatch", matches.getId());
                intent.putExtra("nameFirst", matches.getNameFirstTeam());
                intent.putExtra("nameSecond", matches.getNameSecondTeam());
                startActivity(intent);
            }
        };
        matchAdapter = new MatchAdapter(getContext(), matchesArrayList, matchClickListener);
        recyclerView.setAdapter(matchAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        matchesArrayList.clear();
        dataInitialize();
    }

    private void dataInitialize() {
        ref = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Matches");
        ValueEventListener vListener = new ValueEventListener() {
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
        };
        ref.addValueEventListener(vListener);
    }
}