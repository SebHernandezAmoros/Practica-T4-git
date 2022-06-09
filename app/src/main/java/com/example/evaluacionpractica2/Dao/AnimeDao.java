package com.example.evaluacionpractica2.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.evaluacionpractica2.Entities.Anime;

import java.util.List;

@Dao
public interface AnimeDao {
    //Retornar todos los datos de esa tabla
    @Query("SELECT * FROM animes")
    List<Anime> getAll();
    //Retornar uno por ID
    @Query("SELECT *FROM animes WHERE id = :idContact")
    Anime find (int idContact);
    //Creary agregar dato a bd
    @Insert
    void create(Anime contact);
}
