package com.example.objectlistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.objectlistapp.R;
import com.example.objectlistapp.objects.Estudiantes;

import com.example.objectlistapp.objects.Estudiante;

public class MyAdapterList extends BaseAdapter {
    private Context context;
    private int layout;
    private Estudiantes students;

    public MyAdapterList(Context context, int layout, Estudiantes students) {
        this.context = context;
        this.layout = layout;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        private TextView textViewNombres;
        private TextView textViewApellidos;
        private TextView textViewCorreo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(R.layout.item_list, null);
            holder=new ViewHolder();
            holder.textViewNombres=(TextView)convertView.findViewById(R.id.textVieNombres);
            holder.textViewApellidos=(TextView)convertView.findViewById(R.id.textViewApellidos);
            holder.textViewCorreo=(TextView)convertView.findViewById(R.id.textViewCorreo);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }

        Estudiante current_Item=students.get(position);
        holder.textViewNombres.setText((current_Item.getNombres()));
        holder.textViewApellidos.setText((current_Item.getApellidos()));
        holder.textViewCorreo.setText((current_Item.getCorreo()));

        return convertView;
    }
}
