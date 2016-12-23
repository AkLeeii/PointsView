package com.lch.pointsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * Created by lch on 2016/12/23.
 */
public class PointsView extends LinearLayout {
    private Context context;
    private ArrayList<ImageView> pointArr;
    private int selector_resource;
    private int size;

    public PointsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.pointsview, 0, 0);
        selector_resource = array.getResourceId(R.styleable.pointsview_selector_resource, 0);
    }

    public void setSelectorResource(int selector_resource) {
        this.selector_resource = selector_resource;
    }

    /**
     * 设置轮播图数量
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
        int height = getLayoutParams().height;
        removeAllViews();
        if (pointArr == null) {
            pointArr = new ArrayList<>();
        } else {
            pointArr.clear();
        }
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LayoutParams(height, height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (selector_resource != 0)
                imageView.setImageResource(selector_resource);
            pointArr.add(imageView);
            addView(imageView);
        }
    }

    /**
     * 更改选中
     * @param count
     */
    public void setFocus(int count) {
        if (count > size) {
            return;
        } else {
            for (int i = 0; i < size; i++) {
                if (i == count) {
                    pointArr.get(i).setEnabled(false);
                } else {
                    pointArr.get(i).setEnabled(true);
                }
            }
        }
    }
}
