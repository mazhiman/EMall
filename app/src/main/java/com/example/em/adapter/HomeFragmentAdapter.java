package com.example.em.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.em.R;
import com.example.em.beans.HomeFragmentBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {
    private static final int VIEW_TYPE_L = 0;
    private static final int VIEW_TYPE_R = 1;
    private List<HomeFragmentBean> mDatas;
    //1.创建ViewHolder内部类，作为RecyclerView.Adapter泛型,该内部类可以定义为静态，
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextTitle;
        ImageView mImgBig;
        ImageView mImgSmallTop;
        ImageView mImgSmallBottom;
        //2.获得控件ID
        public ViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title);
            mImgBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            mImgSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            mImgSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);

        }
    }
    //3.初始化时获得数据
    public HomeFragmentAdapter(List<HomeFragmentBean> datas) {
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据getItemViewType返回的viewType加载布局文件
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType==VIEW_TYPE_L) {
            view = inflater.inflate(R.layout.template_home_cardview_l,parent,false);
        }
        if (viewType==VIEW_TYPE_R) {
            view =inflater.inflate(R.layout.template_home_cardview_r,parent,false);
        }
        if(view!=null) {
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //5.根据position获取每个Item对应的Fruit实例，并将对应资源设置到Viewholder对应的item
        HomeFragmentBean hfb = mDatas.get(position);
        holder.mTextTitle.setText(hfb.getTextTitle());
        holder.mImgBig.setImageResource(hfb.getImgBig());
        holder.mImgSmallTop.setImageResource(hfb.getImgSmallTop());
        holder.mImgSmallBottom.setImageResource(hfb.getImgSmallBottom());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        //获得Item的位置,据此选择加载不同的ITem布局
        //该函数返回值传递给onCreateViewHolder方法int viewType参数
        if (position%2==0) {
            return VIEW_TYPE_L;
        }
        else return  VIEW_TYPE_R;
    }
}
