package com.example.em;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private static final int[] mImageIDs=
            new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> viewList;
    private LinearLayout llPoint;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.vp);
        llPoint = (LinearLayout) findViewById(R.id.ll_point_group);
        initGuideViews();
        viewPager.setAdapter(new GuideAdaper());

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
}
