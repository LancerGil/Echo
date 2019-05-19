package com.example.echo.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.echo.R;
import com.example.echo.adapters.BoardListPagerAdapter;
import com.ocnyang.pagetransformerhelp.cardtransformer.AlphaAndScalePageTransformer;

public class FragBoard extends Fragment {
    private static final String TAG="FragBoard";

    /**
     * 声明对象
     */
    private OnFragmentInteractionListener mListener;
    private BoardAppFragment boardAppFragment;
    private ViewPager mViewPager;

    public FragBoard() {
        // Required empty public constructor
    }

    public static FragBoard newInstance(String param1, String param2) {
        return new FragBoard();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //初始化视图
        View view = inflater.inflate(R.layout.fragment_frag_board, container, false);
        final ViewPager viewPager = view.findViewById(R.id.vp_board);
        mViewPager = viewPager;
        final TabLayout tabLayout = view.findViewById(R.id.tab_choose_board);
        //显示排行榜的viewpager的设置,
        BoardListPagerAdapter mBoardListPagerAdapter = new BoardListPagerAdapter(getChildFragmentManager(),3);
        viewPager.setPageMargin(30);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(true,new AlphaAndScalePageTransformer());
        viewPager.setAdapter(mBoardListPagerAdapter);

        //将tablayout和viewpager绑定起来,同步变换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        //设置监听,若点击当前正显示的fragment的对应tab,则将排行榜滚动到顶部
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: ");
                scrollListToTop();
            }
        });

        //设置tablayout中item之间的margin.
        for(int i=0; i < tabLayout.getTabCount()-1; i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 50, 0);
            tab.requestLayout();
        }

        return view;
    }

    /**
     * 外部方法:
     * 将排行榜滚动到顶部
     */
    public void scrollListToTop(){
        boardAppFragment = (BoardAppFragment)getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.vp_board + ":" + mViewPager.getCurrentItem());
        boardAppFragment.scrollResyclerViewTo(0);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
