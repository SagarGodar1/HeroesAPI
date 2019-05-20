package com.example.heroesapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import heroessapi.HeroesAPI;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class MainActivity extends AppCompatActivity {
    private EditText etname, etDescription;
    private Button btnSave;
    private ImageView imgProfile;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDesc);


        btnSave = findViewById(R.id.btnSave);



        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    private void Save() {
        String name = etname.getText().toString();
        String desc = etDescription.getText().toString();

        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("desc",desc);


        Heroes heroes = new Heroes(name,desc);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroesAPI heroessAPI = retrofit.create(HeroesAPI.class);



        Call<Void> heroesCall = heroessAPI.addHero(heroes);
        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}
