package com.cango.basicdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cango.basicdemo.R
import com.cango.basicdemo.databinding.ProductItemBinding
import com.cango.basicdemo.db.entity.ProductEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/03/01
 *     desc   :
 * </pre>
 */
class ProductAdapter(private val mProductOnClick: ProductOnClickCallback) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var mProductList = ArrayList<ProductEntity>()
    fun setProductList(productList: List<ProductEntity>) =
        if (mProductList.isEmpty()) {
            mProductList = productList as ArrayList<ProductEntity>
            notifyItemRangeInserted(0, productList.size)
        } else {
            //比较数据是否相同使用diffutil
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return oldItemPosition == newItemPosition
                }

                override fun getOldListSize(): Int {
                    return mProductList.size
                }

                override fun getNewListSize(): Int {
                    return productList.size
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldProduct = mProductList[oldItemPosition]
                    val newProduct = productList[newItemPosition]
                    return oldProduct.id == newProduct.id && oldProduct.name == newProduct.name &&
                            oldProduct.price == newProduct.price && oldProduct.description == newProduct.description
                }
            })
            mProductList = productList as ArrayList<ProductEntity>
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_item,
            parent, false
        )
        binding.callback = mProductOnClick
        return ProductViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemBinding.product = mProductList[position]
        holder.itemBinding.executePendingBindings()
    }

    class ProductViewHolder(itemView: View, val itemBinding: ProductItemBinding) : RecyclerView.ViewHolder(itemView)

    interface ProductOnClickCallback {
        fun onClick(product: ProductEntity)
    }

}