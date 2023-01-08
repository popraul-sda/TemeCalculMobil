package com.example.tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd, btnDel;
    EditText text;

    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        text = findViewById(R.id.editTextTextPersonName);

        names.add("Raul");
        names.add("Paul");


        myAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(myAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringVal = text.getText().toString();
                names.add(stringVal);
                myAdapter.notifyDataSetChanged();

                text.setText("");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                names.remove(names.size() - 1);
                myAdapter.notifyDataSetChanged();
            }
        });
    }
}