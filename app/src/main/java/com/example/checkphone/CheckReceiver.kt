package com.example.checkphone


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class CheckReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            Intent.ACTION_POWER_CONNECTED -> {
                isConnectedCharger = true
            }
            Intent.ACTION_POWER_DISCONNECTED ->{
                isConnectedCharger = false
            }
            Intent.ACTION_HEADSET_PLUG ->{
                isConnectedHead  =  intent.getIntExtra("state",0) == 1
            }
        }
    }
    companion object{
        var isConnectedHead:Boolean = false
        var  isConnectedCharger = false
        fun getStatusOfHeadset():Boolean{
            return isConnectedHead
        }
        fun getPowerConnected():Boolean{
            return isConnectedCharger
        }
    }

}