package com.example.amazon_mercadolibre.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amazon_mercadolibre.Model.ClsArticulo;
import com.example.amazon_mercadolibre.R;

import java.util.ArrayList;

public class AdapterGridView  extends ArrayAdapter<ClsArticulo> {


    Context context;

    ViewHolder viewHolder;
    ArrayList<ClsArticulo> al_menu = new ArrayList<>();
    public AdapterGridView(Context context, ArrayList<ClsArticulo> al_menu) {
        super(context, R.layout.item_gridw, al_menu);
        this.al_menu = al_menu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return al_menu.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_menu.size() > 0) {
            return al_menu.size();
        } else {
            return 1;
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView== null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_gridw, parent, false);
            viewHolder.tv_foldern = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView2);//

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(getContext()).load(al_menu.get(position).getImagen())
                .placeholder(R.drawable.default_profile_image)
                .into(viewHolder.img);

        viewHolder.tv_foldern.setText(al_menu.get(position).getTitulo());
        return convertView;

    }




    public static class ViewHolder {
        ImageView img,imgicnono;
        TextView tv_foldern, tv_foldersize;

    }


}
