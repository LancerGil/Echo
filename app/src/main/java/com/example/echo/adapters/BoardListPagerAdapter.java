package com.example.echo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.echo.views.fragments.BoardAppFragment;

/**
 * A {@link FragmentPagerAdapter} 返回仅显示app列表的fragment
 * 用于:首页的简略版排行榜APP列表 && 排行榜页的APP列表
 */
public class BoardListPagerAdapter extends FragmentPagerAdapter {
    private int count;

    public BoardListPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count=count;
    }

    @Override
    public Fragment getItem(int position) {
        return BoardAppFragment.newInstance(count!=4?-1:position);
    }

    @Override
    public int getCount() {
        return count;
    }
}
