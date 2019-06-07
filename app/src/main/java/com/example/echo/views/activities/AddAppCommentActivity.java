package com.example.echo.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.echo.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AddAppCommentActivity extends AppCompatActivity {
    private final static String TAG = "AddAppCommentActivity";

    //声明View
    private EditText etTitle,etContent;
    private RadioButton rbtnBUG,rbtnSUG;
    private Toolbar toolbar;

    //声明数据变量
    private String title,content;

    //工具
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app_comment);

        initTools();
        initView();
    }

    private void initTools(){
        gson = new Gson();
    }

    private void initView(){
        etTitle = findViewById(R.id.et_comment_title);
        etContent = findViewById(R.id.et_comment_content);
        rbtnBUG = findViewById(R.id.type_bug);
        rbtnSUG = findViewById(R.id.type_sug);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("我要反馈");
        setSupportActionBar(toolbar);
    }

    private JsonObject getDataCommit(){
        title = etTitle.getText().toString();
        content = etContent.getText().toString();
        //CommentContent.DummyCommentItem commentItem = new CommentContent.DummyCommentItem("-1",title,content);
        //String jsonStr = gson.toJson(commentItem);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("content",content);
        return jsonObject;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.confirm:
                //TODO :LSQ -> 提交对app的反馈到服务器。
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ani_left_get_into, R.anim.ani_right_sign_out);
    }
}
