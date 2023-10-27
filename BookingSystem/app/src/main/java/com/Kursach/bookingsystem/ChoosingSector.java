package com.Kursach.bookingsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.google.firebase.database.DatabaseReference;

public class ChoosingSector extends AppCompatActivity {
    DatabaseReference ref;
    String sectora1, sectora2, sectorc1, sectorb1, sectorb2, sectorb3;
    boolean checkSector = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_sector);

        long Id = getIntent().getExtras().getLong("IdMatch");
        String firstName = getIntent().getExtras().getString("nameFirst");
        String secondName = getIntent().getExtras().getString("nameSecond");
        sectorInitialize();
        Button btn_sector = findViewById(R.id.btn_sector);
        btn_sector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(ChoosingSector.this, btn_sector);
                    popupMenu.getMenuInflater().inflate(R.menu.sector_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            Intent intent = new Intent(ChoosingSector.this, Choosing_seat.class);
                            intent.putExtra("nameFirstTeam", firstName);
                            intent.putExtra("nameSecondTeam", secondName);
                            switch (item.getItemId()) {
                                case R.id.sector_a1:
                                    String num= "sectorA1";
                                    intent.putExtra("sectorName", num);
                                    intent.putExtra("sectorNum", sectora1);
                                    intent.putExtra("IdMatch", Id);
                                    startActivityForResult(intent, 1);
                                    return true;
                                case R.id.sector_a2:
                                    String sectorA2= "sectorA2";
                                    intent.putExtra("sectorName", sectorA2);
                                    intent.putExtra("sectorNum", sectora2);
                                    intent.putExtra("IdMatch", Id);
                                    startActivityForResult(intent, 1);
                                    return true;
                                case R.id.sector_c1:
                                    String sectorC1= "sectorC1";
                                    intent.putExtra("sectorName", sectorC1);
                                    intent.putExtra("sectorNum", sectorc1);
                                    intent.putExtra("IdMatch", Id);
                                    startActivityForResult(intent, 1);
                                    return true;
                                case R.id.sector_b1:
                                    String sectorB1= "sectorB1";
                                    intent.putExtra("sectorName", sectorB1);
                                    intent.putExtra("sectorNum", sectorb1);
                                    intent.putExtra("IdMatch", Id);
                                    startActivityForResult(intent, 1);
                                    return true;
                                case R.id.sector_b2:
                                    String sectorB2= "sectorB2";
                                    intent.putExtra("sectorName", sectorB2);
                                    intent.putExtra("sectorNum", sectorb2);
                                    intent.putExtra("IdMatch", Id);
                                    startActivityForResult(intent, 1);
                                    return true;
                                case R.id.sector_b3:
                                    String sectorB3= "sectorB3";
                                    intent.putExtra("sectorName", sectorB3);
                                    intent.putExtra("sectorNum", sectorb3);
                                    intent.putExtra("IdMatch", Id);
                                    startActivityForResult(intent, 1);
                                    return true;
                                default:
                                    return false;
                            }


                        }
                    });
                    popupMenu.show();
                }
        });

    }
    private void sectorInitialize() {
        sectora1 = getIntent().getExtras().getString("SeatsOfSectorA1");
        sectora2 = getIntent().getExtras().getString("SeatsOfSectorA2");
        sectorc1 = getIntent().getExtras().getString("SeatsOfSectorC1");
        sectorb1 = getIntent().getExtras().getString("SeatsOfSectorB1");
        sectorb2 = getIntent().getExtras().getString("SeatsOfSectorB2");
        sectorb3 = getIntent().getExtras().getString("SeatsOfSectorB3");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == 1){
            finish();
        }
    }

}