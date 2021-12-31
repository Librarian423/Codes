package org.techtown.se_project05;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagerAttendanceTemp extends AppCompatActivity {
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<Integer> classesID = new ArrayList<>();
    private String curClass, userID;
    private int curClassID;
    private static Toast toast;
    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_att_temp);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 2);
        System.out.println("curClassID2" + curClassID);

        Button checkStudent = findViewById(R.id.check_student);
        TextView className = findViewById(R.id.tv_id);
        className.setText(curClass);
        Button back = findViewById(R.id.back);
        EditText et_stud_id = findViewById(R.id.new_stud_id);


        checkStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String studentID = et_stud_id.getText().toString();
                int lectureID = curClassID;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(1);
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Intent intent= new Intent(getApplicationContext(), ManagerAttendance.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("studentID", studentID);
                                intent.putExtra("classes", classes);
                                intent.putExtra("classesID", classesID);
                                intent.putExtra("curClass", curClass);
                                intent.putExtra("curClassID", curClassID);
                                startActivity(intent);
                            }
                            else{
                                showToast( getApplicationContext() ,"존재하지 않는 학생입니다" );
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CheckStudR checkStudR = new CheckStudR(studentID, lectureID, responseListener );
                RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
                queue.add( checkStudR );
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Intent intent= new Intent(getApplicationContext(), ManagerFunc.class);
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
