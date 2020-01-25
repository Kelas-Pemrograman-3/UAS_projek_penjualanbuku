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
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;

import adapter.adapterbuku;
import model.bukuModel;
import server.configurl;
import sesion.sesionmanager;

public class keranjang extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private ProgressDialog pDialog;

    EditText idbuku, judul, penerbit, kategori, harga;

    Intent intent;
    String detailorupdate, strid, strjudul, strpenerbit, strkategori, strharga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukuactivity);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        idbuku = (EditText) findViewById(R.id.idbuku);
        judul = (EditText) findViewById(R.id.judul);
        penerbit = (EditText) findViewById(R.id.penerbit);
        kategori = (EditText) findViewById(R.id.kategori);
        harga = (EditText) findViewById(R.id.harga);

        intent = getIntent();
        detailorupdate = intent.getStringExtra("detailorupdate");
        strid = intent.getStringExtra("Id_Buku");
        strjudul = intent.getStringExtra("Judul");
        strpenerbit = intent.getStringExtra("Penerbit");
        strkategori = intent.getStringExtra("Kategori");
        strharga = intent.getStringExtra("Harga");

        idbuku.setText(strid);
        judul.setText(strjudul);
        penerbit.setText(strpenerbit);
        kategori.setText(strkategori);
        harga.setText(strharga);


    }




    @Override
    public void onBackPressed(){
        Intent i = new Intent(keranjang.this, Menu.class);
        startActivity(i);
        finish();
    }

}
