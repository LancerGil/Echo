package com.example.echo.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.echo.R;

public class LoginActivity extends AppCompatActivity {
    private TextView register = null;
    private TextView forget = null;
    private Button loginBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent toRegisterAty = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(toRegisterAty);
            }
        });
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHomeAty = new Intent(LoginActivity.this,NavigationActivity.class);
                startActivity(toHomeAty);
            }
        });
        forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toVeriftyAty = new Intent(LoginActivity.this,VerifyActivity.class);
                startActivity(toVeriftyAty);
            }
        });


    }
}
