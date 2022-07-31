package com.example.android.homeowrk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android.homeowrk.adapters.FlowerListAdapter
import com.example.android.homeowrk.databinding.FragmentFlowerListBinding
import com.example.android.homeowrk.decorators.FlowerListItemDecorator
import com.example.android.homeowrk.viewmodels.FlowerListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowerListFragment : Fragment() {

    private var _binding: FragmentFlowerListBinding? = null

    private val binding get() = _binding!!

    private val vm: FlowerListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlowerListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        vm.flowers.observe(viewLifecycleOwner) {
            val adapter = FlowerListAdapter(it)

            val decorator = FlowerListItemDecorator()
            binding.flowerList.addItemDecoration(decorator)
            binding.flowerList.adapter = adapter
        }

        return root
    }
}