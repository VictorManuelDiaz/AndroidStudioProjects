package com.example.examen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    //Atributos
    private Context context;
    private ArrayList<Model> products; //ArrayList de productos

    //Constructor generado con los dos atributos definidos
    public Adapter(Context context, ArrayList<Model> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();//Regresando cantidad de elementos en la lista
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);//Obteniendo un item de la colección
    }

    @Override
    public long getItemId(int position) {
        return position;
    }//Obteniendo id del item de la colección

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//Mostrando cada elemento de la lista
        ViewHolder holder;
        if (convertView==null){
            //Inflando cada fila del Lista personalizada
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(R.layout.item_list, null);
            holder=new ViewHolder();

            //Referenciando los elementos a mostrar
            holder.imageViewProduct=(ImageView)convertView.findViewById(R.id.ivProduct);
            holder.textViewName=(TextView)convertView.findViewById(R.id.tvName);
            holder.textViewPrice=(TextView)convertView.findViewById(R.id.tvPrice);
            holder.textViewPresentation=(TextView)convertView.findViewById(R.id.tvPresentation);
            holder.textViewLaboratory=(TextView)convertView.findViewById(R.id.tvLaboratory);
            holder.textViewStock=(TextView)convertView.findViewById(R.id.tvStock);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)convertView.getTag();
        }

        //Definiendo un objeto del Modelo con el que se
        //obtiene la posición de los elementos de la lista
        Model current_Item=products.get(position);
        holder.textViewName.setText((current_Item.getName()));
        holder.textViewPrice.setText((current_Item.getPrice()));
        holder.textViewPresentation.setText((current_Item.getPresentation()));
        holder.textViewLaboratory.setText((current_Item.getLaboratory()));
        holder.textViewStock.setText((current_Item.getStock()));
        //Utilizando setImageResource para asignar desde la carpeta drawable
        holder.imageViewProduct.setImageResource(current_Item.getImage());

        return convertView;//Retornando la vista
    }

    static class ViewHolder{
        //Definiendo elementos del item_list para llenar la lista
        private ImageView imageViewProduct;
        private TextView textViewName;
        private TextView textViewPrice;
        private TextView textViewPresentation;
        private TextView textViewLaboratory;
        private TextView textViewStock;
    }


}
