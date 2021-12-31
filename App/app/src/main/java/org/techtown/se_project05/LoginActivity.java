package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    private RadioGroup radioGroup;
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<Integer> classesID = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        et_id = findViewById( R.id.et_id );
        et_pass = findViewById( R.id.et_pass );
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radioButton2);

        btn_register = findViewById( R.id.btn_register );
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, RegisterActivity.class );
                startActivity( intent );
            }
        });


        btn_login = findViewById( R.id.btn_login );
        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                int userType = 1;
                if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton){
                    userType = 2;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText( getApplicationContext(), "로그인 시도중", Toast.LENGTH_SHORT ).show();
                            System.out.println("res: " + response);

                            JSONObject jsonObject = new JSONObject( response );

                            boolean success = jsonObject.getBoolean( "success" );
                            if(!jsonObject.has("userID") || !jsonObject.has("userPassword")) {
                                Toast.makeText(getApplicationContext(), "존재하지 않는 ID/PW", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            classes.clear();
                            classesID.clear();
                            JSONArray jsonArray= jsonObject.getJSONArray("classes");
                            System.out.println(jsonArray);
                            if (jsonArray.length() != 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject arr = (JSONObject)jsonArray.get(i);
                                    System.out.println("t1: "+ arr.getString("lectureName"));
                                    System.out.println("t2: "+ arr.getInt("lectureID"));
                                    classes.add(arr.getString("lectureName"));
                                    classesID.add(arr.getInt("lectureID"));
                                }
                            }


                            if(success) {//로그인 성공시

                                String userID = jsonObject.getString( "userID" );
                                int userType = jsonObject.getInt("userType");
                                Toast.makeText( getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT ).show();
                                Class goClass;
                                if(userType == 1) {
                                        goClass = StudentInit.class;
                                }
                                else {
                                        goClass = ManagerInit.class;
                                }
                                Intent intent = new Intent(LoginActivity.this, goClass);

                                intent.putExtra( "userID", userID );
                                intent.putExtra("type", userType);
                                intent.putExtra("classes", classes);
                                intent.putExtra("classesID", classesID);
                                startActivity( intent );

                            } else {//로그인 실패시
                                Toast.makeText( getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                if (userID.matches("") || userPass.matches("")){
                    Toast.makeText( getApplicationContext(), "ID/PW를 입력하세요", Toast.LENGTH_SHORT ).show();
                    return;
                }
                LoginRequest loginRequest = new LoginRequest( userID, userPass, userType,responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add( loginRequest );

            }
        });
    }
}
