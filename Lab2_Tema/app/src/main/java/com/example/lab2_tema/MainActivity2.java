package com.example.lab2_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

  int fNumber;
  int sNumber;
  int sum;
  int dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            //afisare
            String nb1 = bundle.getString("number1");
            String nb2 = bundle.getString("number2");
            TextView ViewNb1 = findViewById(R.id.nb1);
            TextView ViewNb2 = findViewById(R.id.nb2);
            TextView ViewNb = findViewById(R.id.nb);
            TextView ViewNb3 = findViewById(R.id.nb3);

            ViewNb1.setText(nb1);
            ViewNb2.setText(nb2);
            ViewNb.setText(nb1);
            ViewNb3.setText(nb2);

            fNumber = Integer.parseInt(ViewNb1.getText().toString()); //primul numar
            sNumber = Integer.parseInt(ViewNb2.getText().toString()); //al doilea numar

           sum = fNumber + sNumber; //suma
           dif = fNumber - sNumber; // diferenta

        }
    }
    public void onClickBtn2(View view) {

        String stringSum = " The sum is: " + sum;
        Intent intent = new Intent();
        intent.putExtra("name_key", stringSum);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickBtn3(View view) {


        String stringDifference = " The difference is: " + dif;
        Intent intent = new Intent();
        intent.putExtra("name_key", stringDifference);
        setResult(RESULT_OK, intent);
        finish();
    }
}
