package org.techtown.se_project05;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagerAddStudent extends AppCompatActivity {
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
        setContentView(R.layout.manager_add_student);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 1);
        Button checkStudent = findViewById(R.id.check_student);
        Button addStudent = findViewById(R.id.add_student);
        Button deleteStudent = findViewById(R.id.delete_student);
        EditText et_stud_id = findViewById(R.id.new_stud_id);
        
        
        //학생 확인 선택시 일어나는 일
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
                                showToast( getApplicationContext() ,"확인:존재하는 학생입니다" );
                            }
                            else{
                                showToast( getApplicationContext() ,"확인:존재하지 않는 학생입니다" );
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

        //학생 삭제 선택시 일어나는 일
        addStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String studentID = et_stud_id.getText().toString();
                int lectureID = curClassID;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("2: " + response);
                            JSONObject jsonObject = new JSONObject( response );
                            int success = jsonObject.getInt( "success" );
                            if(success == 1){
                                showToast( getApplicationContext() ,"추가:새로운 학생을 추가하였습니다"  );
                            }
                            else if (success == 2){
                                showToast( getApplicationContext() ,"추가:존재하지 않는 학생입니다"  );
                            }
                            else if (success == 3){
                                showToast( getApplicationContext() ,"추가:이미 수업을 듣고있는 학생입니다"  );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                AddStudR addStudR = new AddStudR( studentID, lectureID, responseListener );
                RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
                queue.add( addStudR );
            }
        });
        
        //학생 삭제 선택시 일어나는 일
        deleteStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String studentID = et_stud_id.getText().toString();
                int lectureID = curClassID;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("res" + response);
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            if(success){
                                showToast( getApplicationContext() ,"삭제:학생을 삭제하였습니다"  );
                            }
                            else {
                                showToast( getApplicationContext() ,"삭제:존재하지 않는 학생입니다"  );
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteStudR deleteStudR = new DeleteStudR( studentID, lectureID, responseListener );
                RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
                queue.add( deleteStudR );
            }
        });

    }

}
