package com.military;

import android.app.Application;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class MilitaryApplication extends Application {
    public static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
}
