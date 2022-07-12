package com.example.checkphone.fragments

import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checkphone.ActivityTests
import com.example.checkphone.MainActivity
import com.example.checkphone.R
import com.example.checkphone.databinding.FragmentSoundBinding

class SoundFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSoundBinding.inflate(inflater,container,false)

        binding.sound.testIcon.setImageResource(R.drawable.ic_sound)
        binding.sound.txtDescription.text = "Click on button to check sound"
        binding.sound.iconName.text = "Sound"
        binding.sound.btnClick.text = "Check Sound"
        binding.sound.btnClick.setOnClickListener {
            checkSound()
        }
        binding.sound.btnSkip.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(),MainActivity::class.java))
        }
        return binding.root
    }

    private fun checkSound(){
            val mp = MediaPlayer.create(requireActivity(),R.raw.mpsound)
            mp.start()
        Handler().postDelayed(
            {(requireActivity() as ActivityTests).alertDialogConfirm("Sound works well",
                "Sound doesn't work well"
                ,requireActivity(),CameraFragment() , { checkSound() } ,"Do you hear the sound from the phone?")
                mp.pause()
            },
            3000
        )
    }


}