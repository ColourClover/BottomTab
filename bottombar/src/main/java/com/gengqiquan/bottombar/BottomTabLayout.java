package com.gengqiquan.bottombar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengqiquan on 2017/8/7.
 */

public class BottomTabLayout extends LinearLayout {
    List<BottomTabItem> mItems = new ArrayList<>();
    Context mContext;
    LayoutParams mLayoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
    int textSize = 12;
    int mIconWidth;
    int mDotColor = R.drawable.red_dot;

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mLayoutParams.weight = 1;
        mIconWidth = dp2px(20);
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setItems(@NonNull List<BottomTabItem> items) {
        mItems = items;
        addViews();
    }

    private void createItemLayout(final BottomTabItem item) {

        item.icon = new ImageView(mContext);
        item.icon.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(mIconWidth, mIconWidth);
        iconParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        iconParams.setMargins(0, dp2px(6), 0, 0);
        item.icon.setLayoutParams(iconParams);

        item.lable = new TextView(mContext);
        item.lable.setTextSize(textSize);
        item.lable.setGravity(Gravity.CENTER);
        item.lable.setText(item.text);
        RelativeLayout.LayoutParams lableParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lableParams.setMargins(0, mIconWidth + dp2px(6), 0, 0);
        lableParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        item.lable.setLayoutParams(lableParams);

        item.layout = new RelativeLayout(mContext);
        item.layout.setLayoutParams(mLayoutParams);
        item.layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                check(item.text);
            }
        });
        item.layout.addView(item.icon);
        item.layout.addView(item.lable);

    }

    SelectInterceptor mSelectInterceptor;
    SelectListener mSelectListener;

    public void setmSelectInterceptor(SelectInterceptor mSelectInterceptor) {
        this.mSelectInterceptor = mSelectInterceptor;
    }

    public void setmSelectListener(SelectListener mSelectListener) {
        this.mSelectListener = mSelectListener;
    }

    private void check(String text) {
        if (mSelectInterceptor != null && !mSelectInterceptor.select(text)) {
            return;
        }
        for (BottomTabItem mItem : mItems) {
            if (text.equals(mItem.text)) {
                mItem.icon.setImageDrawable(getResources().getDrawable(mItem.checkedIcon));
                mItem.lable.setTextColor(mItem.checkedColor);
            } else {
                mItem.icon.setImageDrawable(getResources().getDrawable(mItem.defaultIcon));
                mItem.lable.setTextColor(mItem.defaultColor);
            }

        }
        if (mSelectListener != null) {
            mSelectListener.select(text);
        }
    }

    private void addViews() {
        removeAllViews();
        for (BottomTabItem mItem : mItems) {
            createItemLayout(mItem);
            addView(mItem.layout);
        }
        check(mItems.get(0).text);
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }


    public void addDot(String text) {
        for (BottomTabItem mItem : mItems) {
            if (text.equals(mItem.text) && mItem.dot == null) {
                mItem.dot = getDot();
                mItem.layout.addView(mItem.dot);
                break;
            }
        }
    }

    public void removeDot(String text) {
        for (BottomTabItem mItem : mItems) {
            if (text.equals(mItem.text)) {
                mItem.layout.removeView(mItem.dot);
                mItem.dot = null;
                break;
            }
        }
    }

    private View getDot() {
        View dot = new View(mContext);
        RelativeLayout.LayoutParams dotParams = new RelativeLayout.LayoutParams(dp2px(5), dp2px(5));
        dotParams.setMargins(0, dp2px(6), dp2px(15), 0);
        dotParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        dot.setLayoutParams(dotParams);
        dot.setBackgroundResource(mDotColor);
        return dot;
    }


    public void addNumber(String text, String number) {
        for (BottomTabItem mItem : mItems) {
            if (text.equals(mItem.text)) {
                if (mItem.number == null) {
                    mItem.number = getNumber();
                    mItem.layout.addView(mItem.number);
                }
                mItem.number.setText(number);
                break;
            }

        }

    }

    public void removeNumber(String text) {
        for (BottomTabItem mItem : mItems) {
            if (text.equals(mItem.text)) {
                mItem.layout.removeView(mItem.number);
                mItem.number = null;
                break;
            }
        }
    }


    private TextView getNumber() {
        TextView number = new TextView(mContext);
        number.setGravity(Gravity.CENTER);
        number.setIncludeFontPadding(false);
        number.setTextSize(12);
        number.setTextColor(Color.WHITE);
        number.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_red_radius7));
        number.setPadding(dp2px(5), 0, dp2px(5), 0);
        RelativeLayout.LayoutParams numberParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp2px(14));
        numberParams.setMargins(0, dp2px(3), dp2px(5), 0);
        numberParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        number.setLayoutParams(numberParams);
        number.setBackgroundResource(mDotColor);
        return number;
    }
}
