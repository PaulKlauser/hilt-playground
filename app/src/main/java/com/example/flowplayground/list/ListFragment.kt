package com.example.flowplayground.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.flowplayground.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val vm: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val loading = view.findViewById<View>(R.id.loading)
        val error = view.findViewById<View>(R.id.error)
        val adapter = Adapter()
        recycler.adapter = adapter

        lifecycleScope.launchWhenStarted {
            vm.detailedTodos.collect {
                if (it == null) {
                    return@collect
                }
                adapter.bind(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.isLoading.collect {
                if (it) {
                    loading.visibility = View.VISIBLE
                } else {
                    loading.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.error.collect {
                if (it) {
                    error.visibility = View.VISIBLE
                } else {
                    error.visibility = View.GONE
                }
            }
        }

        vm.fetchTodos()
    }
}