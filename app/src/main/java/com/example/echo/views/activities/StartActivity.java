package com.example.echo.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.echo.R;
import com.example.echo.general.AppConfig;

public class StartActivity extends AppCompatActivity {
    private static final String TAG ="StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO：实现登录功能后删除这一段。
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toNaviAty = new Intent(StartActivity.this,NavigationActivity.class);
                startActivity(toNaviAty);
                finish();
            }
        },3000); // 延时3s跳转到主导航界面

        //TODO：实现登录功能后恢复这一段。
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLoginStatus();
            }
        },3000); // 延时3s*/
    }


    private void checkLoginStatus() {
        if (AppConfig.checkAccountCachedStatus(StartActivity.this)) {
            Intent toNaviAty = new Intent(StartActivity.this,NavigationActivity.class);
            startActivity(toNaviAty);
            finish();
        }else {
            //TODO: 实现了登录功能后：检查到未登录，跳转到登录界面。
            //Intent toLoginAty = new Intent(LoginActivity.this,NavigationActivity.class);
            //startActivity(toLoginAty);
            //finish();
        }
    }
}
