package com.example.checkphone.fragments


import android.os.BatteryManager
import android.os.BatteryManager.BATTERY_STATUS_CHARGING
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checkphone.ActivityTests
import com.example.checkphone.CheckReceiver
import com.example.checkphone.R
import com.example.checkphone.databinding.FragmentChargingBinding

class ChargingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentChargingBinding.inflate(inflater,container,false)
        binding.charItem.testIcon.setImageResource(R.drawable.ic_charge)
        binding.charItem.iconName.text = "Charging"
        binding.charItem.txtDescription.text = "Connect the charger to check"
        binding.charItem.btnClick.text = "Check Charging"
        binding.charItem.btnClick.setOnClickListener {
            checkCharging()
        }
        binding.charItem.btnSkip.setOnClickListener {
            (requireActivity() as ActivityTests).swipeFragment(VibrationFragment())
        }

        return binding.root
    }

    private fun checkCharging(){
        if ( CheckReceiver.isConnectedCharger  && BATTERY_STATUS_CHARGING == 2){
            (requireActivity() as ActivityTests).alertDialogPass(requireActivity(),VibrationFragment()
                ,"The Charging process works correctly")
        }else{
            (requireActivity() as ActivityTests).alertDialogFail(requireActivity(),VibrationFragment()
            ,{checkCharging()},"Make sure the charger is connected")
        }
    }
}