package com.example.em.beans;

/**
 * Created by Administrator on 2017/5/28.
 */

public class TabRes {
    private int iconResId;
    private int spcNameId;
    private Class fragmentCls;

    public TabRes(int iconResId, int spcNameId, Class fragmentCls) {
        this.iconResId = iconResId;
        this.spcNameId = spcNameId;
        this.fragmentCls = fragmentCls;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getSpcNameId() {
        return spcNameId;
    }

    public void setSpcNameId(int spcNameId) {
        this.spcNameId = spcNameId;
    }

    public Class getFragmentCls() {
        return fragmentCls;
    }

    public void setFragmentCls(Class fragmentCls) {
        this.fragmentCls = fragmentCls;
    }
}
