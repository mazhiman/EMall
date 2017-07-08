package com.example.em.beans;

/**
 * Created by Administrator on 2017/6/11.
 */

public class HomeFragmentBean {
    private int imgBig;
    private int imgSmallTop;
    private int imgSmallBottom;
    private String textTitle;

    public HomeFragmentBean(String textTitle,int imgBig, int imgSmallTop, int imgSmallBottom) {
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
        this.textTitle = textTitle;
    }

    public int getImgBig() {
        return imgBig;
    }

    public int getImgSmallTop() {
        return imgSmallTop;
    }

    public int getImgSmallBottom() {
        return imgSmallBottom;
    }

    public String getTextTitle() {
        return textTitle;
    }
}
