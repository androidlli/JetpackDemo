package com.cango.basicdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cango.basicdemo.BasicApp
import com.cango.basicdemo.DataRepository
import com.cango.basicdemo.db.entity.CommentEntity
import com.cango.basicdemo.db.entity.ProductEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/03/05
 *     desc   :
 * </pre>
 */
class CommentViewModel(application: Application, productId: Int, dataRepository: DataRepository) : AndroidViewModel(application) {
    val mObservalbeProdcut = MediatorLiveData<ProductEntity>()
    val mObservalbeComments = MediatorLiveData<List<CommentEntity>>()
    init {
        mObservalbeProdcut.addSource(dataRepository.loadProduct(productId)) {
            mObservalbeProdcut.postValue(it)
        }
        mObservalbeComments.addSource(dataRepository.loadComments(productId)) {
            mObservalbeComments.postValue(it)
        }
    }
    class Fctory(val application: Application, val productId: Int) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CommentViewModel(application, productId, (application as BasicApp).getDataRepository()!!) as T
        }

    }
}