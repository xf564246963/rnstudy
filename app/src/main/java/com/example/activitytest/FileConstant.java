package com.example.activitytest;

import android.os.Environment;

import java.io.File;

/**
 * Created by Song on 2017/2/15.
 */
public class FileConstant {


    public static final String ZIP_NAME = "patch";
    /**
     * bundle文件名
     */
    public static final String JS_BUNDLE_LOCAL_FILE = "TEST/index.android.bundle";

    /**
     * zip文件
     */
    public static final String JS_BUNDLE_LOCAL_PATH = Environment.getExternalStorageDirectory().toString()
            + File.separator + MainApplication.getInstance().getAppPackageName() +"/"+JS_BUNDLE_LOCAL_FILE;

    public static final String JS_PATCH_LOCAL_PATH = Environment.getExternalStorageDirectory().toString()
            + File.separator + MainApplication.getInstance().getAppPackageName() +"/patch.zip";

    /**
     * 下载URL
     */
//    http://172.16.26.216:8081/index.android.bundle
    public static final String JS_BUNDLE_REMOTE_URL = "http://172.16.26.116:3000/index.android.bundle";

}
