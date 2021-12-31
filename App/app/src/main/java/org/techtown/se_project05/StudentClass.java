package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentClass extends AppCompatActivity {
    private TextView tv_id;
    private Button btn_class1, btn_class2, btn_back;
    private ArrayList<String> classes;
    private ArrayList<Integer> classesID;
    LinearLayout linear1;
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView(R.layout.student_class);
        tv_id = findViewById(R.id.tv_id);
        linear1 = findViewById(R.id.btn_layout);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        System.out.println("classes: " + classes);
        System.out.println("classesID: " + classesID);
        tv_id.setText(userID);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        if (classes.size() == 0){
            TextView textView = new TextView(this);
            textView.setText("수업이 존재하지 않습니다");
            textView.setTextSize(30);
            textView.setLayoutParams(lp);
            linear1.addView(textView);
        }
        for(int i = 0; i< classes.size(); i++){

            lp.width = 800;
            Button btn = new Button(this);
            btn.setLayoutParams(lp);
            btn.setText(classes.get(i));
            btn.setId(i);
            final int num = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(), StudentFunc.class);
                    intent.putExtra("userID", userID);
                    intent.putExtra("classes", classes);
                    intent.putExtra("classesID", classesID);
                    intent.putExtra("curClass", classes.get(num));
                    intent.putExtra("curClassID", classesID.get(num));
                    startActivity(intent);
                }
            });
            linear1.addView(btn);
        }

        btn_back = findViewById(R.id.stu_class_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentInit.class);
                intent.putExtra("userID", userID);
                intent.putExtra("classes", classes);
                intent.putExtra("classesID", classesID);
                startActivity(intent);
            }
        });

    }
}
