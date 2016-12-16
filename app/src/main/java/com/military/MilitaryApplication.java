package com.military;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.military.bean.Channel;
import com.military.common.Constants;
import com.military.utils.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.app.TinkerManager;
import com.tencent.tinker.app.TinkerServerManager;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.server.TinkerServerClient;
import com.tencent.tinker.server.client.PatchRequestCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created by lichengcai on 2016/11/18.
 */

@DefaultLifeCycle(
        //这里将com.github.tinkersample.SampleApplicationLike更改为你自己的包名即可
        application = "com.military.SampleApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL
)
public class MilitaryApplication extends DefaultApplicationLike {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_COUNT = CPU_COUNT+1;
    private static final int MAXNUM_POOL_COUNT = CPU_COUNT*2+1;
    private static final long KEEP_TIME = 10L;

    public static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    public static ArrayList<Channel> mSelected = new ArrayList<>();

    public MilitaryApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent, Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);


        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(application);

//        if (LeakCanary.isInAnalyzerProcess(application)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(application);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        //初始化Tinker
        TinkerInstaller.install(this);

        //初始化TinkerPatch SDK
        TinkerServerManager.installTinkerServer(
                getApplication(), Tinker.with(getApplication()), 3,
                Constants.TINKER_APP_KEY,
                "1.0", "default");
        //开始检查是否有补丁，这里配置的是每隔访问3小时服务器是否有更新。
        TinkerServerManager.checkTinkerUpdate(true);

    }
}
