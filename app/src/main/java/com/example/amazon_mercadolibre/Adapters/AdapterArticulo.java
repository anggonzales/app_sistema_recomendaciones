package com.example.amazon_mercadolibre.Adapters;

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

import com.bumptech.glide.Glide;
import com.example.amazon_mercadolibre.Categorias;
import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterArticulo extends  RecyclerView.Adapter<AdapterArticulo.ViewHolderDatos> implements View.OnClickListener  {

    ArrayList<ClsArticulo> listaArticulo;

    public AdapterArticulo(ArrayList<ClsArticulo> listaArticulo) {
        this.listaArticulo = listaArticulo;
    }

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @NotNull
    @Override
    public AdapterArticulo.ViewHolderDatos onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product2, parent, false);

        return new  ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderDatos holder, int i) {
        if (holder instanceof ViewHolderDatos){
            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.Titulo.setText(listaArticulo.get(i).getTitulo());

            Glide.with(items.ImagenML.getContext()).load(listaArticulo.get(i).getImagen())
                    .placeholder(R.drawable.default_profile_image)
                    .into(items.ImagenML);
            final String URLML = listaArticulo.get(i).getURL();


        }
    }

    @Override
    public int getItemCount() {
        return listaArticulo.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        public TextView Asin;
        public TextView Titulo,condition;
        public TextView Precio;
        public TextView URL;
        public ImageView ImagenML;
        public Button Ver;
        String link;
        public ViewHolderDatos(@NonNull @NotNull View itemView) {
            super(itemView);
            Titulo = (TextView) itemView.findViewById(R.id.txtTitulo2);
            ImagenML = (ImageView) itemView.findViewById(R.id.ImgArticuloML2);

        }
    }
}
