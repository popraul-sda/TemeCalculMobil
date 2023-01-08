package com.example.lab2_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    //declarari
    public EditText number1;
    public EditText number2;
    public Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        button =  findViewById(R.id.rezultat);
    }

    public void onClickBtn1(View view){

        String nb1 = number1.getText().toString().trim();
        String nb2 = number2.getText().toString().trim();
        Bundle bundle = new Bundle();
        bundle.putString("number1", nb1);
        bundle.putString("number2", nb2);

        Intent intent_1 = new Intent(this, MainActivity2.class);
        intent_1.putExtras(bundle);
        startActivityForResult(intent_1, SECOND_ACTIVITY_REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //verificam daca suntem in a doua activitate (Result ok)
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
         if (resultCode == RESULT_OK) {

                //obtinem "stringul" din intent
                String stringGet = data.getStringExtra("name_key");

                //punem stringul in textView-ul nostru
                TextView rezult =findViewById(R.id.afisareRezultat);
                rezult.setText(stringGet);
            }
        }
    }


}
