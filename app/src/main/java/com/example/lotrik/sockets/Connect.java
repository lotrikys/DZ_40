package com.example.lotrik.sockets;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by lotrik on 02.02.16.
 */
public class Connect extends AsyncTask<String, Void, String> {

    Socket socket;

    public InputStream is;
    public OutputStream os;

    InetAddress serverAddr;

    @Override
    protected String doInBackground(String... strings) {

        try {

            serverAddr = InetAddress.getByName("188.42.216.101");
            socket = new Socket(serverAddr, 10700);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            Log.d("tag1", "Connected");

        } catch (Exception e) {
//            Log.d("tag1", "Exception " + e);
        }

        return null;
    }
}
