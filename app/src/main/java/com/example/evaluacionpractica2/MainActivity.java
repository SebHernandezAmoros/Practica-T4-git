package com.example.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.evaluacionpractica2.Adapter.AnimeAdapter;
import com.example.evaluacionpractica2.Dao.AnimeDao;
import com.example.evaluacionpractica2.Entities.Anime;
import com.example.evaluacionpractica2.Service.AnimeService;
import com.example.evaluacionpractica2.database.AppDataBase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Anime> animes = new ArrayList<>();
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=AppDataBase.getDataBase(getApplicationContext());
        AnimeDao animeDAO = db.animeDAO();

        //Obtencion de lista y envio al adapter
        animes = animeDAO.getAll();

        AnimeAdapter adapter = new AnimeAdapter(animes);
        //Obtencion del recyclerview y envio del adapter
        RecyclerView rv = findViewById(R.id.rvLista);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);


    }
}