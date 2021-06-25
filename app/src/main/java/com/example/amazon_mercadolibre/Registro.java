package com.example.amazon_mercadolibre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPassword;
    EditText edtNombre;
    Button btnRegistrar;
    private DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private int currentAnimationFrame = 0;
    private LottieAnimationView animationView;

    private ProgressDialog progressDialog;
    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Cargar();
        mAuth = FirebaseAuth.getInstance();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                Registrar(nombre, email, password);
            }
        });
    }

    private void Cargar() {
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnRegistrar = (Button) findViewById(R.id.btnRegistro);
    }

    private void Registrar(final String nombre, final String email, String password) {

        if (TextUtils.isEmpty(nombre)) {
            edtNombre.setError("campo requerido");
        } else if (TextUtils.isEmpty(email)) {
            edtEmail.setError("campo requerido");
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.setError("campo requerido");
        } else {

            progressDialog =new ProgressDialog(this);
            progressDialog.setTitle("Creando Cuenta");
            progressDialog.setMessage("Espera We ....");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String IdUser = mAuth.getCurrentUser().getUid();
                        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(IdUser);
                        reference.child("IdUsuario").setValue(IdUser);
                        reference.child("NombreUsuario").setValue(nombre);
                        reference.child("CorreoUsuario").setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                user = mAuth.getCurrentUser();
                                if (user != null) {
                                    progressDialog.dismiss();
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            mensajeverfica();

                                        }
                                    });
                                }
                            }
                        });
                    }else{
                        Toast.makeText(Registro.this, "eRRor", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }

    private void mensajeverfica(){

        builder1 = new AlertDialog.Builder(Registro.this);
        Button btcerrrar;

        View v = LayoutInflater.from(Registro.this).inflate(R.layout.dialogok, null);
        animationView = v.findViewById(R.id.animation_viewcheck4);
        resetAnimationView();
        animationView.playAnimation();
        builder1.setView(v);
        btcerrrar=(Button)v.findViewById(R.id.idbtncerrardialogo2);
        btcerrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                Toast.makeText(Registro.this, "Registrado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registro.this, Login.class));
                finish();
            }
        });
        alert  = builder1.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alert.show();
    }
    private void resetAnimationView() {
        currentAnimationFrame = 0;
        animationView.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return null;
                    }
                }
        );
    }
}