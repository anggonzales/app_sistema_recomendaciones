package com.example.amazon_mercadolibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazon_mercadolibre.Adapters.ArticuloAdapter;
import com.example.amazon_mercadolibre.Adapters.ArticuloMLAdapter;
import com.example.amazon_mercadolibre.Model.ClsArticulo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultaMercadoLibre extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button btnConsulta;
    EditText edtNombre;
    ArrayList Articulo = new ArrayList();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mercado_libre);

        btnConsulta = (Button)findViewById(R.id.btnBuscar);
        recyclerView = findViewById(R.id.rcvListaArticuloML);
        edtNombre = (EditText) findViewById(R.id.edtNombre);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticuloMLAdapter adaptadorRecycler = new ArticuloMLAdapter(ConsultaMercadoLibre.this, Articulo);
                recyclerView.setAdapter(adaptadorRecycler);
                layoutManager = new LinearLayoutManager(ConsultaMercadoLibre.this);
                recyclerView.setLayoutManager(layoutManager);
                if(Articulo.size() > 0){
                    Articulo.clear();
                    ObtenerConsultaProducto(Articulo);
                } else {
                    ObtenerConsultaProducto(Articulo);
                }
            }
        });
    }

    public void ObtenerConsultaProducto(final ArrayList lista) {
        String URL = "https://api.mercadolibre.com/sites/MPE/search?q=" + edtNombre.getText().toString() ;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonData = response.getJSONArray("results");
                    Log.e("datos", response.toString());
                    Log.e("test", jsonData.toString());

                    for (int i = 0; i < jsonData.length(); i++) {
                        JSONObject object = jsonData.getJSONObject(i);
                        String asin = object.getString("id");
                        String titulo = object.getString("title");
                        String precio = object.getString("price");
                        String imagen = object.getString("thumbnail");
                        String URL = object.getString("permalink");

                        ClsArticulo articulo = new ClsArticulo(asin, titulo, URL, precio, imagen);
                        Log.e("product", asin + " " + titulo + " " + URL + " " + precio + " " + imagen);
                        lista.add(articulo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(objRequest);
    }
}