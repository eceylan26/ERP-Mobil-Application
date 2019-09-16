package com.example.ersinceylan.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ersin.Ceylan on 8/22/2017.
 */

public class AdapterBillList extends ArrayAdapter<BillClass>
{
    private static final String TAG = "PersonListAdapter";
    private Context mContext;
    int mResource;

    public AdapterBillList(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<BillClass> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        String date=getItem(position).getDate();
        String kind=getItem(position).getKind();
        String client=getItem(position).getClient();
        String place=getItem(position).getPlace();
        String billNo=getItem(position).getBillNo();
        String chapter=getItem(position).getChapter();
        String amount=getItem(position).getAmount();

        BillClass billClass = new BillClass(date,billNo,kind,client,amount,place,chapter);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.styleDateBill);
        TextView tvKind = (TextView) convertView.findViewById(R.id.styleKindBill);
        TextView tvClient = (TextView) convertView.findViewById(R.id.styleClientBill);
        TextView tvPlace= (TextView) convertView.findViewById(R.id.stylePlaceBill);
        TextView tvBillNo= (TextView) convertView.findViewById(R.id.styleBillNoBill);
        TextView tvChapter= (TextView) convertView.findViewById(R.id.styleChapterBill);
        TextView tvAmount= (TextView) convertView.findViewById(R.id.styleAmountBill);


        tvDate.setText(date);
        tvKind.setText(kind);
        tvClient.setText(client);
        tvPlace.setText(place);
        tvBillNo.setText(billNo);
        tvChapter.setText(chapter);
        tvAmount.setText(amount);


        return convertView;
    }
}
