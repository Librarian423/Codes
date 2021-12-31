package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManagerFunc extends AppCompatActivity {
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<Integer> classesID = new ArrayList<>();
    private String curClass, userID;
    private int curClassID;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_func);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 1);
        TextView tv_id = findViewById(R.id.tv_id);
        TextView tv_class = findViewById(R.id.tv_class);
        tv_id.setText("관리자 ID: " + userID);
        tv_class.setText( curClass);

        Button add_student = findViewById(R.id.manager_func_button1);
        Button modify_calendar = findViewById(R.id.manager_func_button2);
        Button add_bulletin = findViewById(R.id.manager_func_button3);

        Button delete_class = findViewById(R.id.delete_class);
        Button change_class = findViewById(R.id.change_class);

        add_student.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), ManagerAddStudent.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                intent.putExtra("curClass", curClass);
                intent.putExtra("curClassID", curClassID);
                startActivity(intent);

            }
        });
        modify_calendar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), ManagerAttendanceTemp.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                intent.putExtra("curClass", curClass);
                intent.putExtra("curClassID", curClassID);
                startActivity(intent);

            }
        });

        add_bulletin.setOnClickListener(new View.OnClickListener(){
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

        delete_class.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), ManagerDeleteClass.class);
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
                Intent intent= new Intent(getApplicationContext(), ManagerInit.class);
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
