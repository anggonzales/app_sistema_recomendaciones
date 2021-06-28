package com.example.amazon_mercadolibre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Administracion extends AppCompatActivity {

    CardView car1,card2;
    Button btn;
    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;

    private DatabaseReference referenceUsuarios,referenceColegas;
    TextView tvnombre,tvcorreo;
    String user_id;
    String correoprofe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        user_id =    mAuth.getCurrentUser().getUid();
        tvcorreo=(TextView)findViewById(R.id.txtcorreouser);
        tvnombre=(TextView)findViewById(R.id.txtnombreuser);
        correoprofe=mAuth.getCurrentUser().getEmail();
        tvcorreo.setText(correoprofe);
        referenceUsuarios = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user_id);
        referenceUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("NombreUsuario").getValue().toString();
                    tvnombre.setText(nombre);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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