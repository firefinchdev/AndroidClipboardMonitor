package com.softinit.androidclipboardmonitor.service

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.content.ClipData
import android.util.Log
import android.widget.Toast


/**
 * Monitors the {@link ClipboardManager} for changes and logs the text to a file.
 */
class ClipboardMonitorService: Service() {

    companion object {
        const val TAG = "Clipboard Manager"
        const val FILENAME = "clipboard-history.txt"
    }

    private lateinit var mHistoryFile: File
    private val mThreadPool: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var mClipboardManager: ClipboardManager

    override fun onCreate() {
        super.onCreate()
        mHistoryFile = File(getExternalFilesDir(null), FILENAME)
        mClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        mClipboardManager.addPrimaryClipChangedListener(mOnPrimaryClipChangedListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        mClipboardManager.removePrimaryClipChangedListener(mOnPrimaryClipChangedListener)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private val mOnPrimaryClipChangedListener: ClipboardManager.OnPrimaryClipChangedListener =
        ClipboardManager.OnPrimaryClipChangedListener {
            val clip = mClipboardManager.primaryClip!!
            Toast.makeText(baseContext, clip.getItemAt(0).text.toString(), Toast.LENGTH_LONG).show()
            Log.d("FUCK", clip.getItemAt(0).text.toString())
        }

}