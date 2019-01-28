package com.cango.basicdemo

import android.app.Application

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/25
 *     desc   :
 * </pre>
 */
class BasicApp : Application() {
    private val mAppExecutors = AppExecutors()
    override fun onCreate() {
        super.onCreate()
    }
}