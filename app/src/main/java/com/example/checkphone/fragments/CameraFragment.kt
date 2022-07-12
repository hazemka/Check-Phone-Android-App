package com.example.checkphone.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.checkphone.ActivityTests
import com.example.checkphone.R
import com.example.checkphone.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    val REQ_CAMERA = 100
    lateinit var binding :FragmentCameraBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCameraBinding.inflate(inflater,container,false)
        binding.camItem.testIcon.setImageResource(R.drawable.ic_camera)
        binding.camItem.iconName.text = "Camera"
        binding.camItem.txtDescription.text = "Please take a picture"
        binding.camItem.btnClick.text = "Take a picture"
        binding.camItem.btnClick.setOnClickListener {
            checkAndOpenCamera()
        }
        binding.camItem.btnSkip.setOnClickListener {
            (requireActivity() as ActivityTests).swipeFragment(FlashFragment())
        }
        return binding.root
    }

    private fun checkAndOpenCamera(){
        if (Build.VERSION.SDK_INT >=23){
            if (requireActivity().checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA) , REQ_CAMERA)
            }else{
                openCamera()
            }
        }else{
            openCamera()
        }
    }
    private fun openCamera(){
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i,REQ_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAMERA && resultCode == RESULT_OK && data !=null){
//            val bitmapImage = data.extras!!.get("data") as Bitmap
//           binding.camItem.image.setImageBitmap(bitmapImage)
//            binding.camItem.image.visibility = View.VISIBLE
            Handler().postDelayed(
                {(requireActivity() as ActivityTests).alertDialogConfirm("The camera works correctly"
                    ,"The camera doesn't work correctly",requireActivity(),FlashFragment()
                    , { checkAndOpenCamera() } ,"Is the camera image is good")
                }
                , 500
            )
        }else{
            Toast.makeText(requireContext(), "Please shot any picture", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera()
            }else{
                Toast.makeText(requireContext(), "Allow the permission to use this feature", Toast.LENGTH_SHORT).show()
            }
        }
    }

}