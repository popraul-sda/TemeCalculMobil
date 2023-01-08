package com.example.tema8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String jsonURL = "https://www.floatrates.com/daily/ron.json";
    private static final String TAG = "MainActivity";
    private TextView sumaFinala;
    double rata;
    private EditText sumaInitiala;
    public Button btnUsd, btnEur, btnGbp, btnMdl, btnHuf, btnCzk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumaFinala = findViewById(R.id.sumaFinala);
        sumaInitiala = findViewById(R.id.sumaInitiala);

        btnUsd = findViewById(R.id.btnUsd);
        btnUsd.setOnClickListener(this);
        btnEur = findViewById(R.id.btnEur);
        btnEur.setOnClickListener(this);
        btnGbp = findViewById(R.id.btnGbp);
        btnGbp.setOnClickListener(this);
        btnMdl = findViewById(R.id.btnMdl);
        btnMdl.setOnClickListener(this);
        btnHuf = findViewById(R.id.btnHuf);
        btnHuf.setOnClickListener(this);
        btnCzk = findViewById(R.id.btnCzk);
        btnCzk.setOnClickListener(this);
    }

    public void Convert(View view, String currency){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(jsonURL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String recData = response.body().string();
                JSONObject emp = null;
                try {
                    emp = (new JSONObject(recData)).getJSONObject(currency);
                    Log.i(TAG, "json: " + emp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    rata = emp.getDouble("inverseRate");
                    Log.i(TAG, "rata: " + rata);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                double sumFinal = Double.parseDouble(String.valueOf(sumaInitiala.getText())) * rata;
                Log.i(TAG, "sumFinal: " + sumFinal);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sumaFinala.setText(String.valueOf(sumFinal));
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUsd:
                Convert(view, "usd");
                break;
            case R.id.btnEur:
                Convert(view, "eur");
                break;
            case R.id.btnGbp:
                Convert(view, "gbp");
                break;
            case R.id.btnHuf:
                Convert(view, "huf");
                break;
            case R.id.btnCzk:
                Convert(view, "czk");
                break;
            case R.id.btnMdl:
                Convert(view, "mdl");
                break;
        }
    }
}