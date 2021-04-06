package com.demo.demoaltp

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demo.demoaltp.databinding.LayoutLevelRunBinding

class LevelRun : Fragment() {
    private lateinit var binding: LayoutLevelRunBinding
    private var mPlayQuestion: MediaPlayer? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutLevelRunBinding.inflate(inflater, container, false)
        Handler().postDelayed({
            (activity as MainActivity).callBackGameIn()
        }, 3000)
        levelRun()
        return binding.root

    }

    private fun levelRun() {
        var levelRun = arguments?.getInt("LEVEL")
        when(levelRun){
            1-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques1)
                mPlayQuestion?.start()

            }
            2-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques2)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -70f

            }
            3-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques3)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -140f

            }
            4-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques4)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -210f
            }
            5-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques5)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -280f
            }
            6-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques6)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -350f
            }
            7-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques7)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -420f
            }
            8-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques8)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -490f
            }
            9-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques9)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -560f
            }
            10-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques10)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -630f
            }
            11-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques11)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -700f
            }
            12-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques12)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -770f
            }
            13-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques13)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -840f
            }
            14-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques14)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -910f
            }
            15-> {
                mPlayQuestion = MediaPlayer.create(context, R.raw.ques15)
                mPlayQuestion?.start()
                var anim = AnimationUtils.loadAnimation(context, R.anim.bg_select_level_run)
                binding.selectLevelRun.startAnimation(anim)
                binding.selectLevelRun.y = -1080f
            }
        }

    }


}