/*
 *     Copyright (c) 2016 Meituan Inc.
 *  
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.bannerdemo;

import android.content.Context;
import android.widget.ImageView;

import com.marsthink.banner.listener.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by zhoumao on 2017/5/25.
 */

public class BannerImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load(path.toString()).into(imageView);
    }
}
