package com.example.permissionzjl.zjldev

import androidx.fragment.app.FragmentActivity

object PermissionZ {

    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment == null) {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        } else {
            existedFragment as InvisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }


}