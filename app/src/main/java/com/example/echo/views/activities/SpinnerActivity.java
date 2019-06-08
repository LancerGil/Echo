package com.example.echo.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.echo.R;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //获取页面中的spinner组件
        spinner = (Spinner) findViewById(R.id.selectSex);

        //设置选中事件监听器
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String content = parent.getItemAtPosition(position).toString();
        switch (parent.getId()){
            case R.id.selectSex:
                Toast.makeText(SpinnerActivity.this,"选择的性别是："+content, Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
