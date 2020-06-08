package com.example.myapplicationsqllite.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplicationsqllite.Entities.Asignatura;
import com.example.myapplicationsqllite.Interfaces.AsignaturaDao;

@Database(entities = {Asignatura.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    //Permisos para listar, eliminar, actualizar e insertar
    //especificados en Dao
    public abstract AsignaturaDao asignaturaDao();

    //Definiendo variable para gestor de la Base de Datos
    public static AppDatabase sInstance;
}
