package com.example.em.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.em.R;
import com.example.em.adapter.HomeFragmentAdapter;
import com.example.em.beans.HomeFragmentBean;
import com.example.em.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private InputMethodManager mInputMethodManager;
    private SliderLayout sliderShow;
    private static final String TAG = "HomeFragment";
    private List<HomeFragmentBean> mDatas;
    private View mView;
    private EditText mSearchText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        sliderShow = (SliderLayout) mView.findViewById(R.id.slider);
        mSearchText = (EditText) mView.findViewById(R.id.toolbar_searchview);
        mInputMethodManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //首页滚动广告
        initSliderLayout();
        //首页其他内容
        initRecView();
        //事件监听
        addListenser();
        return mView;
    }

    private void addListenser() {
        mSearchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mSearchText.setHint("");
                    mSearchText.setGravity(Gravity.LEFT);
                    mSearchText.setFocusable(true);
                    mSearchText.setFocusableInTouchMode(true);
                    //mSearchText.requestFocus();//请求焦点
                    //mSearchText.findFocus();//获取焦点
                    if (!mInputMethodManager.isActive()) {
                        mInputMethodManager.showSoftInput(mSearchText, InputMethodManager.SHOW_FORCED);
                    }
                }else {
                    if (mSearchText.getText()==null) {
                        mSearchText.setHint("请输入搜索内容");
                        mSearchText.setGravity(Gravity.CENTER);
                    }

                    mSearchText.setFocusable(false);
                    mSearchText.setFocusableInTouchMode(false);
                    if (mInputMethodManager.isActive()) {
                        mInputMethodManager.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);// 隐藏输入法
                    }
                    Toast.makeText(HomeFragment.this.getActivity(),"失去焦点了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    * 首页其他内容初始化
    *
    * */
    private void initRecView() {
        initDatas();
        RecyclerView recView = (RecyclerView) mView.findViewById(R.id.rec_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(mDatas);
        recView.setAdapter(homeFragmentAdapter);
        recView.addItemDecoration(new DividerDecoration());
        recView.setLayoutManager(layoutManager);
    }
    /*
    * 将数据装入List中，传递给Adapter
    * */
    private void initDatas() {
        mDatas = new ArrayList<HomeFragmentBean>();

        HomeFragmentBean category = new HomeFragmentBean("热门活动",R.drawable.img_big_1,R.drawable.img_1_small1,R.drawable.img_1_small2);
        mDatas.add(category);

        category = new HomeFragmentBean("有利可图",R.drawable.img_big_4,R.drawable.img_4_small1,R.drawable.img_4_small2);
        mDatas.add(category);

        category = new HomeFragmentBean("品牌街",R.drawable.img_big_2,R.drawable.img_2_small1,R.drawable.img_2_small2);
        mDatas.add(category);

        category = new HomeFragmentBean("金融街 包赚翻",R.drawable.img_big_1,R.drawable.img_3_small1,R.drawable.imag_3_small2);
        mDatas.add(category);

        category = new HomeFragmentBean("超值购",R.drawable.img_big_0,R.drawable.img_0_small1,R.drawable.img_0_small2);
        mDatas.add(category);
    }

    /**
     * 首页滚动效果初始化
     */
    private void initSliderLayout() {
        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView
                .description("Game")
                .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(),"one",Toast.LENGTH_SHORT).show();
            }
        });


        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2
                .description("Game of")
                .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(),"two",Toast.LENGTH_SHORT).show();
            }
        });

        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3
                .description("Game of Thrones")
                .image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(),"three",Toast.LENGTH_SHORT).show();
            }
        });


        sliderShow.addSlider(textSliderView);
        sliderShow.addSlider(textSliderView2);
        sliderShow.addSlider(textSliderView3);

        sliderShow.setPresetTransformer(SliderLayout.Transformer.Tablet);
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderShow.setDuration(2000);
        sliderShow.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: 1111111111111111111111111111111"); 
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: #$%^****");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: 66666666666666666666666666");

            }
        });

    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }
}
