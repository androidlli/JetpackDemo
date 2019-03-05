package com.cango.basicdemo.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cango.basicdemo.R
import com.cango.basicdemo.databinding.FragmentProductListBinding
import com.cango.basicdemo.db.entity.ProductEntity
import com.cango.basicdemo.viewmodel.ProductListViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class ProductListFragment : Fragment() {
    companion object {
        const val TAG = "ProductListViewModel"
    }

    lateinit var dataBinding: FragmentProductListBinding
    lateinit var mAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_list,
            container,
            false
        )
        mAdapter = ProductAdapter(object : ProductAdapter.ProductOnClickCallback {
            override fun onClick(product: ProductEntity) {
                (activity as MainActivity).show(productId = product.id)
            }
        })
        dataBinding.productList.layoutManager = LinearLayoutManager(context)
        dataBinding.productList.adapter = mAdapter
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val model = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        model.getProducts().observe(this,
            Observer<List<ProductEntity>> {
                if (it == null) {
                    dataBinding.isLoading = true
                } else {
                    dataBinding.isLoading = false
                    //更新列表
                    mAdapter.setProductList(it)
                }
                dataBinding.executePendingBindings()
            })
    }
}
