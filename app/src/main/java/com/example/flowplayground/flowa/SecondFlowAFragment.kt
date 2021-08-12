package com.example.flowplayground.flowa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import com.example.flowplayground.R
import com.example.flowplayground.util.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SecondFlowAFragment : Fragment() {

    @Inject
    lateinit var vmFactory: SecondFlowAViewModel.Factory

    private val flowVm by hiltNavGraphViewModels<FlowAViewModel>(R.id.flow_a)

    private val vm: SecondFlowAViewModel by viewModels {
        vmFactory.create(
            flowVm.flowARepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flow_a_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.text)
        lifecycleScope.launchWhenStarted {
            vm.text.collect {
                textView.text = it
            }
        }

    }

}