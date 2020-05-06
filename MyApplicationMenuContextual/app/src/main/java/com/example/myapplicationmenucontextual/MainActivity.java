package com.example.myapplicationmenucontextual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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
    List<String> students;
    MyAdapterList Adapter;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opciones");
        getMenuInflater().inflate(R.menu.menu_context_list, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete:
                this.students.remove(info.position);
                this.Adapter.notifyDataSetChanged();
                return true;
            case R.id.addNew:
                final Dialog dlg= new Dialog(this);
                dlg.setContentView(R.layout.add_new);
                dlg.setTitle("Agregar estudiante");
                dlg.setCancelable(false);
                Button btAdd=(Button)dlg.findViewById(R.id.btAdd);
                Button btCancel=(Button)dlg.findViewById(R.id.btCancel);

                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText_Name=(EditText) dlg.findViewById(R.id.etNames);
                        if (editText_Name.getText().toString().contentEquals("")){
                            Toast.makeText(MainActivity.this,"Ingrese nombre a agregar", Toast.LENGTH_LONG).show();
                        }
                        else{
                            students.add(editText_Name.getText().toString());
                            Adapter.notifyDataSetChanged();
                            editText_Name.setText("");
                            dlg.cancel();
                        }
                    }
                });
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });
                dlg.show();
                break;
        }
        return  super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list1);
        myList.setOnCreateContextMenuListener(this);
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
