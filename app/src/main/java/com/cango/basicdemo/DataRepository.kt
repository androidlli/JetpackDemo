package com.cango.basicdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cango.basicdemo.db.AppDataBase
import com.cango.basicdemo.db.entity.CommentEntity
import com.cango.basicdemo.db.entity.ProductEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/01/30
 *     desc   :
 * </pre>
 */
class DataRepository private constructor(private val mDataBase: AppDataBase) {
    private val mObservableProducts: MediatorLiveData<List<ProductEntity>> = MediatorLiveData()
    val products: LiveData<List<ProductEntity>>
        get() = mObservableProducts

    init {
        mObservableProducts.addSource(
            mDataBase.productDao().loadAllProducts()
        ) { productEntities -> mObservableProducts.postValue(productEntities) }
    }

    companion object {
        private var mInstance: DataRepository? = null
        fun getInstance(appDataBase: AppDataBase): DataRepository? {
            if (null == mInstance) {
                mInstance = DataRepository(appDataBase)
            }
            return mInstance
        }
    }

    fun loadProduct(productId: Int): LiveData<ProductEntity> {
        return mDataBase.productDao().loadProduct(productId)
    }

    fun loadComments(productId: Int): LiveData<List<CommentEntity>> {
        return mDataBase.commentDao().loadComments(productId)
    }
}