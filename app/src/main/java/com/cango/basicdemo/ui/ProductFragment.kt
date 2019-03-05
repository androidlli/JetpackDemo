package com.cango.basicdemo.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cango.basicdemo.R
import com.cango.basicdemo.databinding.FragmentProductBinding
import com.cango.basicdemo.db.entity.CommentEntity
import com.cango.basicdemo.db.entity.ProductEntity
import com.cango.basicdemo.viewmodel.CommentViewModel

class ProductFragment : Fragment() {
    lateinit var mBinding: FragmentProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fctory = CommentViewModel.Fctory(
            activity!!.application as Application,
            arguments!!.getInt(PRODUCT_ID)
        )
        val model = ViewModelProviders.of(this, fctory).get(CommentViewModel::class.java)
        subscribeToModel(model)
    }

    private fun subscribeToModel(model: CommentViewModel) {
        model.mObservalbeProdcut.observe(this,
            Observer<ProductEntity> {
                if (it != null) {
                    mBinding.product = it
                    mBinding.executePendingBindings()
                }
            })
        model.mObservalbeComments.observe(this,
            Observer<List<CommentEntity>> {

            })
    }

    companion object {
        const val PRODUCT_ID = "product-id"
        fun forPorduct(productId: Int): ProductFragment {
            val productFragment = ProductFragment()
            val bundle = Bundle()
            bundle.putInt(PRODUCT_ID, productId)
            productFragment.arguments = bundle
            return productFragment
        }
    }
}
