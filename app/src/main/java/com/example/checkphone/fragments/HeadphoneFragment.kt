package com.example.checkphone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checkphone.ActivityTests
import com.example.checkphone.CheckReceiver
import com.example.checkphone.R
import com.example.checkphone.databinding.FragmentHeadphoneBinding

class HeadphoneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHeadphoneBinding.inflate(inflater,container,false)
        binding.headItem.testIcon.setImageResource(R.drawable.ic_headphones)
        binding.headItem.iconName.text= "HeadPhones"
        binding.headItem.btnClick.text = "Check connection"
        binding.headItem.txtDescription.text ="Please connect headphones to device"
        binding.headItem.btnClick.setOnClickListener {
            check()
        }
        binding.headItem.btnSkip.setOnClickListener {
            (requireActivity() as ActivityTests).swipeFragment(SoundFragment())
        }
        return binding.root
    }

    private fun check(){
        if (CheckReceiver.isConnectedHead){
            (requireActivity() as ActivityTests).alertDialogPass(requireActivity()
            ,SoundFragment(),"Headphones are Working Correctly")
        }else{
            (requireActivity() as ActivityTests).alertDialogFail(requireActivity()
            ,SoundFragment(),{check()},"Check that the headphones are connected well")
        }
    }
}