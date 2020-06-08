package com.example.myapplicationsqllite.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplicationsqllite.Adapter.MyAdapter;
import com.example.myapplicationsqllite.Config.Constants;
import com.example.myapplicationsqllite.Database.AppDatabase;
import com.example.myapplicationsqllite.Entities.Asignatura;
import com.example.myapplicationsqllite.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Definiendo objetos
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    //Definiendo un objeto List utilizando el modelo creado
    List<Asignatura> listAsignaturas;

    //Objeto de la clase AppDatabase para conectar con la Base de Datos
    AppDatabase db;

    //Creando botón
    Button btAdd;

    //Sobreescribiendo método para programar acciones por cada item del menú contextual
    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()){
            case 0: //Definiendo acciones para item 0 correspondiente a Actualizar

                //Declarando diálogo de tipo final para ser reconocido por el evento Click
                final Dialog dialog=new Dialog(MainActivity.this);

                //Asignando xml al diálogo
                dialog.setContentView(R.layout.add_new);
                dialog.setTitle("Agregar Asignaturas");

                //Evitando que el diálogo pueda ser cerrado
                dialog.setCancelable(false);

                //Poscionando diálogo en la parte inferior de la pantalla
                Window window=dialog.getWindow();
                WindowManager.LayoutParams wlp= window.getAttributes();
                wlp.gravity= Gravity.BOTTOM;
                window.setAttributes(wlp);

                //Referenciando botones del diálogo
                final Button btAddNew=(Button)dialog.findViewById(R.id.btAdd);
                Button btCancel=(Button)dialog.findViewById(R.id.btCancel);

                //Referenciando EditText del diálogo
                EditText editText_Name=(EditText)dialog.findViewById(R.id.etAsignatura);
                EditText editText_Des=(EditText)dialog.findViewById(R.id.etDescripcion);

                //Llenando los EditText con los valores del item seleccionado de la lista
                editText_Name.setText(listAsignaturas.get(item.getOrder()).getTitle().toString());
                editText_Des.setText(listAsignaturas.get(item.getOrder()).getDescription().toString());

                //Cambiando el texto del botón Añadir
                btAddNew.setText("Actualizar");

                //Definiendo evento OnClick del botón Añadir
                btAddNew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Llamando al método addItem
                        //Pasando como parámetros el diálogo, el botón Añadir y el item de la lista
                        addItem(dialog, btAddNew, item);
                    }
                });

                //Definiendo evento OnClick del botón Cancelar
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                //Mostrando el diálogo
                dialog.show();
                return true;
            case 1://Definiendo acciones para item 0 correspondiente a  Eliminar
                //Eliminando datos del objeto a la Base de Datos
                long resultadoDelete=db.asignaturaDao().deleteById(listAsignaturas.get(item.getOrder()).getId());
                if (resultadoDelete>0){

                    //Actualizando los datos mostrados en RecyclerView
                    //para visualizar en tiempo real cuando el dato es eliminado
                    listAsignaturas=db.asignaturaDao().getAllAsignatura();

                    //Llenando adaptador con la lista de asignaturas obtenidos de la Base de Datos
                    myAdapter=new MyAdapter(MainActivity.this, listAsignaturas);

                    //Estableciendo adaptador al RecyclerView
                    mRecyclerView.setAdapter(myAdapter);

                    //Mostrando mensaje de eliminación
                    Toast.makeText(MainActivity.this,"Asignatura eliminada",Toast.LENGTH_LONG).show();
                }
                else {
                    //Mostrando mensaje de error
                    Toast.makeText(MainActivity.this, "Error al eliminar asignatura", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conectando a la Base de Datos
        db= Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constants.BD_NAME)
                .allowMainThreadQueries()
                .build();

        //Referenciando los elementos del activity_main
        listAsignaturas=db.asignaturaDao().getAllAsignatura();
        btAdd=findViewById(R.id.btAdd);
        mRecyclerView=findViewById(R.id.recyclerView1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Creando objeto del adaptador utilizando el constructor definido
        //Llenando adaptador con la lista de asignaturas obtenidas de la Base de Datos
        myAdapter=new MyAdapter(this,listAsignaturas);

        //Estableciendo adaptador al RecyclerView
        mRecyclerView.setAdapter(myAdapter);

        //Definiendo evento Click del botón agregar
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Declarando diálogo de tipo final para ser reconocido por el evento Click
                final Dialog dialog=new Dialog(MainActivity.this);

                //Cargando diálogo del archivo addNew.xml
                dialog.setContentView(R.layout.add_new);
                dialog.setTitle("Agregar Asignaturas");
                dialog.setCancelable(false);

                //Poscionando diálogo en la parte inferior de la pantalla
                Window window=dialog.getWindow();
                WindowManager.LayoutParams wlp= window.getAttributes();
                wlp.gravity= Gravity.BOTTOM;
                window.setAttributes(wlp);

                //Referenciando botones del diálogo
                final Button btAddNew=(Button)dialog.findViewById(R.id.btAdd);
                Button btCancel=(Button)dialog.findViewById(R.id.btCancel);

                //Definiendo evento OnClick del botón Añadir
                btAddNew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Llamando al método addItem
                        //Pasando como parámetros el diálogo, el botón Añadir y el valor null para el item
                        addItem(dialog, btAddNew, null);
                    }
                });

                //Definiendo evento OnClick del botón Cancelar
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show(); //Mostrando el diálogo
            }
        });
    }

    //Definiendo método para agregar y actualizar
    public void addItem(Dialog dialog, Button btAdd, MenuItem item){
        //Referenciando EditText del diálogo
        EditText editText_Name=(EditText) dialog.findViewById(R.id.etAsignatura);
        EditText editText_Des=(EditText) dialog.findViewById(R.id.etDescripcion);

        //Referenciando imagen
        ImageView imageAsig=(ImageView) dialog.findViewById(R.id.imagelv);

        //Verificando que los EditText sean llenados
        if ((editText_Name.getText().toString().contentEquals(""))||
                (editText_Des.getText().toString().contentEquals(""))){
            Toast.makeText(MainActivity.this,"Nombre y descripción es requerido",Toast.LENGTH_LONG).show();
        }
        else{
            String nAsignatura, nDesc;

            //Obteniendo valores de los EditText
            nAsignatura=editText_Name.getText().toString();
            nDesc=editText_Des.getText().toString();

            //Defiendo un objeto de asignatura
            Asignatura asignaturaObj= new Asignatura();

            //Asignando contenido de los EditText a los atributos
            //del objeto Asignatura
            asignaturaObj.setTitle(nAsignatura);
            asignaturaObj.setDescription(nDesc);

            //Definiendo acciones a realizar si el texto del botón es Añadir
            if (btAdd.getText().toString().contentEquals("Añadir")){
                //Insertando datos del objeto a la Base de Datos
                long resultadoInsert=db.asignaturaDao().insert(asignaturaObj);
                if (resultadoInsert>0){
                    //Actualizando los datos mostrados en RecyclerView
                    //para visualizar en tiempo real cuando el dato es insertado
                    listAsignaturas=db.asignaturaDao().getAllAsignatura();

                    //Llenando adaptador con la lista de asignaturas obtenidas de la Base de Datos
                    myAdapter=new MyAdapter(MainActivity.this, listAsignaturas);

                    //Estableciendo adaptador al RecyclerView
                    mRecyclerView.setAdapter(myAdapter);
                    Toast.makeText(MainActivity.this,"Asignatura agregada",Toast.LENGTH_LONG).show();

                    //Limpiando EditText
                    editText_Name.setText("");
                    editText_Des.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Error al agregar asignatura", Toast.LENGTH_LONG).show();
                }
            }

            //Definiendo acciones a realizar si el texto del botón es Actualizar
            else{

                //Asignando id del item seleccionado al objeto
                asignaturaObj.setId(listAsignaturas.get(item.getOrder()).getId());

                //Insertando datos del objeto a la Base de Datos
                long resultadoUpdate=db.asignaturaDao().updateEntidad(asignaturaObj);
                if (resultadoUpdate>0){

                    //Actualizando los datos mostrados en RecyclerView
                    //para visualizar en tiempo real cuando el dato es actualizado
                    listAsignaturas=db.asignaturaDao().getAllAsignatura();

                    //Llenando adaptador con la lista de asignaturas obtenidas de la Base de Datos
                    myAdapter=new MyAdapter(MainActivity.this, listAsignaturas);

                    //Estableciendo adaptador al RecyclerView
                    mRecyclerView.setAdapter(myAdapter);
                    Toast.makeText(MainActivity.this,"Asignatura actualizada",Toast.LENGTH_LONG).show();

                    editText_Name.setText("");
                    editText_Des.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Error al actualizar", Toast.LENGTH_LONG).show();
                }
            }

            dialog.cancel();//Cerrando el diálogo
        }
    }
}
