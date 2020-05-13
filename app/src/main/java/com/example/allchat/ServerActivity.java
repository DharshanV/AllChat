package com.example.allchat;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        TextView clientUsername = findViewById(R.id.clientUserName);

        Intent intent = getIntent();
        String clientName = intent.getStringExtra("clientName");
        clientUsername.setText(clientName);
    }
}
