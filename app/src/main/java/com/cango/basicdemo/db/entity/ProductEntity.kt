package com.cango.basicdemo.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/29
 *     desc   :
 * </pre>
 */
@Entity(tableName = "products")
class ProductEntity(@PrimaryKey var id: Int, var name: String, var description: String, var price: Int) {
    constructor(productEntity: ProductEntity) : this(
        productEntity.id,
        productEntity.name,
        productEntity.description,
        productEntity.price
    )
}