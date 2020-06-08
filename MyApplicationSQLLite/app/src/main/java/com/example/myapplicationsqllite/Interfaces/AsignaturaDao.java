package com.example.myapplicationsqllite.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationsqllite.Entities.Asignatura;

import java.util.List;

@Dao//Permitiendo acceso a datos de la entidad
public interface AsignaturaDao {

    //Definiendo métodos para la Base de Datos

    //Método para seleccionar la cantidad de registros
    @Query("SELECT COUNT(*) FROM "+ Asignatura.TABLE_NAME)
    //Método para la consulta definida
    int count();

    //Método para seleccionar todos los registros
    @Query("SELECT * FROM "+Asignatura.TABLE_NAME)
    //Devolviendo una lista con los registros de la Base de Datos
    List<Asignatura> getAllAsignatura();

    //Método insertar
    @Insert
    long insert(Asignatura asignatura);

    //Método eliminar utilizando id
    @Query("DELETE FROM "+Asignatura.TABLE_NAME+" WHERE "+Asignatura.COLUMN_ID+"= :id")
    int deleteById(long id);

    //Método actualizar que requiere un objeto como parámetro
    @Update
    int updateEntidad(Asignatura obj);
}
