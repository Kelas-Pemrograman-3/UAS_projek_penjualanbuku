package com.uas.penjualanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configurl;

public class regisactivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ProgressDialog pDialog;

    private EditText editusername, editpassword, editalamat, editemail, editnama;
    private Button btnregis, btnlinklogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisactivity);
        getSupportActionBar().hide();

        pDialog = new ProgressDialog(this);
        pDialog .setCancelable(false);

        mRequestQueue = Volley.newRequestQueue(this);

        editusername = (EditText) findViewById(R.id.editusername) ;
        editnama = (EditText) findViewById(R.id.editnama) ;
        editemail = (EditText) findViewById(R.id.editemail) ;
        editalamat = (EditText) findViewById(R.id.editalamat) ;
        editpassword = (EditText) findViewById(R.id.editpassword) ;

        btnlinklogin = findViewById(R.id.btnlinklogin);
        btnlinklogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent a = new Intent(regisactivity.this, LoginActivity.class);
                startActivity(a);
                finish();
            }
        });

        btnregis = (Button) findViewById(R.id.btnregis) ;
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strusername = editusername.getText().toString();
                String strnama = editnama.getText().toString();
                String stremail = editemail.getText().toString();
                String stralamat = editalamat.getText().toString();
                String strpassword = editpassword.getText().toString();

                if (strusername.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strnama.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (stremail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (stralamat.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Alamat Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else {
                    regisdata(strusername, strnama, stremail, stralamat, strpassword);

                }

            }
        });


    }

    private void regisdata(String editusername, String editnama, String editemail, String editalamat,  String editpassword){

//        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", editusername);
        params.put("nama", editnama);
        params.put("email", editemail);
        params.put("alamat", editalamat);
        params.put("password", editpassword);

        pDialog.setMessage("Mohon Tunggu");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(configurl.regisuser, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if(status == true){
                                msg = response.getString("pesan");
                            } else {
                                msg = response.getString("pesan");
                                Intent a = new Intent(regisactivity.this, LoginActivity.class);
                                startActivity(a);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//                                VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
//        ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}