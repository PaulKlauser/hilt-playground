package com.example.flowplayground.baseadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter :
    ListAdapter<Entry, BaseAdapter.BaseViewHolder>( // Don't love this being up here
        object : DiffUtil.ItemCallback<Entry>() {
            override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
                return oldItem.data is HasId && newItem.data is HasId && oldItem.data.id == newItem.data.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
                return oldItem.data == newItem.data
            }

            override fun getChangePayload(oldItem: Entry, newItem: Entry): Any? {
                if (oldItem.data is HasChange<*> && newItem.data is HasChange<*>) {
                    return (newItem.data as HasChange<Any>).getChangePayload(oldItem.data)
                }
                return super.getChangePayload(oldItem, newItem)
            }
        }) {

    protected var entries: List<Entry> = ArrayList()

    override fun submitList(list: List<Entry>?) {
        submitList(list, null)
    }

    override fun submitList(list: List<Entry>?, commitCallback: Runnable?) {
        super.submitList(list) {
            commitCallback?.run()
            list?.apply {
                entries = list
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BaseViewHolder(itemView, getPresenterForViewType(viewType, itemView))
    }

    abstract fun getPresenterForViewType(viewType: Int, itemView: View): ViewHolderPresenter<Any>

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(entries[position].data, position)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        holder.bind(entries[position].data, position, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return entries[position].layout
    }


    class BaseViewHolder(itemView: View, private val presenter: ViewHolderPresenter<Any>) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(data: Any, position: Int) {
            presenter.bind(data, position)
        }

        fun bind(data: Any, position:Int, payloads: List<Any>) {
            presenter.bind(data, position, payloads)
        }
    }
}