package com.example.echo.views.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.echo.R;
import com.example.echo.adapters.BoardListPagerAdapter;
import com.ocnyang.pagetransformerhelp.cardtransformer.AlphaAndScalePageTransformer;
import com.ocnyang.pagetransformerhelp.cardtransformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FragHome extends Fragment {
    private static final String TAG = "FragHome";

    /**
     * 声明对象
     */
    private OnFragmentInteractionListener mListener;
    private PagerAdapter pagerAdapter;
    private Timer timer;
    private static Handler viewPagerScrollHandler = new Handler();

    /**
     * 声明数据,参数
     */
    private ArrayList<ImageView> viewList = new ArrayList<>();
    private boolean pagerScrolling = false;
    private static final int TIME = 4000;
    //轮播图当前位置
    private int bannerPosition = 0;
    //屏幕宽度
    private static float mScreenW = -1;
    //轮转图片的ID列表&标题
    private static final int[] bannerResId = new int[]{R.drawable.header2,
            R.drawable.header0, R.drawable.header1, R.drawable.header2, R.drawable.header0};
    private static final String[] bannerTitles = new String[]{"Title3", "Title1", "Title2", "Title3", "Title1"};

    /**
     * 初始化Views
     */
    private NestedScrollView nestedScrollView;

    public FragHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //获取手机屏幕宽度
        if (mScreenW == -1) {
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay()
                    .getMetrics(metrics);
            mScreenW = metrics.widthPixels;
        }

        //fragment布局初始化 + 设置
        View view = inflater.inflate(R.layout.fragment_frag_home, container, false);
        SearchView searchView = view.findViewById(R.id.search_bar);
        nestedScrollView = view.findViewById(R.id.scroll_view);

        //设置轮转图
        initbanner(view, getContext());

        //设置简略版排行榜
        initSimpleBoard(view, getContext());
        return view;
    }

    @SuppressLint("HandlerLeak")
    private void initbanner(View view, Context context) {
        //设置banner父容器的高度，使banner长宽比为16：9
        final FrameLayout bannerContainer = view.findViewById(R.id.banner_container);
        ViewGroup.LayoutParams layoutParams = bannerContainer.getLayoutParams();
        layoutParams.height = (int) mScreenW * 9 / 16;
        bannerContainer.setLayoutParams(layoutParams);

        //初始化banner
        final ViewPager banner = view.findViewById(R.id.vp_banner);
        final TextView bannerTitle = view.findViewById(R.id.bannerTitle);
        bannerTitle.setText(bannerTitles[0]);
        //设置banner的adapter
        pagerAdapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));

                return viewList.get(position);
            }
        };
        banner.setAdapter(pagerAdapter);
        //设置banner每次轮转的绑绑响应
        /**
         * 1、轮转动画中：自动计时器停止。
         * 2、轮转动画后，自动计时器重启。
         * 3、实现无限轮转的关键操作。（灰色注释）
         */
        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bannerPosition = position;
                //设置标题
                bannerTitle.setText(bannerTitles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //若viewpager滑动未停止:停止计时器；直接返回。
                if (state != ViewPager.SCROLL_STATE_IDLE) {
                    if (timer != null) {
                        timer.cancel();
                        timer.purge();
                        timer = null;
                    }
                    return;
                }
                ;

                //重启计时器
                timer = new Timer(true);
                timer.schedule(new BannerTimerTask(), TIME, TIME);
                pagerScrolling = true;

                //若当前为第一张，设置页面为倒数第二张
                if (bannerPosition == 0) {
                    banner.setCurrentItem(viewList.size() - 2, false);
                } else if (bannerPosition == viewList.size() - 1) {
                    //若当前为倒数第一张，设置页面为第二张
                    banner.setCurrentItem(1, false);
                }

            }
        });

        //更改banner轮转动画 —— 视差
        banner.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                int width = page.getWidth();
                //我们给不同状态的页面设置不同的效果
                //通过position的值来分辨页面所处于的状态
                if (position < -1) {//滑出的页面
                    page.setScrollX((int) (width * 0.75 * -1));
                } else if (position <= 1) {//[-1,1]
                    if (position < 0) {//[-1,0]
                        page.setScrollX((int) (width * 0.75 * position));
                    } else {//[0,1]
                        page.setScrollX((int) (width * 0.75 * position));
                    }
                } else {//即将滑入的页面
                    page.setScrollX((int) (width * 0.75));
                }
            }
        });

        //设置轮转图片的列表 —— ImageView装着图片。
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundColor(Color.WHITE);
            Glide.with(getContext()).load(bannerResId[i]).into(imageView);//设置头像
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(imageView);
        }
        pagerAdapter.notifyDataSetChanged();
        banner.setCurrentItem(1);

        /**
         * banner的定时器
         */
        final ArrayList<ImageView> finalViewList1 = viewList;
        viewPagerScrollHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                bannerPosition++;
                if (finalViewList1.size() != 0) {
                    banner.setCurrentItem(bannerPosition % finalViewList1.size());
                } else {
                    if (bannerPosition > viewList.size()) {
                        bannerPosition -= viewList.size();
                        banner.setCurrentItem(bannerPosition);
                    }
                }

            }
        };
        timer = new Timer(true);
        if (!pagerScrolling) {
            timer.schedule(new BannerTimerTask(), TIME, TIME);
            pagerScrolling = true;
        }
    }

    //计时器每一轮的操作
    class BannerTimerTask extends TimerTask {
        @Override
        public void run() {
            viewPagerScrollHandler.sendEmptyMessage(1);
        }
    }

    /**
     * 简略版排行版 - 3个viewpager.
     *
     * @param view
     * @param context
     */
    private void initSimpleBoard(View view, Context context) {
        final ViewPager vpHotApp, vpNewApp, vpDev;
        vpHotApp = view.findViewById(R.id.vp_hot_app_board);
        vpNewApp = view.findViewById(R.id.vp_new_app_board);
        vpDev = view.findViewById(R.id.vp_dev_board_home);

        setupBoardViewPagers(vpHotApp);
        setupBoardViewPagers(vpNewApp);
        setupBoardViewPagers(vpDev);
    }

    /**
     * 设置单个viewpager: page间隔 + 缓存个数 + 适配器adapter
     *
     * @param viewPager
     */
    private void setupBoardViewPagers(ViewPager viewPager) {
        BoardListPagerAdapter mBoardListPagerAdapter = new BoardListPagerAdapter(getChildFragmentManager(), 4);
        viewPager.setPageMargin(30);
//        viewPager.setPageTransformer(true,new AlphaPageTransformer());
        viewPager.setAdapter(mBoardListPagerAdapter);
    }

    /**
     * 提供给外部的方法:滚动视图至 x,y
     *
     * @param x
     * @param y
     */
    public void scrollViewTo(int x, int y) {
        nestedScrollView.smoothScrollTo(x, y);
    }

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
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
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
