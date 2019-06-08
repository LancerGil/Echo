package com.example.echo.views.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.views.fragments.BoardAppFragment;
import com.example.echo.views.fragments.FragBoard;
import com.example.echo.views.fragments.FragHome;
import com.example.echo.views.fragments.FragMe;
import com.example.echo.entities.AppContentInList;

public class NavigationActivity extends AppCompatActivity implements FragBoard.OnFragmentInteractionListener, FragHome.OnFragmentInteractionListener, BoardAppFragment.OnListFragmentInteractionListener {
    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    //TODO: LQ -> 添加声明变量：用户个人页面的fragment
    private Fragment fragHome, fragBoard, fragMe;
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
        Fragment fragHome = new FragHome();
        transaction.replace(R.id.frag_container, fragHome);
        transaction.commit();

        /**
         * 初始化底部导航栏
         */
        initNavigation();
    }

    private void initNavigation() {
        /**
         * 设置底部导航栏选项监听
         */
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_container);
                if (unSelectNaviId == item.getItemId()) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            ((FragHome) fragment).scrollViewTo(0, 0);
                            break;
                        case R.id.navigation_leaderboard:
                            ((FragBoard) fragment).scrollListToTop();
                            break;
                        case R.id.navigation_user:
                            //TODO: LQ -> 第二次点击同一个的tab.刷新用户页面.
                            ((FragMe) fragment).scrollViewTo(0, 0);
                            break;
                    }
                } else {
                    transaction = fragmentManager.beginTransaction();
                    Fragment newFrag = new FragHome();
                    switch (item.getItemId()) {
                        case R.id.navigation_leaderboard:
                            newFrag = new FragBoard();
                            break;
                        case R.id.navigation_user:
                            //TODO: LQ -> 创建用户个人页面的fragment.
                            newFrag = new FragMe();
                            break;
                    }
                    transaction.replace(R.id.frag_container, newFrag);
                    transaction.commit();
                    unSelectNaviId = item.getItemId();
                }
                return true;
            }
        };

        /**
         * 初始化底部导航栏,并设置监听
         */
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(AppContentInList.DummyAppInListItem item) {
        Intent toAppDetails = new Intent(NavigationActivity.this,AppDetailsActivity.class);
        toAppDetails.putExtra("appName",item.id);
        toAppDetails.putExtra("appContent",item.content);
        toAppDetails.putExtra("appDetails",item.details);
        startActivity(toAppDetails);
        overridePendingTransition(R.anim.ani_right_get_into, R.anim.ani_left_sign_out);
    }

}
