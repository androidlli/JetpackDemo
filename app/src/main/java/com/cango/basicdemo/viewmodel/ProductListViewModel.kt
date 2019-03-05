package com.cango.basicdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.cango.basicdemo.BasicApp
import com.cango.basicdemo.db.entity.ProductEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/02/26
 *     desc   :
 * </pre>
 */
class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    private val mObservableProducts: MediatorLiveData<List<ProductEntity>> = MediatorLiveData()

    init {
        //初始化后开始没有数据等待从数据库中取数据
        mObservableProducts.postValue(null)
        if (application is BasicApp) {
            mObservableProducts.addSource(application.getDataRepository()!!.products) {
                mObservableProducts.postValue(it)
            }
        }
    }

    fun getProducts() = mObservableProducts
}
