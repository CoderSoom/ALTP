//package com.demo.demoaltp
//
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.media.AudioManager
//import android.media.MediaPlayer
//import android.net.Uri
//import android.os.AsyncTask
//import android.os.Bundle
//import android.os.Handler
//import android.os.SystemClock
//import android.view.animation.Animation
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.test.*
//import java.io.IOException
//
//
//class TestMedia : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.test)
//        var demoAsyncTask = coutTime()
//        demoAsyncTask.time.execute()
//        btn.setOnClickListener {
//            demoAsyncTask.time.cancel(true)
//        }
//    }
//
//    inner class coutTime : AsyncTask<Void, Int, Int>() {
//        override fun doInBackground(vararg params: Void?): Int? {
//            if (!isCancelled) {
//                for (i in 10 downTo 0) {
//                    SystemClock.sleep(1000)
//                    if (isCancelled) break
//                    onProgressUpdate(i)
//                }
//            }
//            return null
//        }
//
//        override fun onProgressUpdate(vararg values: Int?) {
//            if (!isCancelled)
//                tv_run.text = values[0].toString()
//            super.onProgressUpdate(*values)
//        }
//    }
//
//}
//
//
//
//
