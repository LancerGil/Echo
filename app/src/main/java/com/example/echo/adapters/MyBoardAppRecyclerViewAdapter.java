package com.example.echo.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.views.fragments.BoardAppFragment.OnListFragmentInteractionListener;
import com.example.echo.entities.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} 用于装载显示 {@link DummyItem} 以及实现一个接口↓
 * {@link OnListFragmentInteractionListener}.
 * 用于适配APP列表fragment中的recyclerview.
 */
public class MyBoardAppRecyclerViewAdapter extends RecyclerView.Adapter<MyBoardAppRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private final int itemIndex;
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyBoardAppRecyclerViewAdapter(List<DummyItem> items, int position, OnListFragmentInteractionListener listener) {
        itemIndex = position;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder-viewType: " + viewType);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_boardapp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(itemIndex >= 0?itemIndex * 3 + position : position);
        holder.tvAppName.setText(mValues.get(itemIndex >= 0?itemIndex * 3 + position : position).id);
        holder.tvAppAbstract.setText(mValues.get(itemIndex >= 0?itemIndex * 3 + position : position).content);

        //当装载到最后一项时,将其item底部的分界线隐藏
        if (position == getItemCount() - 1) {
            holder.underline.setVisibility(View.INVISIBLE);
        }

        //设置"详情"的点击事件,回调接口方法
        holder.btnCheckApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemIndex >= 0) {
            return 3;
        } else {
            return mValues.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvAppName;
        public final TextView tvAppAbstract;
        public final ImageView ivAppLogo;
        public final Button btnCheckApp;
        public final View underline;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvAppName = (TextView) view.findViewById(R.id.tv_app_name);
            tvAppAbstract = (TextView) view.findViewById(R.id.tv_app_abstrack);
            ivAppLogo = view.findViewById(R.id.iv_app_logo);
            btnCheckApp = view.findViewById(R.id.btn_check_app);
            underline = view.findViewById(R.id.underline);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvAppAbstract.getText() + "'";
        }
    }
}
