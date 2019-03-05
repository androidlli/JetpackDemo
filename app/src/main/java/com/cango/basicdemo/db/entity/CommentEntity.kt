package com.cango.basicdemo.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/29
 *     desc   :
 * </pre>
 */
@Entity(
    tableName = "comments",
    foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = arrayOf("productId"))]
)
class CommentEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var productId: Int = 0
    lateinit var text: String
    lateinit var postAt: Date
}