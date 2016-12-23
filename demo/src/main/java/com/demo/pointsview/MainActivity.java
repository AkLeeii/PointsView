package com.demo.pointsview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.lch.pointsview.PointsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PointsView pointsView;
    private ViewPager viewPager;
    private List<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointsView = (PointsView) findViewById(R.id.activity_main_pointview);
        viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        data();
        //要先设置一下小圆点的数量
        pointsView.setSize(images.size());
        //选中第0个
        pointsView.setFocus(0);
        viewPager.setAdapter(new adapter(images));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pointsView.setFocus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void data() {
        images = new ArrayList<>();
        images.add(R.mipmap.mitsuha);
        images.add(R.mipmap.mitsuha);
        images.add(R.mipmap.mitsuha);
        images.add(R.mipmap.mitsuha);
        images.add(R.mipmap.mitsuha);
    }

    private class adapter extends PagerAdapter {
        private List<Integer> images;
        private List<ImageView> views;

        public adapter(List<Integer> images) {
            this.images = images;
            views = new ArrayList<>();
        }

        @Override
        public void notifyDataSetChanged() {
            views.clear();
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            views.add(imageView);
            container.addView(imageView);
            Glide.with(MainActivity.this).load(images.get(position)).into(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }
}
