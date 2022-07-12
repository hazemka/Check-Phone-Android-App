package com.example.checkphone



import android.content.Intent
import android.content.IntentFilter
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.checkphone.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val checkReceiver = CheckReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // register the actions
        if (Build.VERSION.SDK_INT >= 26){
            val intentFilter = IntentFilter()
            intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
            intentFilter.addAction(Intent.ACTION_HEADSET_PLUG)
            intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
            registerReceiver(checkReceiver,intentFilter)
        }
        // to attach between fragments and viewPager
        // i created the TabLayoutAdapter to view the fragment in viewPager
        val adapter = TabLayoutAdapter(this)
        binding.viewPager.adapter = adapter
        // to attach between tabLayout and viewPager and display names of fragments
        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            when (position) {
                0 -> tab.text = "Tests"
                1 -> tab.text = "Device Info"
            }
        }
        // to start attaching
        tabLayoutMediator.attach()
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(checkReceiver)
    }

}