package com.uas.penjualanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import sesion.sesionmanager;

public class profil extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private ProgressDialog pDialog;
    private sesionmanager sesion;


    private ImageView linkeditprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil2);
        getSupportActionBar().hide();

        sesion = new sesionmanager(this);

        linkeditprofil = findViewById(R.id.Linkeditprof);

        linkeditprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profil.this, editprofil.class);
                startActivity(i);
                finish();
            }
        });
    }
}
