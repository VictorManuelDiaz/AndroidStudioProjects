package com.example.myapplicationadapterlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView myList;
    List<String> students;
    MyAdapterList Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list1);
        fillList();
        selectItemList();
    }

    void fillList(){
        students=new ArrayList<String>();

        students.add("Esther Duarte");
        students.add("Víctor Díaz");
        students.add("Selena Gómez");

        Adapter=new MyAdapterList(this, R.layout.item_list, students);
        myList.setAdapter(Adapter);
    }

    void selectItemList(){
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item seleccionado: " + students.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}
