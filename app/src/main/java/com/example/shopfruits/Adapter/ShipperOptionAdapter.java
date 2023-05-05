package com.example.shopfruits.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shopfruits.Models.User;

import java.util.List;

public class ShipperOptionAdapter extends ArrayAdapter<User> {

    public ShipperOptionAdapter(Context context, List<User> options) {
        super(context, android.R.layout.simple_spinner_item, options);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        User option = getItem(position);
        textView.setText(String.valueOf(option.getName()));
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        User option = getItem(position);
        textView.setText(String.valueOf(option.getName()));
        return textView;
    }
}
