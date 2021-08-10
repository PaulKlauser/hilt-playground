package com.example.flowplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var todoRepository: TodoRepository
    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app().appComponent.inject(this)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val adapter = Adapter()
        recycler.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.getTodos().collect {
                if (it is Resource.Success) {
                    adapter.bind(it.newData)
                }
            }
        }
        viewModel.fetchTodos()
    }
}