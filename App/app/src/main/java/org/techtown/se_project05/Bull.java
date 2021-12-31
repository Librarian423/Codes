package org.techtown.se_project05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Bull extends AppCompatActivity {

    public ListView listView;
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<Integer> classesID = new ArrayList<>();
    private String curClass, userID;
    private int curClassID;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        classes = intent.getStringArrayListExtra("classes");
        classesID = intent.getIntegerArrayListExtra("classesID");
        curClass = intent.getStringExtra("curClass");
        curClassID = intent.getIntExtra("curClassID", 1);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    JSONArray arr =jsonObject.getJSONArray("list");
                    for(int i = 0; i<arr.length(); i++){
                        list.add(arr.getString(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listView = (ListView) findViewById(R.id.listview); // 앞서 activity_main.xml에서 정의한 listview에 대하여 ListView 변수 정의

                // ArrayAdapter를 통해 Android API platform의 'res' library에 simple_list_item_1.xml에 상응하는 형태로 위의 list를 listView에 반영
                /*final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(mAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // listView에 반영된 item을 Click할 경우 다음 동작을 수행
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position); // 선택한 값을 String 문자열로 받아들여 Toast 출력
                        Toast.makeText(getApplicationContext(), item + " is selected!", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        };
    }
}

