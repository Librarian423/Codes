package org.techtown.se_project05;

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

public class ManagerAddClass extends AppCompatActivity {
    private EditText new_class_name;
    private Button btn_add_class;
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<Integer> classesID = new ArrayList<>();
 protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView(R.layout.manager_add_class);
        new_class_name = findViewById(R.id.new_class);
        btn_add_class = findViewById(R.id.add_class);
        classes = getIntent().getStringArrayListExtra("classes");
        classesID = getIntent().getIntegerArrayListExtra("classesID");
        btn_add_class.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String className = new_class_name.getText().toString();
                String userID= getIntent().getStringExtra("userID");
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    public void onResponse(String response) {
                        try{
                            System.out.println("res: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean( "success" );

                            if (success){
                                JSONObject jsonObject1 = jsonObject.getJSONObject("lectureID");
                                Toast.makeText(getApplicationContext(), "수업추가 성공", Toast.LENGTH_SHORT).show();
                                classes.add(className);
                                classesID.add(jsonObject1.getInt("lectureID"));
                                System.out.println("classesID: " + classesID);
                                Intent intent = new Intent(getApplicationContext(), ManagerInit.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("classes", classes);
                                intent.putExtra("classesID", classesID);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "중복된 수업명입니다", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ManagerAddClassR addClassR = new ManagerAddClassR( userID, className,responseListener );
                RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
                queue.add( addClassR );
                
                
                
            }
            
            
            
        });

    }

}
