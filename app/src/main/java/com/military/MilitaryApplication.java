package com.military;

import android.app.Application;

import com.military.bean.Channel;
import com.military.utils.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class MilitaryApplication extends Application {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_COUNT = CPU_COUNT+1;
    private static final int MAXNUM_POOL_COUNT = CPU_COUNT*2+1;
    private static final long KEEP_TIME = 10L;

    private static MilitaryApplication mInstance;
    public static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    public static ArrayList<Channel> mSelected = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static MilitaryApplication getInstance() {
        return mInstance;
    }

}
