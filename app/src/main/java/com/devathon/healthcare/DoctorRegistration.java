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

public class DoctorRegistration extends AppCompatActivity {

    EditText docIDEditText,docNameEditText,docAddEditText,docNumEditText,docSpecEditText;
    Button Doctorbutton;
    TextView DocClickHere;
    String n, c, a, p, cn;
    String url = "http://192.168.228.1/health/doctor.php";
    StringRequest stringRequest;

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
        Doctorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }
    public void registerUser() {
        n = docNameEditText.getText().toString().trim();
        a = docAddEditText.getText().toString().trim();
        c = docIDEditText.getText().toString().trim();
        cn = docNumEditText.getText().toString().trim();
        p = docSpecEditText.getText().toString().trim();

        if (c.isEmpty()) {
            docIDEditText.setError("Please fill ID");
            docIDEditText.requestFocus();
        }
        if (n.isEmpty()) {
            docNameEditText.setError("Please fill Name");
            docNameEditText.requestFocus();
        }
        if (a.isEmpty()) {
            docAddEditText.setError("Please fill Address");
            docAddEditText.requestFocus();
        }
        if (cn.isEmpty()) {
            docNumEditText.setError("Please fill Number");
            docNumEditText.requestFocus();
        }
        if (p.isEmpty()) {
            docSpecEditText.setError("Please fill the Specification");
            docSpecEditText.requestFocus();
        }
        Signup_With_volley();
    }
    public void Signup_With_volley()
    {
        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e(TAG, "onResponse: " + response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("message");
                            Toast.makeText(DoctorRegistration.this, code + "  " + msg, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication()   ,MainActivity.class));
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
                stringStringMap.put("docNameEditText", n);
                stringStringMap.put("docIDEditText",c);
                stringStringMap.put("docAddEditText",a);
                stringStringMap.put("docSpecEditText", p);
                stringStringMap.put("docNumEditText", cn);
                return stringStringMap;
            }
        };

        MySet.getInstance(DoctorRegistration.this).addRequestQue(stringRequest);
    }

}
