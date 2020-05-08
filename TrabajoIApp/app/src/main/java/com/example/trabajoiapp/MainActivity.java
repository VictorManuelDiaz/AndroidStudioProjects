package com.example.trabajoiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView myList;
    List<String> subjects;
    EditText etItem;
    Button btAdd;
    MyAdapterList Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list1);
        fillList();
        selectItemList();
        addItemList();
    }
    void fillList (){
        subjects =new ArrayList<String>();

        subjects.add("Facultativa II");
        subjects.add("Planificación TIC");
        subjects.add("Investigación aplicada");

        Adapter=new MyAdapterList(this, R.layout.item_list, subjects);
        myList.setAdapter(Adapter);

    }

    void selectItemList(){
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item seleccionado: " + subjects.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }
    void addItemList() {
        btAdd=(Button)findViewById(R.id.button);
        etItem=(EditText)findViewById(R.id.editText);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etItem.getText().toString().contentEquals("")) {
                    Toast.makeText(MainActivity.this, "Campo vacío", Toast.LENGTH_LONG).show();
                }
                else {
                    etItem=(EditText)findViewById(R.id.editText);
                    subjects.add(etItem.getText().toString());
                    Adapter.notifyDataSetChanged();
                    etItem.setText("");
                }
            }
        });
    }
}
