package com.example.flowplayground.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.flowplayground.*
import kotlinx.coroutines.flow.collect
import me.tatarka.injectedvmprovider.viewModels
import javax.inject.Inject
import javax.inject.Provider

class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: Provider<MainViewModel>

    private val vm by viewModels { viewModelProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_activity, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val adapter = Adapter()
        recycler.adapter = adapter

        lifecycleScope.launchWhenStarted {
            vm.getTodos().collect {
                if (it is Resource.Success) {
                    adapter.bind(it.newData)
                }
            }
        }
        vm.fetchTodos()
    }
}