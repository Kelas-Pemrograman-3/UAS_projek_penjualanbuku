package com.uas.penjualanbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class editprofil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);
        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(editprofil.this,profil.class);
        startActivity(i);
        finish();
    }
}
