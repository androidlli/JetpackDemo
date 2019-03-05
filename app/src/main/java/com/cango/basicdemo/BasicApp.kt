package com.cango.basicdemo

import android.app.Application
import com.cango.basicdemo.db.AppDataBase

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/25
 *     desc   :
 * </pre>
 */
class BasicApp : Application() {
    private lateinit var mAppExecutors: AppExecutors
    override fun onCreate() {
        super.onCreate()
        mAppExecutors = AppExecutors()
    }

    fun getDataBase(): AppDataBase? = AppDataBase.getInstance(this, mAppExecutors)

    fun getDataRepository() = getDataBase()?.let { DataRepository.getInstance(it) }
}