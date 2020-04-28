package com.example.myapplicationlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView myList;
    List<String> things;
    EditText etItem;
    Button btAdd;
    ArrayAdapter<String> AdapterList;

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
        things=new ArrayList<String>();
        things.add("Sombrero");
        things.add("Bicicleta");
        things.add("Libro");
        things.add("Lápiz");
        things.add("Zapatos");
        things.add("Camisa");

        AdapterList=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, things);
        myList.setAdapter(AdapterList);

    }

    void selectItemList(){
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item seleccionado: " + things.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    void addItemList() {
        btAdd=(Button)findViewById(R.id.btAdd);
        etItem=(EditText)findViewById(R.id.etItem);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etItem.getText().toString().contentEquals("")) {
                    Toast.makeText(MainActivity.this, "Campo vacío", Toast.LENGTH_LONG).show();
                }
                else {
                    etItem=(EditText)findViewById(R.id.etItem);
                    things.add(etItem.getText().toString());
                    AdapterList.notifyDataSetChanged();
                    etItem.setText("");
                }
            }
        });
    }
}
