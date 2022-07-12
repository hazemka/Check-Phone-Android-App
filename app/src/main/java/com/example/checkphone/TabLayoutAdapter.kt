package com.example.checkphone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.checkphone.fragments.DeviceInfoFragment
import com.example.checkphone.fragments.TestFragment

class TabLayoutAdapter(var fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = TestFragment()
            1 -> fragment = DeviceInfoFragment()
        }
        return fragment
    }
}