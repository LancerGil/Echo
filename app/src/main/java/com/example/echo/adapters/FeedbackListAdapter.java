package com.example.echo.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.echo.R;
import com.example.echo.entities.FeedbackContent.DummyFeedbackItem;
import com.example.echo.views.fragments.BoardAppFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} 用于装载显示 {@link DummyFeedbackItem} 以及实现一个接口↓
 * {@link OnListFragmentInteractionListener}.
 * 用于适配APP列表fragment中的recyclerview.
 */
public class FeedbackListAdapter extends RecyclerView.Adapter<FeedbackListAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private final int itemIndex;
    private final List<DummyFeedbackItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public FeedbackListAdapter(List<DummyFeedbackItem> items, int position, OnListFragmentInteractionListener listener) {
        itemIndex = position;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder-viewType: " + viewType);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_feedback_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
//        Glide.with(holder.feedback_avatar).load(Uri.parse(mValues.get(position).imageUrl));
        holder.feedback_content.setText(mValues.get(position).content);
        holder.feedback_date.setText(mValues.get(position).date);

//        if (itemIndex >= 0 && position == 2) {
//            holder.feedback_underline.setVisibility(View.INVISIBLE);
//        }

//        holder.btnCheckApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onListFragmentInteraction(holder.mItem);
//            }
//        });

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
        public final TextView feedback_content;
        public final TextView feedback_date;
        public final ImageView feedback_avatar;
        public final View feedback_underline;
        public DummyFeedbackItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            feedback_content =  view.findViewById(R.id.feedback_content);
            feedback_date =  view.findViewById(R.id.feedback_date);
            feedback_avatar = view.findViewById(R.id.feedback_avatar);
            feedback_underline = view.findViewById(R.id.feedback_underline);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + tvAppAbstract.getText() + "'";
//        }
    }
}
