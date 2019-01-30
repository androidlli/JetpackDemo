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
class ProductEntity {
    @PrimaryKey var id: Int = 0
    lateinit var name: String
    lateinit var description: String
    var price: Int = 0

    constructor(productEntity: ProductEntity) {
        this.id = productEntity.id
        this.name = productEntity.name
        this.description = productEntity.description
        this.price = productEntity.price
    }
    constructor()
}