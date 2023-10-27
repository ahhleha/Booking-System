package com.Kursach.bookingsystem;

import static com.Kursach.bookingsystem.AdminHome.matchesArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Kursach.bookingsystem.models.Matches;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditingMatch extends AppCompatActivity implements View.OnClickListener{
    DatabaseReference ref;
    private Button btnDatePicker, btnTimePicker, btnSaveChange, btnDelItem;
    private EditText editTextDate, editTextTime;
    private EditText nameFirstTeam, nameSecondTeam, stadium;
    Long ID;
    // делаем переменные даты/времени полями
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_match);
        String fTeam = getIntent().getExtras().getString("nameFirst");
        String sTeam = getIntent().getExtras().getString("nameSecond");
        String namestdm = getIntent().getExtras().getString("nameStadium");
        String dateOfGame = getIntent().getExtras().getString("dateGame");
        String timeOfGame = getIntent().getExtras().getString("timeGame");
        ID = getIntent().getExtras().getLong("IdOfItem");


        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        btnSaveChange  = (Button) findViewById(R.id.btn_update);
        btnDelItem = (Button) findViewById(R.id.btn_delete);
        editTextDate = (EditText) findViewById(R.id.date_match);
        editTextTime = (EditText) findViewById(R.id.time_match);
        nameFirstTeam = (EditText) findViewById(R.id.fst_team);
        nameSecondTeam = (EditText) findViewById(R.id.scnd_team);
        stadium = (EditText) findViewById(R.id.add_stadium);


        nameFirstTeam.setText(fTeam);
        nameSecondTeam.setText(sTeam);
        stadium.setText(namestdm);
        editTextDate.setText(dateOfGame);
        editTextTime.setText(timeOfGame);


        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnDelItem.setOnClickListener(this);
        btnSaveChange.setOnClickListener(this);

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
            case R.id.btn_update:
                updateItem();
                break;
            case R.id.btn_delete:
                deleteItem();
                break;

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

    private void deleteItem(){
        int index = Integer.parseInt(String.valueOf(ID));
        ref = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Matches");
        matchesArrayList.remove(index);
        ref.setValue(matchesArrayList);
        Toast.makeText(EditingMatch.this,"Матч удалён", Toast.LENGTH_LONG).show();
        startActivity(new Intent(EditingMatch.this, AdminHome.class));
        finish();
    }

    private void updateItem(){
        int indexUpdate = Integer.parseInt(String.valueOf(ID));
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
        matchesArrayList.get(indexUpdate).setNameFirstTeam(nameFirst);
        matchesArrayList.get(indexUpdate).setNameSecondTeam(nameSecond);
        matchesArrayList.get(indexUpdate).setStadium(nameStadium);
        matchesArrayList.get(indexUpdate).setDateOfGame(date);
        matchesArrayList.get(indexUpdate).setTimeOfGame(time);
        ref = FirebaseDatabase.getInstance("https://bookingsystem-58da2-default-rtdb.europe-west1.firebasedatabase.app").getReference("Matches");
        ref.setValue(matchesArrayList);
        Toast.makeText(EditingMatch.this,"Матч отредактирован", Toast.LENGTH_LONG).show();
        startActivity(new Intent(EditingMatch.this, AdminHome.class));
        finish();
    }
}