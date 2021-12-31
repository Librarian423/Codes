package org.techtown.se_project05;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerSelectAttendance extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.manager_select_attendance);
        Button present = findViewById(R.id.present);
        Button absent = findViewById(R.id.absent);
        Button set_default = findViewById(R.id.set_default);

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("출석");
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("결석");
            }
        });
        set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("미입력");
            }
        });

    }
}




