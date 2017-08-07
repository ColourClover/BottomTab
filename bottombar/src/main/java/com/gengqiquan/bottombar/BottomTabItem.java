package com.gengqiquan.bottombar;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by gengqiquan on 2017/8/7.
 */

public class BottomTabItem {
    @DrawableRes
    int defaultIcon;
    @DrawableRes
    int checkedIcon;
    @ColorInt
    int defaultColor;
    @ColorInt
    int checkedColor;
    @NonNull
    String text;

    public BottomTabItem(@DrawableRes int defaultIcon, @DrawableRes int checkedIcon, @ColorInt int defaultColor, @ColorInt int checkedColor, @NonNull String text) {
        this.defaultIcon = defaultIcon;
        this.checkedIcon = checkedIcon;
        this.defaultColor = defaultColor;
        this.checkedColor = checkedColor;
        this.text = text;
    }


    protected ImageView icon;
    protected TextView lable;
    protected View dot;
    protected TextView number;
    protected RelativeLayout layout;
}
