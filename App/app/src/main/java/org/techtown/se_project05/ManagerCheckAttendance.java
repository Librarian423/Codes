package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.WeekView;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.HashMap;


public class ManagerCheckAttendance extends AppCompatActivity {
    CustomCalendar customCalendar;
    Calendar cal = Calendar.getInstance();
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_check_attendance);
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openManagerSelectAttendance();
            }
        });

        customCalendar = findViewById(R.id.customcalender);
        HashMap<Object, Property> hashMap = new HashMap<>();

        Property presentProp = new Property();
        presentProp.layoutResource = R.layout.attendance_present;
        presentProp.dateTextViewResource = R.id.text1;
        hashMap.put("present",presentProp);

        Property absentProp = new Property();
        absentProp.layoutResource = R.layout.attendance_absent;
        absentProp.dateTextViewResource = R.id.text1;
        hashMap.put("absent",absentProp);

        Property currentProp = new Property();
        currentProp.layoutResource = R.layout.attendance_current;
        currentProp.dateTextViewResource = R.id.text1;
        hashMap.put("current",currentProp);

        Property defaultProp = new Property();
        defaultProp.layoutResource = R.layout.attendance_default;
        defaultProp.dateTextViewResource = R.id.text1;
        hashMap.put("default",defaultProp);

        customCalendar.setMapDescToProp(hashMap);

        HashMap<Integer, Object> dateMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        dateMap.put(calendar.get(Calendar.DAY_OF_MONTH), "current");




        dateMap.put(3,"present");
        dateMap.put(1,"absent");
        dateMap.put(4,"present");

        customCalendar.setDate(calendar, dateMap);


    }
    public void openManagerSelectAttendance(){
        Intent intent = new Intent(this, ManagerSelectAttendance.class);
        startActivity(intent);
    }
}
