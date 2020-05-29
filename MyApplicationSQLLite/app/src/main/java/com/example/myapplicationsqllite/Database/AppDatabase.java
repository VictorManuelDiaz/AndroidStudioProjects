package com.example.myapplicationsqllite.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplicationsqllite.Entities.Asignatura;
import com.example.myapplicationsqllite.Interfaces.AsignaturaDao;

@Database(entities = {Asignatura.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract AsignaturaDao asignaturaDao();

    public static AppDatabase sInstance;
}
