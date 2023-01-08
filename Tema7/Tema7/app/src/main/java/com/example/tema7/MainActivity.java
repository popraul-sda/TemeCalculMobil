package com.example.tema7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private TextView tvcReceivedData;
    private Button getMessage;
    ServerThread singleServerThread;
    String etcIp = "10.0.2.16";
    int etcPORT = 1234;
    EditText message;
    private static final String TAG = "Chat";
    Socket socket_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcReceivedData = findViewById(R.id.data);
        getMessage = findViewById(R.id.button);
        message = findViewById(R.id.clientMessage);
    }

    public void sendMessage(View view){
        singleServerThread = new ServerThread();
    }

    class ServerThread extends Thread implements Runnable{

        @Override
        public void run(){
            try {
                socket_obj = new Socket(etcIp, etcPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter outputServer = null;
                try {
                    outputServer = new PrintWriter(socket_obj.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputServer.write(String.valueOf(message.getText()));
                Log.i(TAG, "client send: " +String.valueOf(message.getText()));
                outputServer.flush();
                try {
                    socket_obj.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void getMessage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket_obj = new Socket(etcIp, etcPORT);
                    BufferedReader input = Utilities.getReader(socket_obj);
                    String txtFromServer = input.readLine();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvcReceivedData.setText(txtFromServer);
                        }
                    });
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
