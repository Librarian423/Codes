package org.techtown.se_project05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Student_attendance_add extends AppCompatActivity {

    private Spinner sch_year_add,sch_month_add,sch_day_add;
    private Button btn_add,btn_undo;
    private EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_attendance_add);

        et_name = findViewById( R.id.et_schedule_name );

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        sch_year_add = (Spinner)findViewById(R.id.sch_year_add);
        sch_month_add = (Spinner)findViewById(R.id.sch_month_add);
        sch_day_add = (Spinner)findViewById(R.id.sch_day_add);

        btn_add = findViewById(R.id.btn_schedule_check);

        btn_undo = findViewById(R.id.btn_schedule_undo);
        btn_undo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Student_schedule.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
    }


}