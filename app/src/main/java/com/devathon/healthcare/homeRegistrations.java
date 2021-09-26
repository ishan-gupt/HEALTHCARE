package com.devathon.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeRegistrations extends AppCompatActivity {

    Button docRegButton,hosRegButton,patRegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_registrations);

        docRegButton = (Button) findViewById(R.id.docRegButton);
        hosRegButton = (Button) findViewById(R.id.hosRegButton);
        patRegButton = (Button) findViewById(R.id.patRegButton);
        hosRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HospitalRegistration.class));
            }
        });
        patRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PatientRegistration.class));
            }
        });docRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DoctorRegistration.class));
            }
        });
    }
}