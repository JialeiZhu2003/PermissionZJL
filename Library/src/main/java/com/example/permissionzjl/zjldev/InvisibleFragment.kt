package com.example.permissionzjl.zjldev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment


//对运行时权限的API进行封装

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment  : Fragment(){

    private var callback : PermissionCallback? = null

    fun requestNow(callback: PermissionCallback, vararg permissions: String) {
        this.callback = callback
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1){
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }

}