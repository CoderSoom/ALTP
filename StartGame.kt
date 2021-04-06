package com.demo.demoaltp

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.demo.demoaltp.databinding.LayoutLeverBinding
import com.demo.demoaltp.databinding.StartgameBinding
import kotlinx.android.synthetic.main.layout_lever.*
import kotlinx.android.synthetic.main.startgame.*


class StartGame : Fragment(), View.OnClickListener {
    private lateinit var binding : StartgameBinding
    var mediaPlayer: MediaPlayer? = null
    var mediaPlayerTouch: MediaPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartgameBinding.inflate(inflater, container, false)
        binding.playgame.setOnClickListener(this)
        var anim = AnimationUtils.loadAnimation(context, R.anim.bg_rotate)
        anim.fillAfter = true
        binding.bgCircle.startAnimation(anim)
        runMusic()
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.playgame -> {
                mediaPlayerTouch = MediaPlayer.create(context, R.raw.touch_sound)
                mediaPlayerTouch?.start()
                mediaPlayer?.stop()
                (activity as MainActivity).openStartGame()
            }
        }
    }

    private fun runMusic() {
        mediaPlayer = MediaPlayer.create(context, R.raw.bgmusic)
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        super.onDestroy()
    }


}