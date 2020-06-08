package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Definiendo un listView
    ListView myList;

    //Definiendo un objeto ArrayList utilizando el modelo creado
    ArrayList<Model> products=new ArrayList<>();

    //Creando objeto del adaptador
    Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Referenciando elementos del activity_main
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list);

        //Inicializando objeto del adaptador con el constructor definido
        myAdapter=new Adapter(this,fillList());

        //Estableciendo adaptador a la lista
        myList.setAdapter(myAdapter);

        //Creando evento para que la lista cargue el menu contextual
        myList.setOnCreateContextMenuListener(this);
    }

    //Creando método que llena la lista utilizando el modelo
    private ArrayList<Model> fillList(){

        //Definiendo un objeto del Modelo
        Model m=new Model();

        //Inicializando ArrayList con tres objetos
        //Objeto 1
        //Utilizando setter para dar valor a los atributos del Modelo
        m.setImage(R.drawable.alcohol);
        m.setName("Alcohol");
        m.setPrice("C$ 40.00");
        m.setPresentation("Botella 120ml");
        m.setLaboratory("Laboratorio 1");
        m.setStock("500");
        products.add(m);//Agregando al ArrayList

        //Objeto 2
        m=new Model();
        //Utilizando setter para dar valor a los atributos del Modelo
        m.setImage(R.drawable.aspirin);
        m.setName("Aspirina");
        m.setPrice("C$ 20.00");
        m.setPresentation("Tabletas");
        m.setLaboratory("Bayer");
        m.setStock("600");
        products.add(m);//Agregando al ArrayList

        //Objeto 3
        m=new Model();
        //Utilizando setter para dar valor a los atributos del Modelo
        m.setImage(R.drawable.gauze);
        m.setName("Gasa");
        m.setPrice("C$ 50.00");
        m.setPresentation("Rollo");
        m.setLaboratory("Laboratorio 2");
        m.setStock("800");
        products.add(m);//Agregando al ArrayList

        return products;
    }

    //Sobreescribiendo método para mostrar las opciones del menú contextual al presionar un elemento de la lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opciones");
        getMenuInflater().inflate(R.menu.menu_context_list, menu); //Inflando xml con los item
    }

    //Sobreescribiendo para programar acciones para cada item del menú contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.addNew: //Definiendo acciones para item addNew

                //Declarando diálogo de tipo final para ser reconocido por el evento onClick
                final Dialog dlg= new Dialog(this);

                //Asignando xml al diálogo
                dlg.setContentView(R.layout.add_new);
                dlg.setTitle("Agregar estudiante");

                //Evitando que el diálogo pueda ser cerrado
                dlg.setCancelable(false);

                //Poscionando diálogo en la parte inferior de la pantalla
                Window window=dlg.getWindow();
                WindowManager.LayoutParams wlp= window.getAttributes();
                wlp.gravity= Gravity.BOTTOM;
                window.setAttributes(wlp);

                //Referenciando botones del diálogo
                Button btAdd=(Button)dlg.findViewById(R.id.btAdd);
                Button btCancel=(Button)dlg.findViewById(R.id.btCancel);

                //Definiendo evento OnClick del botón Añadir
                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Referenciando los EditText de add_new.xml
                        EditText editText_Name=(EditText) dlg.findViewById(R.id.etName);
                        EditText editText_Price=(EditText) dlg.findViewById(R.id.etPrice);
                        EditText editText_Presentation=(EditText) dlg.findViewById(R.id.etPresentation);
                        EditText editText_Laboratory=(EditText) dlg.findViewById(R.id.etLaboratory);
                        EditText editText_Stock=(EditText) dlg.findViewById(R.id.etStock);

                        //Verificando que todos los EditText se encuentren llenados
                        if (editText_Name.getText().toString().contentEquals("") ||
                                editText_Price.getText().toString().contentEquals("") ||
                                editText_Presentation.getText().toString().contentEquals("") ||
                                editText_Laboratory.getText().toString().contentEquals("") ||
                                editText_Stock.getText().toString().contentEquals("")){
                            Toast.makeText(MainActivity.this,"Campos obligatorios", Toast.LENGTH_LONG).show();
                        }
                        else{

                            //Creando objeto de la clase Model
                            Model m=new Model();

                            //Asignando los valores al objeto a través de los setter
                            m.setImage(R.drawable.picture);
                            m.setName(editText_Name.getText().toString());
                            m.setPrice("C$ "+editText_Price.getText().toString());
                            m.setPresentation(editText_Presentation.getText().toString());
                            m.setLaboratory(editText_Laboratory.getText().toString());
                            m.setStock(editText_Stock.getText().toString());
                            products.add(m);//Agregando al ArrayList
                            myAdapter.notifyDataSetChanged();//Actualizando el adaptador

                            //Limpiando los EditText
                            editText_Name.setText("");
                            editText_Price.setText("");
                            editText_Presentation.setText("");
                            editText_Laboratory.setText("");
                            editText_Stock.setText("");

                            dlg.cancel();//Cerrando diálogo
                        }
                    }
                });

                //Definiendo evento OnClick del botón Cancelar
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });
                //Mostrando diálogo
                dlg.show();
                return true;
            case R.id.delete://Definiendo acciones para item delete
                this.products.remove(info.position);//Removiendo un elemento de la lista
                this.myAdapter.notifyDataSetChanged();//Actualizando el adaptador
                return true;
            case R.id.close://Definiendo acciones para item close
                //Agregando método para cerrar aplicación
                finish();
                break;
        }
        return  super.onContextItemSelected(item);
    }
}
