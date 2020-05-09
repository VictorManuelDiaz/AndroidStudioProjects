package com.example.trabajoiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapterList extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> subjects;

    public MyAdapterList(Context context, int layout, List<String> subjects) {
        this.context = context;
        this.layout = layout;
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(R.layout.item_list, null);
            holder= new ViewHolder();
            holder.textViewSubject=(TextView)convertView.findViewById(R.id.tvItem);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }
        String current_Item=subjects.get(position);
        holder.textViewSubject.setText(current_Item);

        return convertView;
    }

    static class ViewHolder {
        private TextView textViewSubject;
    }
}
