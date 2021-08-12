package com.example.flowplayground.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.flowplayground.R
import com.example.flowplayground.app
import kotlinx.coroutines.flow.collect
import me.tatarka.injectedvmprovider.viewModels
import javax.inject.Inject
import javax.inject.Provider

class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: Provider<ListViewModel>

    private val vm by viewModels { viewModelProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app().appComponent.inject(this)
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