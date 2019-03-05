package com.cango.basicdemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cango.basicdemo.db.entity.ProductEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/29
 *     desc   :
 * </pre>
 */
@Dao
interface ProductDao {
    @Query("select * from products")
    fun loadAllProducts(): LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductEntity>)

    @Query("select * from products where id = :productId")
    fun loadProduct(productId: Int): LiveData<ProductEntity>

    @Insert
    fun insertOther(productEntity: ProductEntity)
}