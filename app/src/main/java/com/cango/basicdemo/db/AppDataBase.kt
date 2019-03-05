package com.cango.basicdemo.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cango.basicdemo.AppExecutors
import com.cango.basicdemo.DataGenerator
import com.cango.basicdemo.db.dao.CommentDao
import com.cango.basicdemo.db.dao.ProductDao
import com.cango.basicdemo.db.entity.CommentEntity
import com.cango.basicdemo.db.entity.ProductEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/28
 *     desc   :
 * </pre>
 */
const val DATA_BASE_NAME = "basic-db"

@Database(version = 1, entities = [ProductEntity::class, CommentEntity::class])
@TypeConverters(DateConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun commentDao(): CommentDao
    var mIsDatabaseCreated = MutableLiveData<Boolean>()

    //使用Object构建单例模式，伴生对象
    companion object {
        private var mInstance: AppDataBase? = null
        fun getInstance(appContext: Context, appExcutors: AppExecutors): AppDataBase? {
            if (null == mInstance) {
                synchronized(AppDataBase::class) {
                    mInstance = buildDataBase(appContext.applicationContext, appExcutors)
                    mInstance!!.updateDatabaseCreated(appContext)
                }
            }
            return mInstance
        }

        private fun buildDataBase(appContext: Context, appExcutors: AppExecutors): AppDataBase {
            return Room.databaseBuilder(appContext, AppDataBase::class.java, DATA_BASE_NAME)
                .addCallback(object : Callback() {
                    //匿名内部类
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //执行初始化数据的代码
                        appExcutors.mDiskIO.execute {
                            //模拟延迟
                            addDelay()
                            val appDataBase = AppDataBase.getInstance(appContext, appExcutors)
                            val products = DataGenerator.generateProducts()
                            val comments = DataGenerator.generateCommentsForProducts(products)
                            appDataBase!!.runInTransaction {
                                appDataBase.productDao().insertAll(products)
                                appDataBase.commentDao().insertAll(comments)
                            }
                            appDataBase.setDatabaseCreated()
                        }
                    }
                })
                .build()
        }

        private fun addDelay() {
            Thread.sleep(4 * 1000)
        }
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATA_BASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }
}