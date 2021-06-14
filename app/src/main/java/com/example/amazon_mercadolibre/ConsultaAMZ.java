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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazon_mercadolibre.Adapters.ArticuloAdapter;
import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.Model.ClsCategoria;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConsultaAMZ extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button btnConsulta;
    EditText edtNombre;
    ArrayList Articulo = new ArrayList();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_a_m_z);
        btnConsulta = (Button) findViewById(R.id.btnBuscar);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        recyclerView = findViewById(R.id.rcvListaArticulo);
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticuloAdapter adaptadorRecycler = new ArticuloAdapter(ConsultaAMZ.this, Articulo);
                recyclerView.setAdapter(adaptadorRecycler);
                layoutManager = new LinearLayoutManager(ConsultaAMZ.this);
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
        String URL = "https://amazon-product-reviews-keywords.p.rapidapi.com/product/search?keyword=" + edtNombre.getText().toString() + "&country=US&category=aps";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonData = response.getJSONArray("products");
                    //JSONObject jsonData = response.getJSONObject("products");
                    Log.e("datos", response.toString());
                    Log.e("test", jsonData.toString());

                    for (int i = 0; i < jsonData.length(); i++) {
                        JSONObject object = jsonData.getJSONObject(i);
                        JSONObject jsonprecio = object.getJSONObject("price");
                        Log.e("price", jsonprecio.toString());

                        String asin = object.getString("asin");
                        String titulo = object.getString("title");
                        String imagen = object.getString("thumbnail");
                        String URL = object.getString("url");
                        String precio = jsonprecio.getString("current_price").toString();

                        ClsArticulo articulo = new ClsArticulo(asin, titulo, URL, precio, imagen);
                        Log.e("product", asin + " " + titulo + " " + URL + " " + precio + " "+ imagen);
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
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-rapidapi-key", "71b392c379msh008efd639965464p10f6efjsn7b49f5074ba1");
                headers.put("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com");
                return headers;
            }
        };
        requestQueue.add(objRequest);
    }
}


