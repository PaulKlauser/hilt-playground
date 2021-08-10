package com.example.flowplayground.flowa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.flowplayground.R
import me.tatarka.injectedvmprovider.viewModels
import javax.inject.Inject
import javax.inject.Provider

class FirstFlowAFragment : Fragment() {

    @Inject
    lateinit var vmProvider: Provider<FirstFlowAViewModel>

    private val vm by viewModels { vmProvider.get() }

    private val flowVm by navGraphViewModels<FlowAViewModel>(R.id.flow_a)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flow_a_first, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowVm.flowAComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.continue_button).setOnClickListener {
            findNavController().navigate(FirstFlowAFragmentDirections.actionFirstFlowAFragmentToSecondFlowAFragment())
        }
        view.findViewById<EditText>(R.id.edit_text).addTextChangedListener {
            vm.text = it.toString()
        }
    }

}
