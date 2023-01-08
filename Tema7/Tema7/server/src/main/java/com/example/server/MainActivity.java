package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Chat";
    private ServerThread singleServerThread;
    private TextView tvsStatus;
    private String serverIp = "";
    private int serverPORT = 1234;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvsStatus = findViewById(R.id.status);
        message = findViewById(R.id.serverMessage);
    }

    public void sendMessage(View view){
        singleServerThread = new ServerThread();
        singleServerThread.startServer();
    }

    class ServerThread extends Thread implements Runnable{
        private boolean serverRunning;
        private ServerSocket serverSocket;

        public void startServer(){
            serverRunning = true;
            start();
        }

        @Override
        public void run(){
            try {
                serverSocket = new ServerSocket(serverPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (serverRunning){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

                PrintWriter outputServer = null;
                try {
                    outputServer = new PrintWriter(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputServer.write(String.valueOf(message.getText()));
                outputServer.flush();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getMessage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket_obj = new Socket(serverIp, serverPORT);
                    BufferedReader input = Utilities.getReader(socket_obj);
                    String txtFromServer = input.readLine();
                    Log.i(TAG, "server received: "+txtFromServer);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvsStatus.setText(txtFromServer);
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