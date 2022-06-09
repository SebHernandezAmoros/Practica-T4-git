package com.example.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.evaluacionpractica2.Entities.Anime;
import com.example.evaluacionpractica2.Factories.RetrofitFactory;
import com.example.evaluacionpractica2.Service.AnimeService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText edtDesciption = findViewById(R.id.edtDesciption);
        EditText etURL = findViewById(R.id.etURL);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build();
                AnimeService service =retrofit.create(AnimeService.class);
                Anime anime = new Anime();

                anime.name=String.valueOf(etNombre.getText());
                anime.description=String.valueOf(edtDesciption.getText());
                anime.img=String.valueOf(etURL.getText());

                Call<Anime> call = service.createAnime(anime);
                call.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        if (response.isSuccessful()){
                            Log.i("PRUEBA",new Gson().toJson(response.body()));

                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {
                        Log.i("PRUEBA","No se pudo conectar");
                    }
                });
            }
        });

    }
}