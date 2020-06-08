package com.example.myapplicationsqllite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationsqllite.Entities.Asignatura;
import com.example.myapplicationsqllite.Holder.MyHolder;
import com.example.myapplicationsqllite.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    List<Asignatura> listAsignaturas; //ArrayList de Asignaturas

    //Constructor generado
    public MyAdapter(Context c, List<Asignatura> listAsignaturas) {
        this.c = c;
        this.listAsignaturas = listAsignaturas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Infla cada fila del RecyclerVIew
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);

        //Retornando la vista
        return new MyHolder(view);
    }

    //Mostrando los datos en la posición especificada
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mTitle.setText(listAsignaturas.get(position).getTitle());
        holder.mDescription.setText(listAsignaturas.get(position).getDescription());
    }

    //Retornando tamaño de la lista
    @Override
    public int getItemCount() {
        return listAsignaturas.size();
    }
}
