package com.example.mysafer.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mysafer.Fragment.*

class MainFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return ManualFragment()
            1 -> return MemberFragment()
            2 -> return RecordsFragment()
            3 -> return SettingsFragment()
            else -> {throw IllegalStateException("$position is illegal") }
        }
    }

    override fun getCount(): Int = fragmentCount // 자바에서는 { return fragmentCount }

}