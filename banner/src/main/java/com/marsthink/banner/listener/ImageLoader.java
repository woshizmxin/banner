/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.banner.listener;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by zhoumao on 2017/5/25.
 */

public interface ImageLoader {
    void displayImage(Context context, Object path, ImageView imageView);
}
