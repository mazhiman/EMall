package com.example.em.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.em.R;
import com.example.em.fragment.CartFragment;
import com.example.em.fragment.CategoryFragment;
import com.example.em.fragment.HomeFragment;
import com.example.em.fragment.HotFragment;
import com.example.em.fragment.UserFragment;

/*
* 使用FragmentTabHost必须继承FragmentActivity
* 不过AppCompatActivity已经继承了FragmentActivity
* */
public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //不能写R.id.tabhost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //FragmentTabHost必须调用setup（）；
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        //创建tabSpace
        //TabHost.TabSpec tabSpec = mTabHost.newTabSpec("home");
        //???why???
        mLayoutInflater = LayoutInflater.from(this);
        /*View view = mLayoutInflater.inflate(R.layout.tab_items,null);
        //下面必须是view.findViewById不能是findViewById
        ImageView icon = (ImageView) view.findViewById(R.id.icon_tab);
        TextView textIndicator = (TextView) view.findViewById(R.id.txt_indicator);
        icon.setBackgroundResource(R.mipmap.icon_home);
        textIndicator.setText("首页");

        tabSpec.setIndicator(view);
        mTabHost.addTab(tabSpec, HomeFragment.class,null);*/
        initTabHost();

    }

    private void initTabHost() {
        //图片资源数组
         int[] iconResArr = new int[]{
             R.drawable.selector_icon_home,
             R.drawable.selector_icon_category,
             R.drawable.selector_icon_hot,
             R.drawable.selector_icon_cart,
             R.drawable.selector_icon_user

        };
        //Fragment数组
         Class[] fragmentArr = {
                HomeFragment.class,
                CategoryFragment.class,
                HotFragment.class,
                CartFragment.class,
                UserFragment.class
        };
        //spc
         int[] spcNameArr = {
                 R.string.home,
                 R.string.category,
                 R.string.hot,
                 R.string.cart,
                 R.string.user
         };
         for(int i=0;i<iconResArr.length;i++) {
             TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(spcNameArr[i]));
             View view = mLayoutInflater.inflate(R.layout.tab_items,null);
             ImageView icon = (ImageView) view.findViewById(R.id.icon_tab);
             TextView textIndicator = (TextView) view.findViewById(R.id.txt_indicator);

             icon.setBackgroundResource(iconResArr[i]);
             textIndicator.setText(spcNameArr[i]);

             tabSpec.setIndicator(view);
             mTabHost.addTab(tabSpec, fragmentArr[i],null);
         }
         //显示分割线
         //mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
    }

}
