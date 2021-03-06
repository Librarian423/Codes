package org.techtown.se_project05;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Student_attendance extends AppCompatActivity {

    public String fname =null;
    public String str=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public TextView diaryTextView,textView2,textView3;
    public TextView contextEditText;
    private ArrayList<String> classes;
    private ArrayList<Integer> classesID;
    private String curClass, userID;
    private int curClassID;
    private int attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_student);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String result;
                if(checkedId == R.id.radioButton5){
                    attendance = 1;
                }else{
                    attendance = 2;
                }
            }
        });


        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 1);
        calendarView=findViewById(R.id.calendarView);
        textView3 = findViewById(R.id.textView3);
        del_Btn=findViewById(R.id.del_Btn);
        cha_Btn=findViewById(R.id.cha_Btn);
        contextEditText=findViewById(R.id.contextEditText);
        //????????? ??? ???????????? ?????????????????? ????????? ?????????
        textView3.setText(userID+"??? ?????????");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                checkDay(year,month,dayOfMonth,userID);
            }
        });
        cha_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(userID, fname, attendance);
            }
        });
    }

    public void  checkDay(int cYear,int cMonth,int cDay,String userID){


        fname=String.format("%d%d%d", cYear, cMonth, cDay);
        FileInputStream fis=null;//FileStream fis ??????

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject( response );
                    if(jsonObject.has("attendance")){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("attendance");
                        int attend = jsonObject1.getInt("attendance");
                        switch (attend){
                            case 1:
                                str = "??????";
                                break;
                            case 2:
                                str = "??????";
                                break;
                        }
                    }
                    else{
                        str = "?????????";
                    }
                    contextEditText.setText(str);

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                del_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contextEditText.setText("");
                        removeDiary(userID, fname);
                    }
                });

            }
        };
        String date = String.format("%d%d%d",cYear,cMonth,cDay);
        AttendR attendR = new AttendR( userID, date, curClassID,responseListener );
        RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
        queue.add( attendR );



    }
    @SuppressLint("WrongConstant")
    public void removeDiary(String userID, String date){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("res: " + response);
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );
                    if(success){
                        Toast.makeText(getApplicationContext(), "?????? ??????", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };

        AttendDelR attendDelR = new AttendDelR(userID, date, curClassID,responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add( attendDelR );
    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String userID, String date, int attendance){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("res: " + response);
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );
                    if(success){
                        Toast.makeText(getApplicationContext(), "?????? ??????", Toast.LENGTH_SHORT).show();
                    }
                    if (attendance == 1) {
                        contextEditText.setText("??????");
                    }
                    else{
                        contextEditText.setText("??????");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };
        AttendAddR attendAddR = new AttendAddR(userID, date, attendance, curClassID,responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add( attendAddR );
    }
}
