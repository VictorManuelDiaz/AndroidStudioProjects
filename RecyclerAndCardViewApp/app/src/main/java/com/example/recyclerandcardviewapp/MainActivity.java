package com.example.recyclerandcardviewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Definiendo objeto ArrayList utilizando el modelo creado
    ArrayList<Model> models=new ArrayList<>();
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
        Model m=new Model();

        //Inicializando ArrayList con tres objetos
        m.setTitle("Facultativa II");
        m.setDescription("Descripción de Facultativa II");
        m.setImg(R.drawable.app);

        //Estableciendo valores para los atributos professor, day, hour, date
        m.setProfessor("Sayra Urbina");
        m.setDay("Lunes");
        m.setHour(Time.valueOf("08:30:00"));
        m.setDate(2020,05-1,24);//Restando uno porque el calendario inicia en mes 00
        models.add(m);

        m=new Model();
        m.setTitle("Investigación");
        m.setDescription("Descripción de Investigación");
        m.setImg(R.drawable.files);

        //Estableciendo valores para los atributos professor, day, hour, date
        m.setProfessor("Jazcar Bravo");
        m.setDay("Martes");
        m.setHour(Time.valueOf("08:30:00"));
        m.setDate(2020,05-1,15);//Restando uno porque el calendario inicia en mes 00
        models.add(m);

        m=new Model();
        m.setTitle("Planificación TIC");
        m.setDescription("Descripción de Planificación");
        m.setImg(R.drawable.pc);

        //Estableciendo valores para los atributos professor, day, hour, date
        m.setProfessor("Jazcar Bravo");
        m.setDay("Lunes");
        m.setHour(Time.valueOf("08:30:00"));
        m.setDate(2020,05-1,23);//Restando uno porque el calendario inicia en mes 00
        models.add(m);

        return models;
    }

    //Agregando método para creación del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Agregando método para programar las acciones de los items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.close: //Definiendo acciones para item close
                //Agregando método para cerrar aplicación
                finish();
                return true;
            case R.id.addNew: //Definiendo acciones para item addNew
                //Declarando diálogo de tipo final para ser reconocido por el evento onClick
                final Dialog dlg= new Dialog(this);

                //Asignando xml al diálogo
                dlg.setContentView(R.layout.add_new);
                dlg.setTitle("Agregar asignatura");

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
                        //Referenciando EditText de add_new.xml
                        EditText editText_Asignatura=(EditText) dlg.findViewById(R.id.etAsignatura);
                        EditText editText_Descripcion=(EditText) dlg.findViewById(R.id.etDescripcion);
                        EditText editText_Docente=(EditText) dlg.findViewById(R.id.etDocente);
                        EditText editText_Dia=(EditText) dlg.findViewById(R.id.etDia);
                        EditText editText_Hora=(EditText) dlg.findViewById(R.id.etHora);
                        EditText editText_Fecha=(EditText) dlg.findViewById(R.id.etFecha);

                        //Verificando que todos los EditText se encuentren llenados
                        if (editText_Asignatura.getText().toString().contentEquals("") ||
                                editText_Descripcion.getText().toString().contentEquals("") ||
                                editText_Docente.getText().toString().contentEquals("") ||
                                editText_Dia.getText().toString().contentEquals("") ||
                                editText_Hora.getText().toString().contentEquals("") ||
                                editText_Fecha.getText().toString().contentEquals("")){
                            Toast.makeText(MainActivity.this,"Campos obligatorios", Toast.LENGTH_LONG).show();
                        }
                        else{
                            //Obteniendo la posición para agregar nuevo item al final del ArrayList
                            int position = models.size();
                            //Creando objeto de la clase Model
                            Model m=new Model();

                            //Controlando excepciones al asignar hora y fecha
                            try {
                                //Asignando los valores al objeto
                                m.setTitle(editText_Asignatura.getText().toString());
                                m.setDescription(editText_Descripcion.getText().toString());
                                m.setImg(R.drawable.outbox);
                                m.setProfessor(editText_Docente.getText().toString());
                                m.setDay(editText_Dia.getText().toString());

                                //Asignando valor al atributo hour a través de la obtención del valor tipo Time por medio de un String
                                m.setHour(Time.valueOf(editText_Hora.getText().toString()));

                                //Obteniendo String de fecha
                                String valueFecha=editText_Fecha.getText().toString();

                                //Dividiendo String para obtener las tres partes de la fecha AAAA-MM-DD
                                String [] parts=valueFecha.split("-", 3);

                                //Convertiendo los String de fecha a int
                                int year=Integer.valueOf(parts[2]), month=Integer.valueOf(parts[1]), day=Integer.valueOf(parts[0]);

                                //Asignando los valores enteros de fecha al metodo set del atributo date
                                m.setDate(year,(month-1),day); //Restando uno porque el calendario inicia en mes 00

                                //Llamando al método add para añadir el objeto creado en la posición final
                                models.add(position, m);

                                //Notificando al Adaptador que un nuevo item será insertado en la posición final
                                myAdapter.notifyItemInserted(position);

                                //Mandando la posición al Adaptador y actualizando el tamaño de la lista
                                myAdapter.notifyItemRangeChanged(position, models.size());
                            }
                            catch (Exception e){
                                Toast.makeText(MainActivity.this,"Error al registrar", Toast.LENGTH_LONG).show();
                            }
                            //Cerrando diálogo
                            dlg.cancel();

                        }
                    }
                });

                //Definiendo evento OnClick del botón Cancelar
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Asignando evento click para el botón Cancelar
                        //Cerrando diálogo
                        dlg.cancel();
                    }
                });
                dlg.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
