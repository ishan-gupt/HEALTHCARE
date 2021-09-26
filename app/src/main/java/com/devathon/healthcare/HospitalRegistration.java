package com.devathon.healthcare;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HospitalRegistration extends AppCompatActivity {
    EditText hosIDEditText,hosNameEditText,hosAddEditText,hosSpecEditText;
    TextView HosClickHere;
    Button Hospitalbutton;
    String url="http://192.168.68.1/health/Hospital.php";
    String n,i,s,a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hospital_registration);

        hosIDEditText = (EditText) findViewById(R.id.hosIDEditText);
        hosNameEditText = (EditText) findViewById(R.id.hosNameEditText);
        hosAddEditText = (EditText) findViewById(R.id.hosAddEditText);
        hosSpecEditText = (EditText) findViewById(R.id.hosSpecEditText);
        HosClickHere = (TextView) findViewById(R.id.HosClickHere);
        Hospitalbutton = (Button) findViewById(R.id.Hospitalbutton);
        HosClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HospitalDetails.class));
            }
        });
        Hospitalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }
    public void registerUser() {
        n = hosNameEditText.getText().toString().trim();
        a = hosAddEditText.getText().toString().trim();
        i = hosIDEditText.getText().toString().trim();
        s = hosSpecEditText.getText().toString().trim();


        if (i.isEmpty()) {
            hosIDEditText.setError("Please fill ID");
            hosIDEditText.requestFocus();
        }
        if (a.isEmpty()) {
            hosAddEditText.setError("Please fill Address");
            hosAddEditText.requestFocus();
        }
        if (n.isEmpty()) {
            hosNameEditText.setError("Please fill Name");
            hosNameEditText.requestFocus();
        }
        if (s.isEmpty()) {
            hosSpecEditText.setError("Please fill Specification");
            hosSpecEditText.requestFocus();
        }

        Signup_With_volley();
    }
    public void Signup_With_volley()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e(TAG, "onResponse: " + response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("message");
                            Toast.makeText(HospitalRegistration.this, code + "  " + msg, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(), MainActivity.class));
                            finish();

                        } catch (JSONException e1) {
                            Log.e(TAG, "onResponse: " + e1);
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringStringMap = new HashMap<String, String>();
                stringStringMap.put("hosNameEditText", n);
                stringStringMap.put("hosIDEditText", i);
                stringStringMap.put("hosAddEditText", a);
                stringStringMap.put("hosSpecEditText", s);

                return stringStringMap;
            }
        };

        MySet.getInstance(HospitalRegistration.this).addRequestQue(stringRequest);
    }
}