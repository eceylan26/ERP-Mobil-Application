package com.example.ersinceylan.myapplication;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ersin.Ceylan on 8/11/2017.
 */

public class AdapterCarAccList extends ArrayAdapter<CariAccClass>
{
    private static final String TAG = "PersonListAdapter";
    private Context mContext;
    int mResource;


    public AdapterCarAccList(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<CariAccClass> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        String isim= getItem(position).getIsim();
        String unvan=getItem(position).getUnvan();
        String mevduat=getItem(position).getMevduat();
        String kod=getItem(position).getKod();

        CariAccClass cariAccClass = new CariAccClass(isim,unvan,mevduat,kod);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvIsim = (TextView) convertView.findViewById(R.id.styleIsim);
        TextView tvUnvan = (TextView) convertView.findViewById(R.id.styleUnvan);
        TextView tvMevduat = (TextView) convertView.findViewById(R.id.styleMevduat);
        TextView tvKod= (TextView) convertView.findViewById(R.id.styleKod);

        tvIsim.setText(isim);
        tvUnvan.setText(unvan);
        tvMevduat.setText(mevduat);
        tvKod.setText(kod);

        return convertView;
    }
}
