package com.example.allchat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText userNameText = findViewById(R.id.userNamePlainTextReg);
        EditText passwordText = findViewById(R.id.passwordPlainTextReg);
        Button registerButton = findViewById(R.id.registerButtonReg);
    }
}
