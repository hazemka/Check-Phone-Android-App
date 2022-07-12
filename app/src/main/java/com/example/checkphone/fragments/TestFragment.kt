package com.example.checkphone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.checkphone.R
import com.example.checkphone.adapter.FeatureAdapter
import com.example.checkphone.databinding.FragmentTestBinding
import com.example.checkphone.model.Feature

class TestFragment : Fragment() {
    companion object{
        val ID_WIFI = 0
        val ID_Bluetooth = 1
        val ID_CHARGING = 2
        val ID_VIBRATION = 3
        val ID_CAMERA = 4
        val ID_FLASH = 5
        val ID_HEADPHONE = 6
        val ID_SOUND = 7
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTestBinding.inflate(inflater,container,false)
        val data = ArrayList<Feature>()
        data.add(Feature(ID_WIFI,"Wifi", R.drawable.ic_wifi))
        data.add(Feature(ID_Bluetooth,"Bluetooth", R.drawable.ic_bluetooth))
        data.add(Feature(ID_CHARGING,"Charging",R.drawable.ic_charge))
        data.add(Feature(ID_VIBRATION,"Vibration",R.drawable.ic_vibration))
        data.add(Feature(ID_CAMERA,"Camera", R.drawable.ic_camera))
        data.add(Feature(ID_FLASH,"Flash", R.drawable.ic_flash))
        data.add(Feature(ID_HEADPHONE,"Headphone", R.drawable.ic_headphones))
        data.add(Feature(ID_SOUND,"Sound",R.drawable.ic_sound))

        binding.rvData.layoutManager = GridLayoutManager(requireContext(),3)

        val featureAdapter = FeatureAdapter(requireActivity(),data)
        binding.rvData.adapter = featureAdapter

        return binding.root
    }
}