package com.example.myapplicationsqllite.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationsqllite.Entities.Asignatura;

import java.util.List;

public interface AsignaturaDao {

    @Query("SELECT COUNT(*) FROM "+ Asignatura.TABLE_NAME)
    int count();

    @Query("SELECT * FROM "+Asignatura.TABLE_NAME)
    List<Asignatura> getAllAsignatura();

    @Insert
    long insert(Asignatura asignatura);

    @Query("DELETE FROM "+Asignatura.TABLE_NAME+" WHERE "+Asignatura.COLUMN_ID+"= :id")
    int deleteById(long id);

    @Update
    int updateEntidad(Asignatura obj);
}