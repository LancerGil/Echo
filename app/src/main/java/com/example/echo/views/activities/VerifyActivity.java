package com.example.echo.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.echo.R;

public class VerifyActivity extends AppCompatActivity {
    private Button verifty = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        verifty = findViewById(R.id.verifyBtn);
        verifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toForgetAty = new Intent(VerifyActivity.this, ForgetPassordActivity.class);
                startActivity(toForgetAty);
            }
        });
    }
}
