/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.banner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.marsthink.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
        private List<ImageView> images = new ArrayList<ImageView>();
        private OnBannerListener onBannerListener = null;

        public ViewPagerAdapter(List<ImageView> images) {
            this.images = images;
        }

        public void setOnBannerListener(OnBannerListener onBannerListener){
            this.onBannerListener = onBannerListener;
        }

        public ViewPagerAdapter() {
        }

        public void setImages(List<ImageView> images) {
            this.images = images;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, final int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            if (onBannerListener != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBannerListener.OnBannerClick(position);
                    }
                });
            }
            return images.get(position);
        }

    }