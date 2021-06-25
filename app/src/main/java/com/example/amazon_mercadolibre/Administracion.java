package com.example.amazon_mercadolibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Administracion extends AppCompatActivity {

    CardView car1,card2;
    Button btn;
    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public void AMZ(View view) {
        startActivity(new Intent(this, ConsultaAMZ.class));
    }

    public void ML(View view) {
        startActivity(new Intent(this, ConsultaMercadoLibre2.class));
    }
    public void Salir(View view) {

        logOutUser();
    }

    private void logOutUser() {
        mAuth.signOut();
        Intent loginIntent =  new Intent(Administracion.this, Login.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}