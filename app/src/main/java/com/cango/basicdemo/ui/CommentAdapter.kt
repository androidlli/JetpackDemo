package com.cango.basicdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cango.basicdemo.R
import com.cango.basicdemo.databinding.CommentItemBinding
import com.cango.basicdemo.db.entity.CommentEntity

/**
 * <pre>
 *     author : cango
 *     e-mail : lili92823@163.com
 *     time   : 2019/03/01
 *     desc   :
 * </pre>
 */
class CommentAdapter :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    var mCommentList = ArrayList<CommentEntity>()
    fun setComments(commentList: List<CommentEntity>) =
        if (mCommentList.isEmpty()) {
            mCommentList = commentList as ArrayList<CommentEntity>
            notifyItemRangeInserted(0, commentList.size)
        } else {
            //比较数据是否相同使用diffutil
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mCommentList[oldItemPosition].id == commentList[newItemPosition].id
                }

                override fun getOldListSize(): Int {
                    return mCommentList.size
                }

                override fun getNewListSize(): Int {
                    return commentList.size
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldComment = mCommentList[oldItemPosition]
                    val newComment = commentList[newItemPosition]
                    return oldComment.id == newComment.id && oldComment.text == newComment.text
                }
            })
            mCommentList = commentList as ArrayList<CommentEntity>
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding: CommentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.comment_item,
            parent, false
        )
        return CommentViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return mCommentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.itemBinding.comment = mCommentList[position]
        holder.itemBinding.executePendingBindings()
    }

    class CommentViewHolder(itemView: View, val itemBinding: CommentItemBinding) : RecyclerView.ViewHolder(itemView)

}