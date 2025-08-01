package com.alex.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.task.R
import com.alex.task.databinding.FragmentHomeBinding
import com.alex.task.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()
    }

    private fun initTabs(){
        val pagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = pagerAdapter
        pagerAdapter.addFragment(TodoFragment(), R.string.status_task_todo)
        pagerAdapter.addFragment(TodoFragment(), R.string.status_task_doing)
        pagerAdapter.addFragment(TodoFragment(), R.string.status_task_done)
        
        binding.viewPager.offscreenPageLimit= pagerAdapter.itemCount
        
        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = getString(pagerAdapter.getTitle(position))
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}