package com.example.echo.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.echo.R;

public class UserInfoActivity extends AppCompatActivity {
    private Button infoBtn = null;
    private TextView username = null;
    private TextView gender = null;
    private TextView phone = null;
    private Button editBtn = null;
    private EditText newUsername = null;
    private RadioGroup genderBox = null;
    private RadioButton male = null;
    private RadioButton female = null;
    private EditText newPhone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        infoBtn = (Button)findViewById(R.id.infoBtn);
        username = (TextView)findViewById(R.id.displayName);
        gender = (TextView)findViewById(R.id.displayGender);
        phone = (TextView)findViewById(R.id.displayPhone);

        editBtn = (Button)findViewById(R.id.editBtn);
        newUsername = (EditText)findViewById(R.id.editName);
        genderBox = (RadioGroup)findViewById(R.id.editGender);
        male = (RadioButton)findViewById(R.id.genderMale);
        female = (RadioButton)findViewById(R.id.genderFemale);
        newPhone = (EditText)findViewById(R.id.editPhone);

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUsername.setText(username.getText().toString());
                newPhone.setText(phone.getText().toString());
                if (gender.getText().toString() == "男"){
                    male.setChecked(true);
                } else {
                    female.setChecked(true);
                }
                username.setVisibility(View.GONE);
                gender.setVisibility(View.GONE);
                phone.setVisibility(View.GONE);
                infoBtn.setVisibility(View.GONE);
                newUsername.setVisibility(View.VISIBLE);
                genderBox.setVisibility(View.VISIBLE);
                newPhone.setVisibility(View.VISIBLE);
                editBtn.setVisibility(View.VISIBLE);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText(newUsername.getText().toString());
                if (male.isChecked()){
                    gender.setText("男");
                } else {
                    gender.setText("女");
                }
                phone.setText(newPhone.getText().toString());
                username.setVisibility(View.VISIBLE);
                gender.setVisibility(View.VISIBLE);
                phone.setVisibility(View.VISIBLE);
                infoBtn.setVisibility(View.VISIBLE);
                newUsername.setVisibility(View.GONE);
                genderBox.setVisibility(View.GONE);
                newPhone.setVisibility(View.GONE);
                editBtn.setVisibility(View.GONE);
            }
        });
    }
}
