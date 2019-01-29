package com.cango.basicdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
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
@Database(version = 1, entities = [ProductEntity::class, CommentEntity::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun commentDao(): CommentDao

}