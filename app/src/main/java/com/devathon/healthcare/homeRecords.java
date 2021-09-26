package com.devathon.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeRecords extends AppCompatActivity {

    Button patDetButton,hosDetButton,docDetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_records);

        hosDetButton = (Button) findViewById(R.id.hosDetButton);
        patDetButton = (Button) findViewById(R.id.patDetButton);
        docDetButton = (Button) findViewById(R.id.docDetButton);
        hosDetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HospitalDetails.class));
            }
        });
        patDetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PatientDetails.class));
            }
        });docDetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DoctorDetails.class));
            }
        });

    }
}