package com.cango.basicdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cango.basicdemo.db.entity.CommentEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/29
 *     desc   :
 * </pre>
 */
@Dao
interface CommentDao {
    @Query("select * from comments where productId = :productId")
    fun loadComments(productId: Int): LiveData<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: List<CommentEntity>)
}