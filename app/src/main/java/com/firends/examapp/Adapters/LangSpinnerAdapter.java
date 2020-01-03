package com.firends.examapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firends.examapp.R;

public class LangSpinnerAdapter extends BaseAdapter {


    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;

    public LangSpinnerAdapter(Context context, int[] flags, String[] countryNames ) {
        this.context = context;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(context));
     }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public String getItem(int position) {
        return countryNames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.iconandtext, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.flagicon);
        TextView names = (TextView) convertView.findViewById(R.id.txt_lang);
        icon.setImageResource(flags[position]);
        names.setText(countryNames[position]);
        return convertView;
    }
}
