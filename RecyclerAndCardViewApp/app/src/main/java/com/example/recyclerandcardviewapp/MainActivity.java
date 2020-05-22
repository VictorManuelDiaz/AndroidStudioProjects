package com.example.recyclerandcardviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recyclerView1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new Adapter(this,getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }

    private ArrayList<Model> getMyList(){
        ArrayList<Model> models=new ArrayList<>();
        Model m=new Model();

        m.setTitle("Facultativa II");
        m.setDescription("Descripción de Facultativa II");
        m.setImg(R.drawable.app);
        models.add(m);

        m=new Model();
        m.setTitle("Investigación");
        m.setDescription("Descripción de Investigación");
        m.setImg(R.drawable.files);
        models.add(m);

        m=new Model();
        m.setTitle("Planificación TIC");
        m.setDescription("Descripción de Planificación");
        m.setImg(R.drawable.pc);
        models.add(m);
        return models;
    }
}
