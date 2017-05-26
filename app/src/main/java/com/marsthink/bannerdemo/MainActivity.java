/*
 *     Copyright (c) 2016 Meituan Inc.
 *
 *     The right to copy, distribute, modify, or otherwise make use
 *     of this software may be licensed only pursuant to the terms
 *     of an applicable Meituan license agreement.
 *
 */

package com.marsthink.bannerdemo;

import android.app.Activity;
import android.os.Bundle;

import com.marsthink.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序主入口
 *
 * @author liuyazhuang
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBanner();
    }

    private void initBanner() {
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495704851386&di=940358729e2732a02642131ffa037f6e&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F140714%2F1-140G40Z645-51.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495704884531&di=17856c7c591f4051a1ce460e53151027&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F064%2F388F7330277K.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495704884531&di=47e3e3bfa4af41b474c3d267998508c1&imgtype=0&src=http%3A%2F%2Fwww.nitutu.com%2Fuploads%2Fallimg%2F150802%2Fco150P20P127-0.jpg");
        List<String> titles = new ArrayList<>();
        titles.add("1st");
        titles.add("2st");
        titles.add("3st");
        Banner banner = (Banner) findViewById(R.id.banner);
        banner.setBannerTitles(titles);
        banner.setImageLoader(new BannerImageLoader()).setImages(imgUrls).start();
    }

}
