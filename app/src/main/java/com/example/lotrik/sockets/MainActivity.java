package com.example.lotrik.sockets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Runnable{

    TextView textView;
    EditText editText;
    Button button;

    Connect connect;

    byte buf[] = new byte[64];
    byte mesbuf[] = new byte[64];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        connect = new Connect();
        connect.execute();

        Thread thread = new Thread(this);
        thread.start();

    }

    public void send() {

        try {

            String name = "Alex: ";
            String message = name + editText.getText().toString();
            mesbuf = message.getBytes();

            for (int i = 0; i < buf.length; i++){

                buf[i] = 0;

            }

            for (int i = 0; i < mesbuf.length; i++) {

                buf[i] = mesbuf[i];

            }

            connect.os.write(buf, 0, buf.length);
            connect.os.flush();

        } catch (Exception e) {

        }

    }

    public void recieveinfo() {

        try {

            connect.is.read(buf);

            String answer = new String(buf, "UTF-8");

            textView.setText(answer);

        } catch (Exception e) {

        }

    }

    @Override
    public void run() {

        while (true) {

            recieveinfo();
        }
    }

}
