package com.example.amazon_mercadolibre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amazon_mercadolibre.Model.ClsSuscripcion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SuscripcionRespuesta extends AppCompatActivity {

    TextView txtMonto;
    String txtNombreSuscripcion = "Mensual";
    String txtEstado = "Valido";
    private DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    public static String IdUser = "";
    private static final String PATH_PAGO = "Transferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscripcion_respuesta);
        txtMonto = (TextView) findViewById(R.id.txtMonto);
        mAuth = FirebaseAuth.getInstance();
        IdUser = mAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference(PATH_PAGO);

        Intent intent = getIntent();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            MostrarDetalles(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void MostrarDetalles(JSONObject response, String paymentAmount) {
        Date objFecha = new Date();
        String strFormat = "yyyy-MM-dd";
        SimpleDateFormat objFormatoFecha = new SimpleDateFormat(strFormat);
        txtMonto.setText(paymentAmount);
        ClsSuscripcion suscripcion = new ClsSuscripcion(IdUser, Double.valueOf(txtMonto.getText().toString()), txtNombreSuscripcion, objFecha.toString(), txtEstado);
        reference.push().setValue(suscripcion);
    }

    public void Regresar(View view) {
        startActivity(new Intent(this, ConsultaAMZ.class));
    }

    public void ActivtyCategoriaUsuario(View view) {
        startActivity(new Intent(this, CategoriaProductoUsuario.class));
    }
}