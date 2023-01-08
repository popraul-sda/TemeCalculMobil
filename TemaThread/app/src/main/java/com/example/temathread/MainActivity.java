package com.example.temathread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    EditText interval, number;
    TextView text;
    private int numberThreadOne, numberThreadTwo;
    private final Handler mainHandler = new Handler();
    private int myNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.button);
        interval = findViewById(R.id.editTextTextPersonName);
        number = findViewById(R.id.editTextTextPersonName2);
        text = findViewById(R.id.textView);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String auxiliarX = String.valueOf(interval.getText());
                String auxiliarY = String.valueOf(number.getText());
                int x;
                try {
                    x = Integer.parseInt(auxiliarX);
                    myNumber = Integer.parseInt(auxiliarY);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Introduceti un numar in ambele casute text", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (x < 2){
                    Toast.makeText(MainActivity.this, "Introduceti un numar mai mare decat 1", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (myNumber > x){
                    Toast.makeText(MainActivity.this, "Numarul ales nu poate sa fie mai mare decat extrema intervalului", Toast.LENGTH_SHORT).show();
                    return;
                }

                start.setEnabled(false);
                generateNumber runnable = new generateNumber(Integer.parseInt(auxiliarX),true);
                new Thread(runnable).start();

                generateNumber runnable2 = new generateNumber(Integer.parseInt(auxiliarX),false);
                new Thread(runnable2).start();

            }
        });
    }

    class generateNumber implements Runnable{

        private String status = "Rezultat";
        int number;
        boolean which;

        public generateNumber(int number, boolean which) {
            this.number = number;
            this.which = which;
        }

        @Override
        public void run() {
            int i = 0;
            while(i < this.number){
                if(this.which == true)
                {
                    Random rand = new Random();
                    numberThreadOne = rand.nextInt(number);
                    i++;
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Random rand = new Random();
                    numberThreadTwo = rand.nextInt(number);
                    i++;
                    if(numberThreadOne == numberThreadTwo) status = "Ghicit de catre Calculator";
                    if(numberThreadTwo == myNumber) status = "Ai castigat";
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(status);
                        }
                    });
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {start.setEnabled(true);}
            });
        }
    }
}
