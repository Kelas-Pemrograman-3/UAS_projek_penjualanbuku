package com.uas.penjualanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configurl;
import sesion.sesionmanager;


public class LoginActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private EditText edtuser,edtpassword;

    private Button btnlinkregister,login;

    private ProgressDialog pDialog;

    //  ini untuk session Login
    private sesionmanager sesionmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Untuk Menghilangkan NavBar di Tampilan
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

//        sesionmanager = new sesionmanager(this);
//        if (sesionmanager.isLoggedIn()){
//            Intent i = new Intent(LoginActivity.this,Menu.class);
//            startActivity(i);
//            finish();
//        }
        edtuser = (EditText)findViewById(R.id.txtuserlogin);
        edtpassword = (EditText)findViewById(R.id.txtpasslogin);

        login = (Button)findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struser = edtuser.getText().toString();

                String strpassword = edtpassword.getText().toString();

                if (struser.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();

                }else if (strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password Tidak Boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else {
                    login(struser,strpassword);
                }
            }
        });

        btnlinkregister = (Button) findViewById(R.id.btnlinkregister);

        btnlinkregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,regisactivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void login(String user, String password){
//        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", password);

        pDialog.setMessage("Mohon Tunggu...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(configurl.login, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status== true){
                                msg = response.getString("pesan");
                            }else {
//                                sesionmanager.setLogin(true);
                                msg = response.getString("pesan");
                                Intent i = new Intent(LoginActivity.this,Menu.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_LONG).show();
//                            VolleyLog.v("Response:%n %s", response.toString(4));
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
