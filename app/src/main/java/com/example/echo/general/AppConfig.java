package com.example.echo.general;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    public static final String APP_ID="Echo";
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_PWD = "password";
    public static final String KEY_CACHED_ACCOUNT = "cached_account";


    //储存账号密码
    public static void cacheToken(Context context, String account, String pwd){
        SharedPreferences.Editor editor = context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_CACHED_ACCOUNT,true);
        editor.putString(KEY_ACCOUNT,account);
        editor.putString(KEY_PWD,pwd);
        editor.apply();
    }

    //检查是否已记住密码
    public static boolean checkAccountCachedStatus(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getBoolean(KEY_CACHED_ACCOUNT,false);
    }

    //取得账号
    public static String getCachedAccount(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_ACCOUNT,null);
    }

    //取得密码
    public static String getCachedPwd(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_PWD,null);
    }

    //清除账号密码
    public static void removeToken(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_CACHED_ACCOUNT,false);
        editor.remove(KEY_ACCOUNT);
        editor.remove(KEY_PWD);
        editor.apply();
    }


}
