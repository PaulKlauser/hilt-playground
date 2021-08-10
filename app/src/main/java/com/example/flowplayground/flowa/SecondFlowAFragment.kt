package com.example.flowplayground.flowa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.flowplayground.R
import me.tatarka.injectedvmprovider.viewModels
import javax.inject.Inject
import javax.inject.Provider

class SecondFlowAFragment : Fragment() {

    @Inject
    lateinit var vmProvider: Provider<SecondFlowAViewModel>

    private val vm by viewModels { vmProvider.get() }

    private val flowVm by navGraphViewModels<FlowAViewModel>(R.id.flow_a)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flow_a_second, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowVm.flowAComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text).text = vm.text
    }

}