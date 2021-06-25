package com.example.amazon_mercadolibre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazon_mercadolibre.Model.ClsBusqueda;
import com.example.amazon_mercadolibre.Model.ClsCategoria;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity {

    String id_usuario,nombre_usuario,foto_usuario;
    List<ClsBusqueda> listaBusqueda;
    private DatabaseReference reference,referencebusquedas;
    private StaggeredGridLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<ClsCategoria,MovieViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        listaBusqueda = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclebusqueda);


        id_usuario=getIntent().getStringExtra("iduser");
        referencebusquedas= FirebaseDatabase.getInstance().getReference("Busquedas").child(id_usuario);
        mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ClsBusqueda> options = new FirebaseRecyclerOptions.Builder<ClsBusqueda>().setQuery(referencebusquedas, ClsBusqueda.class).build();
        FirebaseRecyclerAdapter<ClsBusqueda,MovieViewHolder> adapter =
                new FirebaseRecyclerAdapter<ClsBusqueda, MovieViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MovieViewHolder holder, int position, @NonNull final ClsBusqueda model) {

                        holder.tvBusqueda.setText(model.getBusqueda());
                    }

                    @NonNull
                    @Override
                    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View  view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_busquedas,viewGroup,false);
                        MovieViewHolder viewHolder = new MovieViewHolder(view);
                        return viewHolder;
                    }
                };

        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView tvBusqueda;
        public MovieViewHolder(View v) {
            super(v);
            tvBusqueda = (TextView) v.findViewById(R.id.txtbusqueda);

        }
    }

}