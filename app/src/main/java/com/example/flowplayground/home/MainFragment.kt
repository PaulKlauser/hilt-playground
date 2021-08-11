package com.example.flowplayground.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowplayground.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.flow_a_button).setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToFlowA())
        }
        view.findViewById<Button>(R.id.list_button).setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToListFragment())
        }
    }

}