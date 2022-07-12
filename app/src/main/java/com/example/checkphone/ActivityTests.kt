package com.example.checkphone

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.checkphone.databinding.ActivityTestsBinding
import com.example.checkphone.databinding.DialogConfirmBinding
import com.example.checkphone.databinding.DialogFailBinding
import com.example.checkphone.databinding.DialogPassBinding
import com.example.checkphone.fragments.*

class ActivityTests : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        when(intent.getIntExtra("id",-1)){
            TestFragment.ID_WIFI -> swipeFragment(WifiFragment())
            TestFragment.ID_Bluetooth -> swipeFragment(BluetoothFragment())
            TestFragment.ID_HEADPHONE -> swipeFragment(HeadphoneFragment())
            TestFragment.ID_CAMERA -> swipeFragment(CameraFragment())
            TestFragment.ID_VIBRATION -> swipeFragment(VibrationFragment())
            TestFragment.ID_CHARGING -> swipeFragment(ChargingFragment())
            TestFragment.ID_SOUND -> swipeFragment(SoundFragment())
            TestFragment.ID_FLASH -> swipeFragment(FlashFragment())
        }
    }

    fun swipeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,fragment)
            .commit()
    }

    fun alertDialogPass(activity: Activity,fragment: Fragment,txtDesc:String){
        val dialogBinding = DialogPassBinding.inflate(layoutInflater)
        val dialog = Dialog(activity)
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialogBinding.txtDesc.text = txtDesc
        dialogBinding.btnNext.setOnClickListener {
            swipeFragment(fragment)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun alertDialogFail(activity: Activity , fragmentTo: Fragment ,function: () -> (Unit) ,txtDesc: String){
        val dialogBinding = DialogFailBinding.inflate(layoutInflater)
        val dialog = Dialog(activity)
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialogBinding.txtDecription.text = txtDesc
        dialogBinding.btnNextTest.setOnClickListener {
            swipeFragment(fragmentTo)
            dialog.dismiss()
        }
        dialogBinding.btnRetry.setOnClickListener {
            function()
            dialog.dismiss()
        }
        dialog.show()
    }
    fun alertDialogConfirm(questionPass:String ,questionFail: String, activity: Activity, fragmentTo: Fragment,function: () -> (Unit), textConfirm:String){
        val dialogConfirmBinding = DialogConfirmBinding.inflate(layoutInflater)
        val dialog = Dialog(activity)
        dialog.setContentView(dialogConfirmBinding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialogConfirmBinding.txtQuestion.text = textConfirm
        dialogConfirmBinding.btnYes.setOnClickListener {
            alertDialogPass(activity,fragmentTo,questionPass)
            dialog.dismiss()
        }
        dialogConfirmBinding.btnNo.setOnClickListener {
            alertDialogFail(activity,fragmentTo,function,questionFail)
            dialog.dismiss()
        }
        dialog.show()
    }

}