package com.example.flowplayground.list

import android.view.View
import android.widget.TextView
import com.example.flowplayground.R
import com.example.flowplayground.baseadapter.ViewHolderPresenter

class TodoPresenter(view: View) : ViewHolderPresenter<DetailedTodo> {

    private val title : TextView = view.findViewById(R.id.title)

    override fun bind(data: DetailedTodo) {
        title.text = data.title
    }
}