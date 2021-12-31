package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentFunc extends AppCompatActivity {
    private TextView tv_id, class_id;
    private ArrayList<String> classes;
    private ArrayList<Integer> classesID;
    private String curClass, userID;
    private int curClassID;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_func);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 1);
        TextView tv_id = findViewById(R.id.tv_id);
        TextView tv_class = findViewById(R.id.tv_class);
        tv_id.setText("학생 ID: " + userID);
        tv_class.setText(curClass);

        Button check_bulletin = findViewById(R.id.student_func_button1);
        Button check_attendance = findViewById(R.id.student_func_button2);

        Button change_class = findViewById(R.id.change_class);

        check_bulletin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), StudentBulletin.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                intent.putExtra("curClass", curClass);
                intent.putExtra("curClassID", curClassID);
                startActivity(intent);
            }
        });

        check_attendance.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), Student_attendance.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                intent.putExtra("curClass", curClass);
                intent.putExtra("curClassID", curClassID);
                startActivity(intent);
            }
        });

        change_class.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), StudentClass.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                intent.putExtra("curClass", curClass);
                intent.putExtra("curClassID", curClassID);
                startActivity(intent);
            }
        });
    }
}
