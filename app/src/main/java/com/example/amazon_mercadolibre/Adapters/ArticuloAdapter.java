package com.example.amazon_mercadolibre.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.Model.ClsCategoria;
import com.example.amazon_mercadolibre.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ViewHolder> {

    List<ClsArticulo> ListaArticulo;
    Context micontext;

    public ArticuloAdapter(Context context, List<ClsArticulo> datos) {
        this.ListaArticulo = datos;
        this.micontext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(micontext).inflate(R.layout.item_productamazon, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
      //  holder.Asin.setText(ListaArticulo.get(i).getAsin());
        holder.Titulo.setText(ListaArticulo.get(i).getTitulo());
        holder.Precio.setText(ListaArticulo.get(i).getCurrency_id()+" "+ListaArticulo.get(i).getPrecio());
        Picasso.with(micontext).load(ListaArticulo.get(i).getImagen()).into(holder.getImagen());
      //  holder.URL.setText(ListaArticulo.get(i).getURL());
        final String URLAMZ = ListaArticulo.get(i).getURL();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                micontext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLAMZ)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListaArticulo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Asin;
        public TextView Titulo;
        public TextView Precio;
        public TextView URL;
        public ImageView Imagen;
        public Button Ver;

        public ViewHolder(View itemView) {
            super(itemView);
        //    Asin = (TextView) itemView.findViewById(R.id.txtAsin);
            Titulo = (TextView) itemView.findViewById(R.id.txtTituloAM);
            Precio = (TextView) itemView.findViewById(R.id.txtPrecioAM);
        //    URL = (TextView) itemView.findViewById(R.id.txtURL);
            Imagen = (ImageView) itemView.findViewById(R.id.ImgArticuloAM);
        }

        public ImageView getImagen() {
            return Imagen;
        }

        public TextView getAsin() {
            return Asin;
        }

        public TextView getTitulo() {
            return Titulo;
        }

        public TextView getPrecio() {
            return Precio;
        }

        public TextView getURL() {
            return URL;
        }
    }
}
