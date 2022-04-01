package com.example.mysafer.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mysafer.Adapter.PagerAdapter
import com.example.mysafer.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_manual.*


class ManualFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_manual, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = PagerAdapter(childFragmentManager)
        adapter.addFragment(SchoolSosFragment(), "학교폭력")
        adapter.addFragment(HomeSosFragment(),"가정폭력")
        adapter.addFragment(SexSosFragment(), "성폭력")
        adapter.addFragment(ExSosFragment(), "그 외")

        viewpager.adapter = adapter
        tabLayout.setupWithViewPager(viewpager)
    }
}




