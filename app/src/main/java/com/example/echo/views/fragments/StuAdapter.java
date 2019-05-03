package com.example.echo.views.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.echo.R;
import com.example.echo.views.fragments.StuContent;

import java.util.ArrayList;

public class StuAdapter extends RecyclerView.Adapter {
    private ArrayList<StuContent.StuItemContent> data;
    private Context context;

    public StuAdapter(){ }

    public StuAdapter(Context context, ArrayList<StuContent.StuItemContent> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_item, viewGroup, false);
        return new StuItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //强制转换为自定义的元素viewholder
        StuItem vh = (StuItem) viewHolder;
        //从data获取第i个位置的元素的数据
        StuContent.StuItemContent itemData = data.get(i);
        //从该元素数据中读取设置该元素的布局显示
        vh.ivStuPhoto.setImageResource(itemData.stuPhotoId);
        vh.tvStuName.setText(itemData.stuName);
        vh.tvStuNum.setText(itemData.stuNum);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //外部设置列表数据
    public void setData(ArrayList<StuContent.StuItemContent> data){
        this.data = data;
        notifyDataSetChanged();
    }

    //外部添加列表数据
    public void addData(ArrayList<StuContent.StuItemContent> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    //外部清除列表数据
    public void clearData(){
        this.data.clear();
        notifyDataSetChanged();
    }

    //自定义viewholder
    class StuItem extends RecyclerView.ViewHolder {

        public TextView tvStuName,tvStuNum;
        public ImageView ivStuPhoto;

        public StuItem(@NonNull View itemView) {
            super(itemView);
            tvStuName = itemView.findViewById(R.id.studentItem_textView1);
            tvStuNum = itemView.findViewById(R.id.studentItem_textView2);
            ivStuPhoto = itemView.findViewById(R.id.studentItem_imageView1);
        }

    }
}
