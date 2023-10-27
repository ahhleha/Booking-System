package com.Kursach.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Kursach.bookingsystem.models.Matches;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Choosing_seat extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;
    DatabaseReference ref;
    List <Order> ors = new ArrayList<>();
    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;
    private DatabaseReference data, dataOr, dataTr;
    List<Integer> arr = new ArrayList<>();
    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_seat);
        layout = findViewById(R.id.layoutSeat);
        Button confirm = findViewById(R.id.btn_confirmSeat);

        String firstName = getIntent().getExtras().getString("nameFirstTeam");
        String secondName = getIntent().getExtras().getString("nameSecondTeam");
        String seats = getIntent().getStringExtra("sectorNum");
        long IdOfMatch = getIntent().getExtras().getLong("IdMatch");
        String str= Long.toString(IdOfMatch);

        ref = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Matches/"+str);
        dataOr = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Orders");

        char[] sector = seats.toCharArray();

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layoutSeat.addView(layout);
        int count = 0;

        for (int index = 1; index < sector.length; index++) {
            if(count%10 ==0){
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            }
            if (sector[index] == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (sector[index] == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            }
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arr.size()!=0) {
                    int a;
                    for (int i = 0; i < arr.size(); i++) {
                        a = arr.get(i);
                        sector[a] = 'U';
                    }
                    String newseats = new String(sector);
                    String sName = getIntent().getExtras().getString("sectorName", "");
                    if (sName.equals("sectorA1")) {
                        ref.child("sectorA1").setValue(newseats);
                    }
                    else if (sName.equals("sectorA2")) {
                        ref.child("sectorA2").setValue(newseats);
                    }
                    else if (sName.equals("sectorC1")) {
                        ref.child("sectorC1").setValue(newseats);
                    }
                    else if (sName.equals("sectorB1")) {
                        ref.child("sectorB1").setValue(newseats);
                    }
                    else if (sName.equals("sectorB2")) {
                        ref.child("sectorB2").setValue(newseats);
                    }
                    else if (sName.equals("sectorB3")) {
                        ref.child("sectorB3").setValue(newseats);
                    }
                    ors.clear();
                    dataOr.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot i: snapshot.getChildren()) {

                                Order order = i.getValue(Order.class);
                                ors.add(order);
                            }
                            SaveInOrder(arr, str, sName, firstName, secondName);
                            setResult(1);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
                else{
                    Toast.makeText(Choosing_seat.this,"Сначала выберите место", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.ic_seats_book);
                for(int i =0; i<arr.size(); i++)
                {
                    if(arr.get(i) == view.getId()){
                        arr.remove(i);
                    }
                }
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                arr.add(view.getId());
                view.setBackgroundResource(R.drawable.ic_seats_selected);
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }

    public void SaveInOrder(@NonNull List<Integer> arr, String idM, String sName, String firstName, String secondName){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String seatsOfUser = "";
        for(int i = 0; i<arr.size(); i++){
            seatsOfUser += arr.get(i);
            seatsOfUser+=" ";
        }
        Order newOrder = new Order(idM, auth.getCurrentUser().getUid().toString(), sName, seatsOfUser, firstName, secondName);
        ors.add(newOrder);
        dataOr.setValue(ors);
    }

}

