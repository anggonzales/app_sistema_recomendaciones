package com.example.amazon_mercadolibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Registro(View view) {
        startActivity(new Intent(this, Registro.class));
    }

    public void Login(View view) {
        startActivity(new Intent(this, Login.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!= null){
            startActivity(new Intent(this, CategoriaProductoUsuario.class));
            finish();
        }
    }
}