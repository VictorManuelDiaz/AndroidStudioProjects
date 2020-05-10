package com.example.objectlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.objectlistapp.adapter.MyAdapterList;
import com.example.objectlistapp.objects.Estudiante;
import com.example.objectlistapp.objects.Estudiantes;

public class MainActivity extends AppCompatActivity {
    ListView myList;
    Estudiantes students;
    Estudiante student;
    MyAdapterList Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list1);
        llenarLista();

    }
    void llenarLista () {

        students= new Estudiantes();
        student= new Estudiante("Esther", "Duarte", "12458", "esther@gmail.com", "85225145");
        students.add(student);
        student= new Estudiante("Selena", "Gómez", "12485", "selena@gmail.com", "85227788");
        students.add(student);
        Adapter=new MyAdapterList(this,R.layout.item_list,students);
        myList.setAdapter(Adapter);
    }
}
