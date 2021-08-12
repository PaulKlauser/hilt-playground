package com.example.flowplayground.flowa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.example.flowplayground.R
import com.example.flowplayground.util.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirstFlowAFragment : Fragment() {

    @Inject
    lateinit var vmFactory: FirstFlowAViewModel.Factory

    private val flowVm by hiltNavGraphViewModels<FlowAViewModel>(R.id.flow_a)

    private val vm: FirstFlowAViewModel by viewModels {
        vmFactory.create(flowVm.flowARepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flow_a_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.continue_button).setOnClickListener {
            findNavController().navigate(FirstFlowAFragmentDirections.actionFirstFlowAFragmentToSecondFlowAFragment())
        }
        view.findViewById<EditText>(R.id.edit_text).addTextChangedListener {
            vm.setText(it.toString())
        }
    }

}
