package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.uas.penjualanbuku.Bukuactivity;
import com.uas.penjualanbuku.Menu;
import com.uas.penjualanbuku.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import model.bukuModel;

import model.keranjangModel;
import server.configurl;

public class adapterkeranjang extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<keranjangModel> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapterkeranjang(Activity activity, List<keranjangModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
        this.pDialog = pDialog;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_keranjang, null);


        TextView idbuku = (TextView) convertView.findViewById(R.id.txtidbuku);
        TextView judul = (TextView) convertView.findViewById(R.id.txtjudul);
        TextView penerbit = (TextView) convertView.findViewById(R.id.txtpenerbit);
        TextView kategori = (TextView) convertView.findViewById(R.id.txtkategori);
        TextView harga = (TextView) convertView.findViewById(R.id.txtharga);



        idbuku.setText(item.get(position).getId_Buku());
        judul.setText(item.get(position).getJudul());
        penerbit.setText(item.get(position).getPenerbit());
        kategori.setText(item.get(position).getKategori());
        harga.setText(item.get(position).getHarga());
        return convertView;
    }

    private void registrasi(String Id_Buku, String Judul, String Penerbit, String Kategori, String Harga){
//        final String URL = "/volley/resource/12";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Id_Buku", Id_Buku);
        params.put("Judul", Judul);
        params.put("Penerbit", Penerbit);
        params.put("Kategori", Kategori);
        params.put("Harga", Harga);

        pDialog.setMessage("Mohon Tunggu...");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(configurl.getkeranjang, new JSONObject(params),
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
                                msg = response.getString("pesan");

                                Intent a = new Intent(activity, Bukuactivity.class);
                                activity.startActivity(a);
                                activity.finish();
                            }
                            Toast.makeText(activity,  msg, Toast.LENGTH_LONG).show();
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
