package com.Kursach.bookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Kursach.bookingsystem.models.Matches;
import com.Kursach.bookingsystem.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMatch extends AppCompatActivity implements View.OnClickListener{
    private Button btnDatePicker, btnTimePicker, btnAddMatches;
    private EditText editTextDate, editTextTime;
    private EditText nameFirstTeam, nameSecondTeam, stadium;
    // делаем переменные даты/времени полями, т.к. в реальных
    // приложениях они чаще всего используются и в других местах.
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        btnAddMatches = (Button) findViewById(R.id.add_match_btn);
        editTextDate = (EditText) findViewById(R.id.date_match);
        editTextTime = (EditText) findViewById(R.id.time_match);
        nameFirstTeam = (EditText) findViewById(R.id.fst_team);
        nameSecondTeam = (EditText) findViewById(R.id.scnd_team);
        stadium = (EditText) findViewById(R.id.add_stadium);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnAddMatches.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_date:
                // вызываем диалог с выбором даты
                callDatePicker();
                break;

            case R.id.btn_time:
                // вызываем диалог с выбором времени
                callTimePicker();
                break;
            case R.id.add_match_btn:
                addMatchbtn();

        }
    }


    private void callTimePicker() {
        // получаем текущее время
        final Calendar cal = Calendar.getInstance();
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        // инициализируем диалог выбора времени текущими значениями
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String editTextTimeParam = hourOfDay + " : " + minute;
                        editTextTime.setText(editTextTimeParam);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void callDatePicker() {
        // получаем текущую дату
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        // инициализируем диалог выбора даты текущими значениями
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                        editTextDate.setText(editTextDateParam);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void addMatchbtn() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Matches");
        List<Matches> ids = new ArrayList<>();
        String nameFirst = nameFirstTeam.getText().toString();
        String nameSecond= nameSecondTeam.getText().toString();
        String nameStadium = stadium.getText().toString();
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();;
        if(nameFirst.isEmpty())
        {
            nameFirstTeam.setError("name of first team is empty");
            nameFirstTeam.requestFocus();
            return;
        }
        if(nameSecond.isEmpty())
        {
            nameSecondTeam.setError("name of second team is empty");
            nameSecondTeam.requestFocus();
            return;
        }
        if(nameStadium.isEmpty())
        {
            stadium.setError("name of stadium is empty");
            stadium.requestFocus();
            return;
        }
        if(date.isEmpty())
        {
            editTextDate.setError("date is empty");
            editTextDate.requestFocus();
            return;
        }
        if(time.isEmpty())
        {
            editTextTime.setError("date is empty");
            editTextTime.requestFocus();
            return;
        }
        String sectorA1 = "AAAAAAAAAAAAAAAAAAAAA";
        String sectorA2 = "AAAAAAAAAAAAAAAAAAAAA";
        String sectorC1 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String sectorB1 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String sectorB2 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String sectorB3 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        ids.clear();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Matches f = dataSnapshot.getValue(Matches.class);
                    ids.add(f);
                }
                long newMatchId = ids.size();
                Matches newMatch = new Matches(newMatchId, nameFirst, nameSecond, date, time, nameStadium, sectorA1, sectorA2, sectorC1, sectorB1, sectorB2, sectorB3);
                ids.add(newMatch);
                mDatabase.setValue(ids);
                Toast.makeText(AddMatch.this,"Матч добавлен в базу данных", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddMatch.this, AdminHome.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}