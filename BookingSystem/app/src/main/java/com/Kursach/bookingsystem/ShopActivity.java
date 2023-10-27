package com.Kursach.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopActivity extends AppCompatActivity {
    private DatabaseReference dataOr, match;
    FirebaseAuth auth;
    List<Order> individualOrder = new ArrayList<>();
    List <Order> allOrders = new ArrayList<>();
    ArrayList<String> indArr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ListView orderList = findViewById(R.id.listViewOrder);
        auth = FirebaseAuth.getInstance();
        dataOr = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Orders");
        match = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Matches/");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShopActivity.this, android.R.layout.simple_list_item_1, indArr);
        orderList.setAdapter(arrayAdapter);
        dataOr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                allOrders.clear();
                individualOrder.clear();
                for(DataSnapshot i: snapshot.getChildren()) {

                    Order order = i.getValue(Order.class);
                    allOrders.add(order);
                    assert order != null;
                    if(Objects.equals(order.getUserURL(), auth.getCurrentUser().getUid().toString())) {
                        individualOrder.add(order);
                    }
                }
                for(Order c: individualOrder){
                    indArr.add(c.getFirstTeam()+" -:- "+c.getSecondTeam() +"\n"+c.getSectorName()+" seats: " + c.getValueOfSeats());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}