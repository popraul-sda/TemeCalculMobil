package com.example.temafragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    int i = 0;

    public void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStanga = findViewById(R.id.button1);
        btnStanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0) {
                    SecondPicture secondPicture = new SecondPicture();
                    switchFragment(secondPicture);
                    i++;
                }
                else if(i == 1)
                {
                    ThirdPicture thirdPicture = new ThirdPicture();
                    switchFragment(thirdPicture);
                    i++;
                }
                else if(i == 2){
                    FourthPicture fourthPicture = new FourthPicture();
                    switchFragment(fourthPicture);
                    i++;
                }
                else if(i == 3){
                    FirstPicture firstPicture = new FirstPicture();
                    switchFragment(firstPicture);
                    i = 0;
                }
            }
        });

        Button btnDreapta = findViewById(R.id.button2);
        btnDreapta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0) {
                    FourthPicture fourthPicture = new FourthPicture();
                    switchFragment(fourthPicture);
                    i = 3;
                }
                else if(i == 1)
                {
                    FirstPicture firstPicture = new FirstPicture();
                    switchFragment(firstPicture);
                    i--;
                }
                else if(i == 2){
                    SecondPicture secondPicture = new SecondPicture();
                    switchFragment(secondPicture);
                    i--;
                }
                else if(i == 3){
                    ThirdPicture thirdPicture = new ThirdPicture();
                    switchFragment(thirdPicture);
                    i--;
                }
            }
        });

        Button description = findViewById(R.id.button);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0){
                    schimba(view, "unu");
                }
                else if (i == 1){
                    schimba(view, "doi");
                }
                else if (i == 2){
                    schimba(view, "trei");
                }
                else if (i == 3){
                    schimba(view, "patru");
                }
            }
        });

    }

    public void schimba(View view, String value){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("key",value);
        startActivity(i);
    }
}