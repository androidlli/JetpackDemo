package com.cango.basicdemo

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/25
 *     desc   :
 * </pre>
 */
class AppExecutors {
    val mDiskIO = Executors.newSingleThreadExecutor()
    val mNetworkIO = Executors.newFixedThreadPool(3)
    val mMainThread = MainThreadExecutor()
}

class MainThreadExecutor : Executor {
    private val mMainHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable?) {
        mMainHandler.post(command)
    }
}