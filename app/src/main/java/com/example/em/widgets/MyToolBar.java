package com.example.em.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.em.R;

/**
 * Created by Administrator on 2017/5/30.
 */

public class MyToolBar extends Toolbar {
    //在非Activity子类里，想要获取布局文件，需利用LayoutInflater
    private LayoutInflater mInflater;
    private EditText mSearchView;
    private TextView mTextTitle;
    private ImageButton mRightButton;
    private View mView;

    public MyToolBar(Context context) {
        this(context,null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化自定义布局
        initView();
        //设置搜索框距屏幕两侧的距离
        setContentInsetsRelative(10,10);
        //attrs不为空时，读写attrs.xml中我们自定义的属性???
        if(attrs!=null) {
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.MyToolBar, defStyleAttr, 0);
            //读取attrs.xml中自定义的rightButtonIcon
            final Drawable rightButtonIcon = a.getDrawable(R.styleable.MyToolBar_rightButtonIcon);
            if (rightButtonIcon != null) {
                setRightButtonIcon(rightButtonIcon);
            }
            //读取读取attrs.xml中自定义的isShowSearchView属性
            boolean isShowSearchView =
                    a.getBoolean(R.styleable.MyToolBar_isShowSearchView,false);
            if(isShowSearchView) {
                //隐藏RightButton
                hideRightButton();
                //隐藏TextTitle
                hideTextTitle();
                //显示SearchView
                showSearchView();


            }

            //回收资源
            a.recycle();


        }



    }
    //设置右边按钮
    private void setRightButtonIcon(Drawable icon) {
        //为ImagineButton mRightButton设置图片，并让其可见
        mRightButton.setImageDrawable(icon);
        showRightButton();
    }

    private void initView() {
        if(mView==null){
            //先得到LayoutInflater
           // mInflater = LayoutInflater.from(getContext());
            //再得到View ！！！！！！！！！！！！！！！！！！！！！！！查
            mView = LayoutInflater.from(getContext()).inflate(R.layout.toolbar,null);
            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mRightButton = (ImageButton) mView.findViewById(R.id.toolbar_rightButton);
            //设置布局参数
            LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView,params);
        }
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        //父类ToolBar初始化时，会调用setTitle方法，但MyToolBar方法
        //initView在ToolBar构造函数后执行，因此无法设置标题
        initView();
        if(mTextTitle!=null) {
            //这个title由布局文件中传入
            mTextTitle.setText(title);
            //显示标题
            showTextTitle();
        }

    }

    //定义rightButton的点击事件监听
    public void setRightButtonOnClickListener(OnClickListener li){
        mRightButton.setOnClickListener(li);
    }
    //控制mTextTitle显示
    private void showTextTitle() {
        if(mTextTitle!=null)
            mTextTitle.setVisibility(VISIBLE);
    }
    //控制TextTitle隐藏
    private void hideTextTitle() {
        if(mTextTitle!=null)
            mTextTitle.setVisibility(GONE);
    }
    //控制mSearchView显示
    private void showSearchView() {
        if(mSearchView!=null)
            mSearchView.setVisibility(VISIBLE);
    }
    //控制mSearchView隐藏
    private void hideSearchView() {
        if(mSearchView!=null)
            mSearchView.setVisibility(GONE);
    }
    //控制mRightButton显示
    private void showRightButton() {
        if(mRightButton!=null)
            mRightButton.setVisibility(VISIBLE);
    }

    //控制mRightButton隐藏
    private void hideRightButton() {
        if(mRightButton!=null)
            mRightButton.setVisibility(GONE);
    }


}
