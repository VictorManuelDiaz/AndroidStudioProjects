package com.example.myapplicationsqllite.Entities;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Asignatura {
    public static final String TABLE_NAME="asignatura";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ID= BaseColumns._ID;


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

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
