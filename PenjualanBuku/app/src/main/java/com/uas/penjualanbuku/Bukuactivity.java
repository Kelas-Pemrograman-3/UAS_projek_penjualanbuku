package com.uas.penjualanbuku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

import adapter.adapterbuku;
import model.bukuModel;
import server.configurl;

;

public class Bukuactivity extends AppCompatActivity {

    ProgressDialog pDialog;

    adapterbuku adapter;
    ListView list;

    ArrayList<bukuModel> newsList = new ArrayList<bukuModel>();

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_List);
        newsList.clear();
        adapter = new adapterbuku(Bukuactivity.this, newsList, mRequestQueue, pDialog);
        list.setAdapter(adapter);
        getAllData();

    }

    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, configurl.getAll, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    bukuModel buku1 = new bukuModel();
                                    buku1.set_id(jsonObject.getString("_id"));
                                    buku1.setId_Buku(jsonObject.getString("Id_Buku"));
                                    buku1.setJudul(jsonObject.getString("Judul"));
                                    buku1.setPenerbit(jsonObject.getString("Kategori"));
                                    buku1.setKategori(jsonObject.getString("Penerbit"));
                                    buku1.setHarga(jsonObject.getString("Harga"));
                                    newsList.add(buku1);
                                    }
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
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


    @Override
    public void onBackPressed(){
        Intent i = new Intent(Bukuactivity.this, Menu.class);
        startActivity(i);
        finish();
    }
}
