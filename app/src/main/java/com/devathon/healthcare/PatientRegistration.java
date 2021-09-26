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

public class PatientRegistration extends AppCompatActivity {

    EditText patIDEditText,patNameEditText,patAddEditText,patNumEditText,patMailEditText,patAgeEditText,patGenEditText,patHeiEditText,patWeiEditText2,patIDEditText2,patNumEditText2,patAttEditText,patRelEditText;
    TextView PatientClickHere;
    Button Patientbutton;
    String n,i,a,pa,pat,patn,pn,pr,m,w,h,g;
    String url="http://192.168.68.1/health/Patient.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patient_registration);

        patIDEditText = (EditText) findViewById(R.id.patIDEditText);
        patNameEditText = (EditText) findViewById(R.id.patNameEditText);
        patAddEditText = (EditText) findViewById(R.id.patAddEditText);
        patNumEditText = (EditText) findViewById(R.id.patNumEditText);
        patMailEditText = (EditText) findViewById(R.id.patMailEditText);
        patAgeEditText = (EditText) findViewById(R.id.patAgeEditText);
        patGenEditText = (EditText) findViewById(R.id.patGenEditText);
        patHeiEditText = (EditText) findViewById(R.id.patHeiEditText);
        patWeiEditText2 = (EditText) findViewById(R.id.patWeiEditText2);
        patIDEditText2 = (EditText) findViewById(R.id.patIDEditText2);
        patAttEditText = (EditText) findViewById(R.id.patAttEditText);
        patNumEditText2 = (EditText) findViewById(R.id.patNumEditText2);
        patRelEditText = (EditText) findViewById(R.id.patRelEditText);
        PatientClickHere =(TextView) findViewById(R.id.PatientClickHere);
        Patientbutton =(Button) findViewById(R.id.Patientbutton);
        PatientClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PatientDetails.class));
            }
        });
        Patientbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }
    public void registerUser() {
        n = patNameEditText.getText().toString().trim();
        i = patIDEditText.getText().toString().trim();
        a = patAddEditText.getText().toString().trim();
        pn = patNumEditText.getText().toString().trim();
        m = patMailEditText.getText().toString().trim();
        pa = patAgeEditText.getText().toString().trim();
        g = patGenEditText.getText().toString().trim();
        h = patHeiEditText.getText().toString().trim();
        w = patWeiEditText2.getText().toString().trim();
        pat = patAttEditText.getText().toString().trim();
        patn= patNumEditText2.getText().toString().trim();
        pr = patRelEditText.getText().toString().trim();


        if (n.isEmpty()) {
            patNameEditText.setError("Please fill Name");
            patNameEditText.requestFocus();
        }
        if (i.isEmpty()) {
            patIDEditText.setError("Please fill ID");
            patIDEditText.requestFocus();
        }
        if (a.isEmpty()) {
            patAddEditText.setError("Please fill Address");
            patAddEditText.requestFocus();
        }
        if (pn.isEmpty()) {
            patNumEditText.setError("Please fill Number");
            patNumEditText.requestFocus();
        }
        if (m.isEmpty()) {
            patMailEditText.setError("Please fill the Mail");
            patMailEditText.requestFocus();
        }
        if (pa.isEmpty()) {
            patAgeEditText.setError("Please fill the Age");
            patAgeEditText.requestFocus();
        }
        if (g.isEmpty()) {
            patGenEditText.setError("Please fill the Gender");
            patGenEditText.requestFocus();
        }
        if (h.isEmpty()) {
            patHeiEditText.setError("Please fill the Height");
            patHeiEditText.requestFocus();
        }
        if (w.isEmpty()) {
            patWeiEditText2.setError("Please fill the Weight");
            patWeiEditText2.requestFocus();
        }
        if (pat.isEmpty()) {
            patAttEditText.setError("Please fill the attendie name");
            patAttEditText.requestFocus();
        }
        if (patn.isEmpty()) {
            patNumEditText2.setError("Please fill the Attendie number");
            patNumEditText2.requestFocus();
        }
        if (pr.isEmpty()) {
            patRelEditText.setError("Please fill the Atendie relationship");
            patRelEditText.requestFocus();
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
                            Toast.makeText(PatientRegistration.this, code + "  " + msg, Toast.LENGTH_SHORT).show();
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
                stringStringMap.put("patNameEditText", n);
                stringStringMap.put("patIDEditText", i);
                stringStringMap.put("patAddEditText", a);
                stringStringMap.put("patNumEditText", pn);
                stringStringMap.put("patMailEditText", m);
                stringStringMap.put("patAgeEditText", pa);
                stringStringMap.put("patGenEditText", g);
                stringStringMap.put("patHeiEditText", h);
                stringStringMap.put("patWeiEditText2", w);
                stringStringMap.put("patAttEditText", pat);
                stringStringMap.put("patNumEditText2", patn);
                stringStringMap.put("patRelEditText", pr);

                return stringStringMap;
            }
        };

        MySet.getInstance(PatientRegistration.this).addRequestQue(stringRequest);
    }

}