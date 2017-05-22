package com.example.em;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private static final String TAG = "GuideActivity";

    private static final int[] mImageIDs=
            new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> viewList;
    private LinearLayout llPoint;
    private ViewPager viewPager;
    private View viewPointRed;
    private int pointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.vp);
        llPoint = (LinearLayout) findViewById(R.id.ll_point_group);
        viewPointRed = findViewById(R.id.view_point_red);
        initGuideViews();
        //为viewPager设置适配器
        viewPager.setAdapter(new GuideAdaper());
        //为viewpager设置监听，监听其位置变化，以便小红点与之一同变化
        viewPager.addOnPageChangeListener(new GuidePageListener());

    }

    /**
     * 初始化引导界面
     */
    private void initGuideViews() {
        viewList = new ArrayList<ImageView>();
        for (int i=0;i<mImageIDs.length;i++) {
            //设置引导页当前内容
            ImageView imageView = new ImageView(this);
            //设置当前引导页背景
            imageView.setBackgroundResource(mImageIDs[i]);
            viewList.add(imageView);
        }
        //动态加载小灰点
        for (int i=0;i<mImageIDs.length;i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            //设置灰色小圆点大小
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(20,20);
            if (i>0) {
                //设置小圆点间间距，第一个小圆点不用设置
                params.leftMargin = 20;
            }
            point.setLayoutParams(params);
            //为线性布局添加灰色圆点
            llPoint.addView(point);
        }

        //计算两个小灰点间的距离，以便计算小红点应滑动的位置
        //measure layout ondraw,只有在layout布局完成后，才能获得小灰点的位置
        //因为此时，才完成布局，所以要监听layout是否完成
        //首先获得ViewTreeObserver对象
        ViewTreeObserver viewTreeObserver = llPoint.getViewTreeObserver();
        //为ViewTreeObserver对象设置监听layout
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //layout结束时，回调此方法
            @Override
            public void onGlobalLayout() {
                llPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                pointWidth = llPoint.getChildAt(1).getLeft()
                        - llPoint.getChildAt(0).getLeft();
                Log.d(TAG,"灰点间距：" + pointWidth);
            }
        });
    }

    class GuideAdaper extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIDs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            //container.removeView(viewList.get(position));
        }
    }

    /**
     * 处理viewpager页面变化时事件
     */
    class GuidePageListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "position"+position+"  positionOfset"
                    +positionOffset+"  positionOffsetPixels"+positionOffsetPixels);
            //根据页面移动的位置，计算小红点应该滑动的距离
            int len = (int) (pointWidth*positionOffset+position*pointWidth);
            //小红点的页面参数已经定义在XMl文件中，先获得其布局参数
            RelativeLayout.LayoutParams params =
                    (RelativeLayout.LayoutParams) viewPointRed.getLayoutParams();
            //根据计算的滑动距离移动小红点位置
            params.leftMargin = len;
            //为小红点设置新的布局参数
            viewPointRed.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
