package com.example.lab6_temasunete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {


    EditText perioada;
    EditText volum;
    EditText durata;

    String seteazaPerioada;
    String seteazaVolum;
    String seteazaDurata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view){

        perioada = findViewById(R.id.perioada);
        volum = findViewById(R.id.volum);
        durata = findViewById(R.id.durata);

        seteazaPerioada = perioada.getText().toString().trim();
        seteazaVolum =  volum.getText().toString().trim();
        seteazaDurata = durata.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), MyService.class);
        intent.putExtra("volum", seteazaVolum );
        intent.putExtra("perioada", seteazaPerioada);
        intent.putExtra("durata", seteazaDurata);

        this.startService(intent);

    }

}