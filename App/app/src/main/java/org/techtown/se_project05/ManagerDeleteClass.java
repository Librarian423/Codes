package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagerDeleteClass extends AppCompatActivity {
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<Integer> classesID = new ArrayList<>();
    private String curClass, userID;
    private int curClassID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_del_class);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 1);

        TextView tv_class = findViewById(R.id.tv_class);
        Button yes_button= findViewById(R.id.yes_button);
        Button no_button = findViewById(R.id.no_button);

        tv_class.setText(curClass);

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText( getApplicationContext(), "수업 삭제 성공", Toast.LENGTH_SHORT ).show();
                        Intent intent= new Intent(getApplicationContext(), ManagerInit.class);
                        if(!classes.remove(curClass)){
                            Toast.makeText( getApplicationContext(), "삭제오류 1", Toast.LENGTH_SHORT ).show();
                        }
                        if(!classesID.remove(Integer.valueOf(curClassID))){
                            Toast.makeText( getApplicationContext(), "삭제오류 2", Toast.LENGTH_SHORT ).show();
                        }
                        intent.putExtra("userID", userID);
                        intent.putExtra("classes", classes);
                        intent.putExtra("classesID", classesID);
                        startActivity(intent);
                    }
                };
                ManagerDelClassR managerDelClassR = new ManagerDelClassR(userID, curClassID, responseListener );
                RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
                queue.add( managerDelClassR );
            }
        });
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "삭제 취소", Toast.LENGTH_SHORT ).show();
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
