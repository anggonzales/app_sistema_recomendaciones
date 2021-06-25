package com.example.amazon_mercadolibre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.amazon_mercadolibre.Adapters.AdapterArticulo;
import com.example.amazon_mercadolibre.Adapters.AdapterGridView;
import com.example.amazon_mercadolibre.Adapters.ArticuloAdapter;
import com.example.amazon_mercadolibre.Adapters.ArticuloMLAdapter;
import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.Model.ClsCategoria;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Categorias extends AppCompatActivity {


    private DatabaseReference reference,reference2;
    RecyclerView recyclerView,mRecyclerView;
    ArrayList Articulo = new ArrayList();
    List<ClsArticulo>  listaArticulo;
    //private StaggeredGridLayoutManager mLayoutManager;
    GridView gridlist;
    JsonObjectRequest objRequest;

    AdapterGridView myAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        reference= FirebaseDatabase.getInstance().getReference("CategoriaProducto");
        recyclerView=(RecyclerView)findViewById(R.id.recyclercategorias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaArticulo= new ArrayList<>();
     //   mRecyclerView=(RecyclerView)findViewById(R.id.recyclerproductos);
        gridlist=(GridView)findViewById(R.id.simpleGridView1);
        Charge();

    }
    private void Charge(){
        progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("cargando");
        progressDialog.show();
    }

    private  void  dismiss(){
        progressDialog.dismiss();
    }
    int row_index=0;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<ClsCategoria> recyclerOptions = new FirebaseRecyclerOptions.Builder<ClsCategoria>()
                .setQuery(reference, ClsCategoria.class).build();
        FirebaseRecyclerAdapter<ClsCategoria,Items> adapter =new FirebaseRecyclerAdapter<ClsCategoria, Items>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull final Items items, final int i, @NonNull ClsCategoria claseesprofe) {
                final String key = getRef(i).getKey();
                reference.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            dismiss();
                            row_index++;
                            final String nombre=dataSnapshot.child("nombre").getValue().toString();
                            items.tvtnombre.setText(nombre);
                            final String icategory =dataSnapshot.child("idcategory").getValue().toString();
                            String  rutafoto=dataSnapshot.child("img").getValue().toString();
                            if (i==0){
                                ListarProducCategory(icategory);
                            }
                            Glide.with(Categorias.this).load(rutafoto)
                                    .placeholder(R.drawable.default_profile_image)
                                    .into(items.imgcam);
                            items.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                   ListarProducCategory(icategory);
                                    row_index=i;
                                   // items.linear.setBackgroundColor(Color.YELLOW);

                                    //items.linear.setBackgroundColor(Color.YELLOW);
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        dismiss();
                    }
                });
            }

            @NonNull
            @Override
            public Items onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoryes, parent, false);
                return new Items(vista);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    private  void Blanco(){

    }
    private  void ListarProducCategory(String idcategory){
        final ArrayList<ClsArticulo> listaSedes2=new ArrayList<>();
        listaArticulo.clear();
       // Toast.makeText(Categorias.this, idcategory, Toast.LENGTH_SHORT).show();
        String URL="https://api.mercadolibre.com/sites/MPE/search?category="+idcategory;
        RequestQueue requestQueue = Volley.newRequestQueue(Categorias.this);

         objRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ClsArticulo arti=null;
              //  StaggeredGridLayoutManager   mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
             //   mRecyclerView.setLayoutManager(mLayoutManager);

                final ArrayList<ClsArticulo> listaSedes3=new ArrayList<>();
                try {
                    JSONArray jsonData = response.getJSONArray("results");
                    Log.e("test", jsonData.toString());
                    for (int i = 0; i < jsonData.length(); i++) {
                    //    JSONObject object = jsonData.getJSONObject(i);
                        arti= new ClsArticulo();
                        JSONObject object=null;
                        object=jsonData.getJSONObject(i);
                        arti.setAsin(object.getString("id"));
                        arti.setTitulo(object.getString("title"));
                        arti.setURL(object.getString("permalink"));
                        arti.setPrecio(object.getString("price"));
                        arti.setImagen(object.getString("thumbnail"));
                        arti.setCurrency_id(object.getString("currency_id"));
                        arti.setCondition(object.getString("condition"));
                     //   ClsArticulo articulo = new ClsArticulo(asin, titulo, URL, precio, imagen,currency_id,condition);
                        listaSedes3.add(arti);
                    }
                    myAdapter=new AdapterGridView(getApplicationContext(),listaSedes3);
                  //  AdapterArticulo adaptera=new AdapterArticulo(listaSedes3);
                  //  mRecyclerView.setAdapter(adaptera);
                    gridlist.setAdapter(myAdapter);

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
    public   class Items extends RecyclerView.ViewHolder{
        TextView tvtnombre,tvcargo;
        ImageView imgcam;
        LinearLayout linear;
// android:background="@color/colorList"
        public Items(final View itemView) {
            super(itemView);
            tvtnombre=(TextView)itemView.findViewById(R.id.txtnamecategory);
            imgcam=(ImageView)itemView.findViewById(R.id.imgcategory);
            linear=(LinearLayout)itemView.findViewById(R.id.linear1);
        }
    }
}