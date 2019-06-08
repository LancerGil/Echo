package com.example.echo.views.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.entities.AppContentInList;
import com.example.echo.entities.FeedbackContent;
import com.example.echo.views.fragments.FragBoard;
import com.example.echo.views.fragments.FragFeedback;
import com.example.echo.views.fragments.FragHome;
import com.example.echo.views.fragments.FragMe;

public class FeedbackActivity extends AppCompatActivity implements FragFeedback.OnListFragmentInteractionListener {
    private TextView appFedTab;
    private TextView myFedTab;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    //TODO: LQ -> 添加声明变量
    private Fragment fragFeedback, MyFeedback;
    private int unSelectNaviId;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 设置状态栏:透明背景 + 黑色字体
         */
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        /**
         * 装载初始的fragment页面
         */
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Fragment fragFeedback = new FragFeedback();
        transaction.replace(R.id.fedPager, fragFeedback);
        transaction.commit();

        /**
         * 初始化底部导航栏
         */
        initNavigation();
    }

    private void initNavigation(){
        appFedTab = findViewById(R.id.app_feedback);
        myFedTab = findViewById(R.id.my_feedback);
        transaction = fragmentManager.beginTransaction();
        appFedTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFrag = new FragFeedback();
                transaction.replace(R.id.fedPager, newFrag);
                transaction.commit();
            }
        });
        myFedTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFrag = new FragFeedback();
                transaction.replace(R.id.fedPager, newFrag);
                transaction.commit();
            }
        });
    }

//    private void initNavigation() {
//        /**
//         * 设置底部导航栏选项监听
//         */
//        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fedPager);
//                if (unSelectNaviId == item.getItemId()) {
//                    switch (item.getItemId()) {
//                        case R.id.navigation_home:
//                            ((FragHome) fragment).scrollViewTo(0, 0);
//                            break;
//                        case R.id.navigation_leaderboard:
//                            ((FragBoard) fragment).scrollListToTop();
//                            break;
//                        case R.id.navigation_user:
//                            //TODO: LQ -> 第二次点击同一个的tab.刷新用户页面.
//                            ((FragMe) fragment).scrollViewTo(0, 0);
//                            break;
//                    }
//                } else {
//                    transaction = fragmentManager.beginTransaction();
//                    Fragment newFrag = new FragHome();
//                    switch (item.getItemId()) {
//                        case R.id.navigation_leaderboard:
//                            newFrag = new FragBoard();
//                            break;
//                        case R.id.navigation_user:
//                            //TODO: LQ -> 创建用户个人页面的fragment.
//                            newFrag = new FragMe();
//                            break;
//                    }
//                    transaction.replace(R.id.fedPager, newFrag);
//                    transaction.commit();
//                    unSelectNaviId = item.getItemId();
//                }
//                return true;
//            }
//        };
//
//        /**
//         * 初始化底部导航栏,并设置监听
//         */
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }

    @Override
    public void onListFragmentInteraction(FeedbackContent.DummyFeedbackItem item) {

    }
}
