package com.devathon.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class DoctorDetails extends AppCompatActivity {

    EditText docDelEdittext;
    TextView DocDelNametextView,DocDelAddetextView,DocDelSpectextView,DocDelNumtextView;
    Button DocDelButton;
    String url="http://192.168.68.1/health/doctor.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_doctor_details);

        docDelEdittext = (EditText) findViewById(R.id.docDelEdittext);
        DocDelNametextView = (TextView) findViewById(R.id.DocDelNametextView);
        DocDelAddetextView = (TextView) findViewById(R.id.DocDelAddetextView);
        DocDelSpectextView = (TextView) findViewById(R.id.DocDelSpectextView);
        DocDelNumtextView = (TextView) findViewById(R.id.DocDelNumtextView);
        DocDelButton = (Button) findViewById(R.id.DocDelButton);
        DocDelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reload();
            }
        });


    }
    private void Reload() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            Log.e(TAG, "onResponse: json" + jsonObject);
                            Log.e(TAG, "onResponse: " + jsonObject.getString("enroll"));


                            if (code.equals("login fail")) {
                                AlertDialog.Builder builder = null;
                                builder.setTitle("ERROR");
                                Log.e(TAG, "onResponse: 1" + code);
                                builder.setMessage(jsonObject.getString("code"));
                                builder.show();
                            } else {
                                Shareprefences.getInstance(getContext()).userlogin(jsonObject.getString("docNameEditText")
                                        , jsonObject.getString("docIDEditText")
                                        , jsonObject.getString("docAddEditText")
                                        , jsonObject.getString("docSpecEditText")
                                        , jsonObject.getString("docNumEditText"));

                                DocDelNametextView.setText(Shareprefences.getInstance(getContext()).getname());
                                DocDelAddetextView.setText(Shareprefences.getInstance(getContext()).getadd());
                                DocDelSpectextView.setText(Shareprefences.getInstance(getContext()).getspec());
                            }

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ERROR" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> stringStringMap = new HashMap<String, String>();
                stringStringMap.put("enroll", Shareprefences.getInstance(getContext()).getEnroll());
                stringStringMap.put("pass", Shareprefences.getInstance(getContext()).getPass());
                Log.e(TAG, "getParams: " + stringStringMap);
                return stringStringMap;
            }
        };

        MySet.getInstance(getContext()).addRequestQue(stringRequest);

    }
}