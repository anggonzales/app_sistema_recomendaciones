package com.example.amazon_mercadolibre.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazon_mercadolibre.ConsultaMercadoLibre2;
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
        View v = LayoutInflater.from(micontext).inflate(R.layout.item_products, parent, false);
        return new ViewHolder(v);

        //item_consulta_gearbest
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        //holder.Asin.setText(ListaArticuloML.get(i).getAsin());
     //   holder.Titulo.setText(ListaArticuloML.get(i).getTitulo());
     //   holder.Precio.setText(" S/ " + ListaArticuloML.get(i).getPrecio());

     //   Glide.with(holder.itemView.getContext())
      //          .load(ListaArticuloML.get(i).getImagen())
     //           .placeholder(R.drawable.default_profile_image)
     //           .error(R.drawable.default_profile_image)
     //           .override(300,300)
     ///           .centerCrop()
     //           .into(holder.ImagenML);
      //  Log.e("img",ListaArticuloML.get(i).getImagen());
       // Picasso.with(micontext).load(ListaArticuloML.get(i).getImagen()).into(holder.ImagenML);
      //  holder.URL.setText(ListaArticuloML.get(i).getURL());
     //   final String URLML = ListaArticuloML.get(i).getURL();
      //  holder.itemView.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View v) {
       //         micontext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLML)));
      ///      }
     //   });
String urlimg="http://http2.mlstatic.com/D_612195-MPE43966147992_112020-O.jpg";
        if (holder instanceof ViewHolder){

            final ViewHolder datgolder =(ViewHolder)holder;
            datgolder.Titulo.setText(ListaArticuloML.get(i).getTitulo());
            datgolder.Precio.setText(ListaArticuloML.get(i).getCurrency_id() +" "+ ListaArticuloML.get(i).getPrecio());
            datgolder.condition.setText( ListaArticuloML.get(i).getCondition());
            Picasso.with(datgolder.ImagenML.getContext()).load(ListaArticuloML.get(i).getImagen()).resize(300, 300)
                    .centerCrop().into(datgolder.ImagenML);
            final String URLML = ListaArticuloML.get(i).getURL();
            datgolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    micontext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLML)));
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return ListaArticuloML.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Asin;
        public TextView Titulo,condition;
        public TextView Precio;
        public TextView URL;
        public ImageView ImagenML;
        public Button Ver;
        String link;
        public ViewHolder(View itemView) {
            super(itemView);
            // Asin = (TextView) itemView.findViewById(R.id.txtAsin);
            Titulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            Precio = (TextView) itemView.findViewById(R.id.txtPrecio);
           // URL = (TextView) itemView.findViewById(R.id.txtURL);
            ImagenML = (ImageView) itemView.findViewById(R.id.ImgArticuloML1);
            condition=(TextView)itemView.findViewById(R.id.condition);
        }

      /*  public TextView getAsin() {
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
        } */
    }
}
