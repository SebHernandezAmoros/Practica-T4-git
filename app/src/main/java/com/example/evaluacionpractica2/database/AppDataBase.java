package com.example.evaluacionpractica2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.evaluacionpractica2.Dao.AnimeDao;
import com.example.evaluacionpractica2.Entities.Anime;

@Database(entities = {Anime.class }, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract AnimeDao animeDAO();
    //En caso de agregar mas tablas aqui su DAO y arriba la vlase declarada(linea 9)

    public static AppDataBase getDataBase(Context context){
        return Room.databaseBuilder(context, AppDataBase.class, "com.example.evaluacionpractica2.database.anime_db")
                //Permiso para usar el hilo principal
                .allowMainThreadQueries()
                .build();
    }
}