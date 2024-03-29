package com.example.recyclerandcardviewapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;

    public Adapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDescription.setText(models.get(position).getDescription());
        holder.mImageView.setImageResource(models.get(position).getImg());

        //Agregando método Click del holder
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String hTitle=models.get(position).getTitle();
                String hDesc=models.get(position).getDescription();

                BitmapDrawable bitmapDrawable=(BitmapDrawable)holder.mImageView.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();

                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
                byte[]bytes=stream.toByteArray();

                String hProf=models.get(position).getProfessor();
                String hDay=models.get(position).getDay();
                String hHour=models.get(position).getHour().toString();

                //Formateando String para obtener fecha
                DateFormat formatter=new SimpleDateFormat("dd-MM-yy");
                String hDate=formatter.format(models.get(position).getDate().getTime());

                //Obteniendo los datos con intent y enviándolos a la Activity2
                Intent intent=new Intent(c, Activity2.class);

                intent.putExtra("iTitle", hTitle);
                intent.putExtra("iDesc", hDesc);
                intent.putExtra("iImage", bytes);
                intent.putExtra("iProf", hProf);
                intent.putExtra("iDay", hDay);
                intent.putExtra("iHour", hHour);
                intent.putExtra("iDate", hDate);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

