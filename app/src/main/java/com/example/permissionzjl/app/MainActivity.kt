package com.example.permissionzjl.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.permissionzjl.app.databinding.ActivityMainBinding
import com.example.permissionzjl.zjldev.PermissionZ
import java.util.*
import android.Manifest   // ✅
import android.content.Intent
import android.net.Uri
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            PermissionZ.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call(){
        try{
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"))
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}