package com.demo.demoaltp

import android.content.Intent
import android.database.DatabaseUtils
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.demo.demoaltp.databinding.InGameBinding

class InGame : Fragment() {
    var mediaPlayer: MediaPlayer? = null
    private lateinit var binding: InGameBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InGameBinding.inflate(inflater, container, false)
        mediaPlayer = MediaPlayer.create(context, R.raw.gofind)
        mediaPlayer?.start()
        loadQuestions()
        startLevelGame()
        return binding.root
    }

    fun loadQuestions() {
        binding.levelBonues.visibility = View.GONE
        binding.Sug.visibility = View.GONE
        binding.containCouttime.visibility = View.GONE
        binding.containQuestion.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        var animProgress = AnimationUtils.loadAnimation(context,  R.anim.bg_progress_bar)
        animProgress.fillAfter = true
        binding.progressBar.startAnimation(animProgress)
    }
    private fun startLevelGame(){
        Handler().postDelayed({
            (activity as MainActivity).openLevelRun(1)
        }, 7000)
        Handler().postDelayed({
            (activity as MainActivity).openGameIn()
        }, 8500)
    }




}