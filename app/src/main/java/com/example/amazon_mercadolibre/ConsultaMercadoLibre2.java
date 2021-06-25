package com.example.amazon_mercadolibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.amazon_mercadolibre.Adapters.ArticuloMLAdapter;
import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.Model.ClsBusqueda;
import com.example.amazon_mercadolibre.Model.ClsCategoria;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsultaMercadoLibre2 extends AppCompatActivity {



    ScaleAnimation shrinkAnim;

    private RecyclerView mRecyclerView;
  //  private StaggeredGridLayoutManager mLayoutManager;
    //private FirebaseRecyclerAdapter<Movie,MovieViewHolder> adapter;
    private TextView tvNoMovies,txtcategorias;
    ArrayList Articulo = new ArrayList();
    Button btnConsulta,btncategoria;
    EditText edtNombre;
     ImageView ImagenML;
    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    String id_usuario,nombre_usuario,foto_usuario;
    List<ClsCategoria> listaCategoria;
    private DatabaseReference reference,referencebusquedas;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mercado_libre2);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        btnConsulta = (Button)findViewById(R.id.btnBuscar);
       /// btncategoria=(Button)findViewById(R.id.btncetegoria);
        txtcategorias=(TextView)findViewById(R.id.txtvercategorias);
        txtcategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoria();
            }
        });
        listaCategoria= new ArrayList<>();
        Charge();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        id_usuario= mAuth.getCurrentUser().getUid();
        reference= FirebaseDatabase.getInstance().getReference("Usuarios").child(id_usuario).child("Categoria");
        referencebusquedas= FirebaseDatabase.getInstance().getReference("Busquedas").child(id_usuario);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
      //  tvNoMovies = (TextView) findViewById(R.id.tv_no_movies);

        shrinkAnim = new ScaleAnimation(1.15f, 0f, 1.15f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }



        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda=edtNombre.getText().toString();
                if(Articulo.size() > 0){
                    Articulo.clear();
                    ObtenerConsultaProducto(Articulo);
                } else {
                    ObtenerConsultaProducto(Articulo);
                }
                String key =referencebusquedas.push().getKey();
                ClsBusqueda o = new ClsBusqueda(key,busqueda);
                referencebusquedas.child(key).setValue(o);
            }
        });
        edtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ConsultaMercadoLibre2.this,Busqueda.class);
                Bundle bundle = new Bundle();
                bundle.putString("iduser",id_usuario);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void categoria() {
        startActivity(new Intent(ConsultaMercadoLibre2.this,Categorias.class));

    }
    private void Charge(){
        progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("cargando");
        progressDialog.show();
    }

    private  void  dismiss(){
        progressDialog.dismiss();
    }
    @Override
    protected void onStart() {
        super.onStart();

        Query q=reference;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaCategoria.clear();
                dismiss();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ClsCategoria artist = postSnapshot.getValue(ClsCategoria.class);
                    listaCategoria.add(artist);
                }
                for (ClsCategoria item:  listaCategoria   ) {
                    System.out.println( item.getIdcategory());
                    ListProducCategory(item.getIdcategory());
                }
                       // myAdapter=new AdapterArchivos(getApplicationContext(),birdList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dismiss();
            }
        });

    }
    private void ListProducCategory(String idcategory){

        //String URL = "https://api.mercadolibre.com/sites/MPE/search?q=" + edtNombre.getText().toString() ;
        String URL="https://api.mercadolibre.com/sites/MPE/search?category="+idcategory;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    StaggeredGridLayoutManager      mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    JSONArray jsonData = response.getJSONArray("results");
                    Log.e("json", response.toString());
                    //  Log.e("test", jsonData.toString());
                    for (int i = 0; i < jsonData.length(); i++) {
                        JSONObject object = jsonData.getJSONObject(i);
                        String asin = object.getString("id");
                        String titulo = object.getString("title");
                        String precio = object.getString("price");
                        String imagen = object.getString("thumbnail");
                        String URL = object.getString("permalink");
                        String currency_id = object.getString("currency_id");
                        String condition = object.getString("condition");

                        ClsArticulo articulo = new ClsArticulo(asin, titulo, URL, precio, imagen,currency_id,condition);
                      //    Log.e("product", asin + " " + titulo + " " + URL + " " + precio + " " + imagen);
                        Articulo.add(articulo);
                    }
                    ArticuloMLAdapter adaptadorRecycler = new ArticuloMLAdapter(ConsultaMercadoLibre2.this, Articulo);
                    mRecyclerView.setAdapter(adaptadorRecycler);


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
    public void ObtenerConsultaProducto(final ArrayList lista) {
        String URL = "https://api.mercadolibre.com/sites/MPE/search?q=" + edtNombre.getText().toString() ;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                StaggeredGridLayoutManager      mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager);


                try {
                    JSONArray jsonData = response.getJSONArray("results");
                    Log.e("json", response.toString());
                  //  Log.e("test", jsonData.toString());
                    for (int i = 0; i < jsonData.length(); i++) {
                        JSONObject object = jsonData.getJSONObject(i);
                        String asin = object.getString("id");
                        String titulo = object.getString("title");
                        String precio = object.getString("price");
                        String imagen = object.getString("thumbnail");
                        String URL = object.getString("permalink");
                        String currency_id = object.getString("currency_id");
                        String condition = object.getString("condition");

                        ClsArticulo articulo = new ClsArticulo(asin, titulo, URL, precio, imagen,currency_id,condition);
                     //   Log.e("product", asin + " " + titulo + " " + URL + " " + precio + " " + imagen);
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