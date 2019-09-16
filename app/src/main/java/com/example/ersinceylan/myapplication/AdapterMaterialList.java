package com.example.ersinceylan.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ersin.Ceylan on 8/11/2017.
 */

public class AdapterMaterialList extends ArrayAdapter<MaterialClass>
{
    private static final String TAG = "MaterialListAdapter";
    private Context mContext;
    int mResource;


    public AdapterMaterialList(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MaterialClass> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        String aciklama= getItem(position).getAciklama();
        String kod=getItem(position).getKod();

        MaterialClass materialClass = new MaterialClass(aciklama,kod);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvAciklama = (TextView) convertView.findViewById(R.id.styleKodMaterial);
        TextView tvKod = (TextView) convertView.findViewById(R.id.styleAciklamaMaterial);

        tvAciklama.setText(aciklama);
        tvKod.setText(kod);


        return convertView;
    }
}
