package com.softinit.androidclipboardmonitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softinit.androidclipboardmonitor.service.ClipboardMonitorService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        startService(Intent(this, ClipboardMonitorService::class.java))
        finish()
    }
}
