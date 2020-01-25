package com.uas.penjualanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

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
//import sesionmanager.sesionmanager;


public class Menu extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private ProgressDialog pDialog;
    private sesionmanager sesion;
    private ImageView linkbuku, linkkeranjang, linkprofil, Linkkeluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        sesion = new sesionmanager(this);

        linkbuku = findViewById(R.id.LinkBuku);

        linkbuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Bukuactivity.class);
                startActivity(i);
                finish();
            }
        });


        linkkeranjang = findViewById(R.id.Linkkeranjang);

        linkkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, keranjang.class);
                startActivity(i);
                finish();
            }
        });

        linkprofil = findViewById(R.id.Linkprofil);

        linkprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, profil.class);
                startActivity(i);
                finish();
            }
        });


        Linkkeluar = findViewById(R.id.Linkkeluar);

        Linkkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

            }

        }