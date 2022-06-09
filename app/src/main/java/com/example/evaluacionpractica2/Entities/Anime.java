package com.example.evaluacionpractica2.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animes")
public class Anime {
    @PrimaryKey
    public int id;
    public String name;
    public String description;
    public String img;

    public Anime() {
    }

    public Anime(String name, String description, String img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public Anime(int id, String name, String description, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
    }
}
