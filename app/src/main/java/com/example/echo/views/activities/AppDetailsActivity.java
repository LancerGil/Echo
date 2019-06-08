package com.example.echo.views.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.adapters.AppCommentRVAdapter;
import com.example.echo.entities.CommentContent;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.zip.Inflater;

public class AppDetailsActivity extends AppCompatActivity {
    private final static String TAG = "AppDetailsActivity";

    //声明视图
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView ivAppLogo;
    ImageView ivAddComment;
    Button btnAppDetail;
    TextView tvAppDev;
    TextView tvAppRate;
    TextView tvAppAnnounce, tvInteractAnnounce;
    RecyclerView commentList;
    Toolbar toolbar;

    //声明数据
    private String title, appDev, appRate, appAnnounce;
    private Boolean isAnnounceFolded;

    //声明工具

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /**
         * 设置状态栏:透明背景 + 黑色字体
         */
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        //设置右滑退出
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeSensitivity(1.0f)
                .setSwipeRelateEnable(true)
                .setSwipeRelateOffset(300)
                .setSwipeEdgePercent(0.15f)
                .setClosePercent(0.5f);

        initView();
        getData();
        loadData();
        initInteract();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        ivAppLogo = findViewById(R.id.iv_app_logo);
        ivAddComment = findViewById(R.id.iv_add_comment);
        btnAppDetail = findViewById(R.id.btn_app_detail);
        tvAppDev = findViewById(R.id.tv_app_dev);
        tvAppRate = findViewById(R.id.tv_app_rate);
        tvAppAnnounce = findViewById(R.id.tv_app_announce);
        commentList = findViewById(R.id.rv_comment_list);
        tvInteractAnnounce = findViewById(R.id.tv_interact_announce);
        //设置recyclerView不滚动，从而恢复scrollview惯性滚动
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        commentList.setLayoutManager(layoutManager);
    }

    private void getData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("appContent");
        appDev = intent.getStringExtra("appDetails");
        Log.d(TAG, "getData: " + appDev + "-" + title);
    }

    private void loadData() {
        isAnnounceFolded = true;
        collapsingToolbarLayout.setTitle(title);
        tvAppDev.setText(appDev);
        commentList.setLayoutManager(new LinearLayoutManager(this));
        commentList.setAdapter(new AppCommentRVAdapter(CommentContent.ITEMS));
    }

    private void initInteract() {
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

         tvInteractAnnounce.setOnClickListener(v -> {
             if (isAnnounceFolded) {
                 isAnnounceFolded = false;
                 tvInteractAnnounce.setText("收起");
                 tvAppAnnounce.setEllipsize(null);
                 tvAppAnnounce.setMaxLines(Integer.MAX_VALUE);
             } else {
                 isAnnounceFolded = true;
                 tvInteractAnnounce.setText("展开");
                 tvAppAnnounce.setEllipsize(TextUtils.TruncateAt.END);
                 tvAppAnnounce.setMaxLines(2);
             }
         });

        ivAddComment.setOnClickListener(v -> {
            Intent toAddComment = new Intent(AppDetailsActivity.this,AddAppCommentActivity.class);
            startActivity(toAddComment);
            overridePendingTransition(R.anim.ani_right_get_into, R.anim.ani_left_sign_out);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ani_left_get_into, R.anim.ani_right_sign_out);
    }
}
