package com.example.flowplayground.list

import android.view.View
import com.example.flowplayground.R
import com.example.flowplayground.baseadapter.BaseAdapter
import com.example.flowplayground.baseadapter.Entry
import com.example.flowplayground.baseadapter.ViewHolderPresenter

class Adapter : BaseAdapter() {

    fun bind(todos: List<DetailedTodo>) {
        submitList(todos.map { Entry(R.layout.todo, it) })
    }

    override fun getPresenterForViewType(viewType: Int, itemView: View): ViewHolderPresenter<Any> {
        if (viewType == R.layout.todo) {
            //TODO: PK - figure out this generic deal?
            return TodoPresenter(itemView) as ViewHolderPresenter<Any>
        }
        throw IllegalArgumentException()
    }

}