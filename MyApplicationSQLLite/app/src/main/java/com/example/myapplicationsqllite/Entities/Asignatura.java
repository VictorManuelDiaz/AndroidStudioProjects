package com.example.myapplicationsqllite.Entities;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity (tableName = Asignatura.TABLE_NAME)
public class Asignatura {

    //Definiendo el nombre de la tabla
    public static final String TABLE_NAME="asignatura";

    public static final String COLUMN_NAME="name";

    //Definiendo el nombre de la columna ID
    public static final String COLUMN_ID= BaseColumns._ID;

    //Estableciendo llave primaria autogenerada
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)//Definiendo índice que no se repita
    public long id;

    //Información de la columna
    @ColumnInfo(name = "titulo")
    private String title;

    @ColumnInfo(name = "descripcion")
    private String description;

    public Asignatura(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Asignatura() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
