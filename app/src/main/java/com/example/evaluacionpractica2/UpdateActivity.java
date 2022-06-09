package com.example.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.evaluacionpractica2.Entities.Anime;
import com.example.evaluacionpractica2.Factories.RetrofitFactory;
import com.example.evaluacionpractica2.Service.AnimeService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        String animeJson = getIntent().getStringExtra("ANIME");
        Anime animeAdaptor = new Gson().fromJson(animeJson, Anime.class);

        EditText etNombreEdit = findViewById(R.id.etNombreEdit);
        EditText edtDesciptionEdit = findViewById(R.id.edtDesciptionEdit);
        ImageView imagen = findViewById(R.id.imageView);

        Button btnActualizar = findViewById(R.id.btnActualizar);

        etNombreEdit.setText(animeAdaptor.name);
        edtDesciptionEdit.setText(animeAdaptor.description);
        Picasso.get()
                .load(animeAdaptor.img)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(imagen);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build();
                AnimeService service =retrofit.create(AnimeService.class);
                Anime anime = new Anime();

                anime.name=String.valueOf(etNombreEdit.getText());
                anime.description=String.valueOf(edtDesciptionEdit.getText());
                anime.img= animeAdaptor.img;
            }
        });
    }
}