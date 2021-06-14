package com.example.amazon_mercadolibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Administracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);
    }

    public void AMZ(View view) {
        startActivity(new Intent(this, ConsultaAMZ.class));
    }

    public void ML(View view) {
        startActivity(new Intent(this, ConsultaMercadoLibre.class));
    }
}