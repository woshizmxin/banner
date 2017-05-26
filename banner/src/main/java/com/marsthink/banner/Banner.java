/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.marsthink.banner.adapter.ViewPagerAdapter;
import com.marsthink.banner.listener.ImageLoader;
import com.marsthink.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoumao on 2017/5/25.
 */

public class Banner extends FrameLayout {
    private String TAG = "banner";
    private List<String> titles = new ArrayList<>();
    private List<View> dots;
    private List<String> imageUrls = new ArrayList();
    private List<ImageView> imageViews = new ArrayList<>();
    private ViewPager mViewPaper;
    private long delayTime = 2000;
    private ImageLoader imageLoader = null;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    private ScheduledExecutorService scheduledExecutorService;
    private ViewPagerAdapter adapter;
    private Context context;
    private TextView tv_title;
    private Handler mHandler = new Handler();

    public static class Transformer {
        public static Class DefaultTransformer = com.marsthink.banner.transformer.DefaultTransformer.class;
        public static Class RotateDownTransformer = com.marsthink.banner.transformer.RotateDownTransformer.class;
        public static Class ZoomInTransformer = com.marsthink.banner.transformer.ZoomInTransformer.class;
        public static Class AccordionTransformer = com.marsthink.banner.transformer.AccordionTransformer.class;
        public static Class DepthPageTransformer = com.marsthink.banner.transformer.DepthPageTransformer.class;
        public static Class RotateUpTransformer = com.marsthink.banner.transformer.RotateUpTransformer.class;
        public static Class ZoomOutSlideTransformer = com.marsthink.banner.transformer.ZoomOutSlideTransformer.class;
        public static Class FlipHorizontalTransformer = com.marsthink.banner.transformer.FlipHorizontalTransformer.class;
        public static Class ScaleInOutTransformer = com.marsthink.banner.transformer.ScaleInOutTransformer.class;
        public static Class FlipVerticalTransformer = com.marsthink.banner.transformer.FlipVerticalTransformer.class;
        public static Class ForegroundToBackgroundTransformer = com.marsthink.banner.transformer.ForegroundToBackgroundTransformer.class;
        public static Class TabletTransformer = com.marsthink.banner.transformer.TabletTransformer.class;
        public static Class CubeOutTransformer = com.marsthink.banner.transformer.CubeOutTransformer.class;
        public static Class StackTransformer = com.marsthink.banner.transformer.StackTransformer.class;
        public static Class ZoomOutTranformer = com.marsthink.banner.transformer.ZoomOutTranformer.class;
        public static Class BackgroundToForegroundTransformer = com.marsthink.banner.transformer.BackgroundToForegroundTransformer.class;
        public static Class CubeInTransformer = com.marsthink.banner.transformer.CubeInTransformer.class;
    }

    private void initAnimationMap() {

    }


    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mViewPaper = (ViewPager) view.findViewById(R.id.bannerViewPager);
        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (titles.size() > position) tv_title.setText(titles.get(position));
                else tv_title.setText("");
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));
    }

    public void start() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPagerRunnable(),
                2,
                delayTime,
                TimeUnit.MILLISECONDS);
    }

    public Banner setBannerStyle(int bannerStyle) {
        return this;
    }

    public Banner setDelayTime(long delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public Banner setBannerAnimation(Class<? extends ViewPager.PageTransformer> transformer) {
        try {
            mViewPaper.setPageTransformer(true, transformer.newInstance());
        } catch (Exception e) {
            Log.e(TAG, "Please set the PageTransformer class");
        }
        return this;
    }

    public Banner setBannerTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public Banner setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    public Banner setImages(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        for (String imgUrl : imageUrls) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);
            if (imageLoader != null) {
                imageLoader.displayImage(context, imgUrl, imageView);
            } else {
                Log.e(TAG, "please setImageLoader() first !");
            }
        }
        adapter.setImages(imageViews);
        return this;
    }

    public Banner setOnBannerListener(OnBannerListener onBannerListener) {
        this.adapter.setOnBannerListener(onBannerListener);
        return this;
    }

    private class ViewPagerRunnable implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageViews.size();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mViewPaper.setCurrentItem(currentItem);
                }
            });
        }
    }
}
