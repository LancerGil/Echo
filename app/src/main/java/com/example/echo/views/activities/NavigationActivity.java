package com.example.echo.views.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.views.fragments.FragBoard;
import com.example.echo.views.fragments.FragHome;

public class NavigationActivity extends AppCompatActivity implements FragBoard.OnFragmentInteractionListener,FragHome.OnFragmentInteractionListener {
    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    //TODO: LQ -> 添加声明变量：用户个人页面的fragment
    private Fragment fragHome,fragBoard;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Fragment fragHome = new FragHome();
        transaction.replace(R.id.frag_container,fragHome);
        transaction.commit();

        initNavigation();
    }

    private void initNavigation() {
        mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction = fragmentManager.beginTransaction();
                        Fragment fragHome = new FragHome();
                        transaction.replace(R.id.frag_container,fragHome);
                        transaction.commit();
                        return true;
                    case R.id.navigation_leaderboard:
                        transaction = fragmentManager.beginTransaction();
                        Fragment fragBoard = new FragBoard();
                        transaction.replace(R.id.frag_container,fragBoard);
                        transaction.commit();
                        return true;
                    case R.id.navigation_user:
                        //TODO: LQ -> 点击底部导航栏的"用户/我"跳转到用户个人的frament
//                        transaction = fragmentManager.beginTransaction();
//                        Fragment fragHome = new FragHome();
//                        transaction.replace(R.id.frag_container,fragHome);
                        return true;
                }
                return false;
            }
        };

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
