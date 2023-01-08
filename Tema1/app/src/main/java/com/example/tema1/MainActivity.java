package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nume, prenume, email;
    Button send;
    TextView one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nume = findViewById(R.id.editTextTextPersonName);
        prenume = findViewById(R.id.editTextTextPersonName2);
        email = findViewById(R.id.editTextTextPersonName3);
        one = findViewById(R.id.textView);
        two = findViewById(R.id.textView2);
        three = findViewById(R.id.textView3);

        send = findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                one.setText(nume.getText());
                two.setText(prenume.getText());
                three.setText(email.getText());
            }
        });
    }
}