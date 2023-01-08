package com.example.temafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = findViewById(R.id.textView);
        String string =getIntent().getStringExtra("key");
        text.setText(string);
    }

    public void Schimba(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}