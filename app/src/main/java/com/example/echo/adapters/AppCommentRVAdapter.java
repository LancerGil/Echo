package com.example.echo.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.entities.AppContentInList.DummyAppInListItem;
import com.example.echo.entities.CommentContent.DummyCommentItem;
import com.example.echo.views.fragments.BoardAppFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} 用于装载显示 {@link DummyAppInListItem} 以及实现一个接口↓
 * {@link OnListFragmentInteractionListener}.
 * 用于适配APP列表fragment中的recyclerview.
 */
public class AppCommentRVAdapter extends RecyclerView.Adapter<AppCommentRVAdapter.ViewHolder> {
    private static final String TAG = "AppCommentRVAdapter";

    private final List<DummyCommentItem> mValues;

    public AppCommentRVAdapter(List<DummyCommentItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder-viewType: " + viewType);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvCommentTitle.setText(mValues.get(position).title);
        holder.tvCommentContent.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvCommentTitle;
        public final TextView tvCommentContent;
        public DummyCommentItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvCommentTitle = view.findViewById(R.id.tv_comment_title);
            tvCommentContent = view.findViewById(R.id.tv_comment_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvCommentContent.getText() + "'";
        }
    }
}
