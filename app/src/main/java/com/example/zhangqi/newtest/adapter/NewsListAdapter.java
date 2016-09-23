package com.example.zhangqi.newtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangqi.newtest.R;
import com.example.zhangqi.newtest.bean.NewsList;
import com.example.zhangqi.newtest.listener.OnItemClickListener;
import com.example.zhangqi.newtest.ui.Home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangqi on 2016/9/12.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListHolder>{
    public Context mContext;
    private List<NewsList.NewsEntity> mList = new ArrayList<>();
    public NewsListAdapter(HomeActivity homeActivity) {
        mContext = homeActivity;
    }

    public void flush(List<NewsList.NewsEntity> list){
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NewsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsListHolder(LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NewsListHolder holder, int position) {
        NewsList.NewsEntity newsList = mList.get(position);
        holder.tv_title.setText(newsList.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if(mOnItemClickListener != null)mOnItemClickListener.onItemClick(newsList);
        });

        Glide.with(mContext).load(newsList.getBackground()).into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class NewsListHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.iv_background)
        ImageView iv_image;
        public NewsListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
