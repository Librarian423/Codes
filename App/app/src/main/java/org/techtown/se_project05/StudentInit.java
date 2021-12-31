package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentInit extends AppCompatActivity {

    private TextView tv_id;
    private Button btn_class, btn_plan,btn_logout;
    private ArrayList<String> classes;
    private ArrayList<Integer> classesID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.student_main);

        tv_id = findViewById(R.id.tv_id);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        tv_id.setText(userID);

        btn_class = findViewById(R.id.stud_init_button1);
        btn_class.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentClass.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                startActivity(intent);
            }
        });
        btn_plan = findViewById(R.id.stud_init_button2);
        btn_plan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Student_schedule.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                startActivity(intent);
            }
        });

        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}