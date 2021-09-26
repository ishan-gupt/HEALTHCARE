package com.devathon.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DoctorRegistration extends AppCompatActivity {

    EditText docIDEditText,docNameEditText,docAddEditText,docNumEditText,docSpecEditText;
    Button Doctorbutton;
    TextView DocClickHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_doctor_registration);

        docIDEditText = (EditText) findViewById(R.id.docIDEditText);
        docNameEditText = (EditText) findViewById(R.id.docNameEditText);
        docAddEditText = (EditText) findViewById(R.id.docAddEditText);
        docNumEditText = (EditText) findViewById(R.id.docNumEditText);
        docSpecEditText = (EditText) findViewById(R.id.docSpecEditText);
        Doctorbutton = (Button) findViewById(R.id.Doctorbutton);
        DocClickHere = (TextView) findViewById(R.id.DocClickHere);
        DocClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PatientDetails.class));
            }
        });

    }
}