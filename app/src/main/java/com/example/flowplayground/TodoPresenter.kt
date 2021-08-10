package com.example.flowplayground

import android.view.View
import android.widget.TextView
import com.example.flowplayground.baseadapter.ViewHolderPresenter

class TodoPresenter(view: View) : ViewHolderPresenter<DetailedTodo> {

    private val title : TextView = view.findViewById(R.id.title)

    override fun bind(data: DetailedTodo) {
        title.text = data.title
    }
}