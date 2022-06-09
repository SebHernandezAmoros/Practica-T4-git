package com.example.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.evaluacionpractica2.Adapter.AnimeAdapter;
import com.example.evaluacionpractica2.Dao.AnimeDao;
import com.example.evaluacionpractica2.Entities.Anime;
import com.example.evaluacionpractica2.Factories.RetrofitFactory;
import com.example.evaluacionpractica2.Service.AnimeService;
import com.example.evaluacionpractica2.database.AppDataBase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuActivity extends AppCompatActivity {
    AppDataBase db;
    List<Anime> animes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnCrear = findViewById(R.id.btnCrear);
        Button btnSincronizar = findViewById(R.id.btnSincronizar);
        Button btnMostrar = findViewById(R.id.btnMostrar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofit = RetrofitFactory.build();
                AnimeService service = retrofit.create(AnimeService.class);
                Call<List<Anime>> call= service.GetAnimes();
                call.enqueue(new Callback<List<Anime>>() {
                    @Override
                    public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                        if(!response.isSuccessful()){
                            Log.i("APP_VJ20221","Error de aplicacion");
                        }else{
                            Log.i("APP_VJ20221","Respuesta correcta");
                            Log.i("APP_VJ20221",new Gson().toJson(response.body()));

                            //Obtencion de lista y envio al adapter
                            animes = response.body();
                            registrarAnimes(animes);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Anime>> call, Throwable t) {
                        Log.i("APP_VJ20221","No pudo conectar con el servicio");
                    }
                });


            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void registrarAnimes(List<Anime> animeList){
        db=AppDataBase.getDataBase(getApplicationContext());
        AnimeDao animeDAO = db.animeDAO();
        for (Anime Ani:animeList){
            animeDAO.create(Ani);
        }

    }
}