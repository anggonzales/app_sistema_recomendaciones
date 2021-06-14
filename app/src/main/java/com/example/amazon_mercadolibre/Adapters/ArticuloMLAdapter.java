package com.example.amazon_mercadolibre.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticuloMLAdapter extends RecyclerView.Adapter<ArticuloMLAdapter.ViewHolder> {

    List<ClsArticulo> ListaArticuloML;
    Context micontext;

    public ArticuloMLAdapter(Context context, List<ClsArticulo> datos) {
        this.ListaArticuloML = datos;
        this.micontext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(micontext).inflate(R.layout.item_consulta_gearbest, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.Asin.setText(ListaArticuloML.get(i).getAsin());
        holder.Titulo.setText(ListaArticuloML.get(i).getTitulo());
        holder.Precio.setText(" S/ " + ListaArticuloML.get(i).getPrecio());
        Picasso.with(micontext).load(ListaArticuloML.get(i).getImagen()).into(holder.getImagenML());
        holder.URL.setText(ListaArticuloML.get(i).getURL());
        final String URLML = ListaArticuloML.get(i).getURL();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                micontext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLML)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListaArticuloML.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Asin;
        public TextView Titulo;
        public TextView Precio;
        public TextView URL;
        public ImageView ImagenML;
        public Button Ver;

        public ViewHolder(View itemView) {
            super(itemView);
            Asin = (TextView) itemView.findViewById(R.id.txtAsin);
            Titulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            Precio = (TextView) itemView.findViewById(R.id.txtPrecio);
            URL = (TextView) itemView.findViewById(R.id.txtURL);
            ImagenML = (ImageView) itemView.findViewById(R.id.ImgArticuloML);
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

        public ImageView getImagenML() {
            return ImagenML;
        }
    }
}
