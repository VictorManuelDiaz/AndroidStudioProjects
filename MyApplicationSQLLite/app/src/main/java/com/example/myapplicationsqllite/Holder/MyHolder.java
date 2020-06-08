package com.example.myapplicationsqllite.Holder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplicationsqllite.R;

//Implementando OnCreateContextMenuListeneren en el ViewHolder
public class MyHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public ImageView mImageView;
    public TextView mTitle, mDescription;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        //Elementos del archivo row.xml
        this.mImageView = itemView.findViewById(R.id.imagelv);
        this.mTitle = itemView.findViewById(R.id.title);
        this.mDescription = itemView.findViewById(R.id.description);

        //Creando evento para que el RecyclerView cargue el menu contextual
        itemView.setOnCreateContextMenuListener(this);
    }

    //Creando menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //Añadiendo título del menu contextual
        menu.setHeaderTitle("Opciones");

        //Añadiendo items del menu contextual
        menu.add(0, 0, getAdapterPosition(), "Actualizar");//groupId, itemId, order, título
        menu.add(0, 1, getAdapterPosition(), "Eliminar");//groupId, itemId, order, título
    }
}
