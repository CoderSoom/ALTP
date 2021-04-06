package com.demo.demoaltp

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.demo.demoaltp.databinding.LayoutLeverBinding
import kotlinx.android.synthetic.main.layout_lever.*
import kotlin.system.exitProcess

class LevelGame : Fragment(), View.OnClickListener {
    private lateinit var binding: LayoutLeverBinding
    var mPlay: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutLeverBinding.inflate(inflater, container, false)
        var anim = AnimationUtils.loadAnimation(context, R.anim.anim_startgame)
        anim.start()
        anomationSelect()
        runMusic()
        binding.start.setOnClickListener(this)
        return binding.root
    }

    private fun runMusic() {
        mPlay = MediaPlayer.create(context, R.raw.luatchoi)
        mPlay?.start()
        mPlay?.setOnCompletionListener {
            var dialog = AlertDialog.Builder(context!!)
            dialog.setTitle("Questions")
            dialog.setMessage("Bạn đã sẵn sàng?")
            dialog.setPositiveButton("Yes") { _, _ ->
                (activity as MainActivity).openGame()

            }
            dialog.setNegativeButton("No") { _, _ ->
                exitProcess(0)
            }
            dialog.show()


        }

    }

    var click = 0
    override fun onClick(v: View?) {
        click += 1
        when (v!!.id) {
            R.id.start -> if (click == 1) {
                mPlay?.stop()
                (activity as MainActivity).openGame()
            }
        }
    }

    private fun anomationSelect() {
        Handler().postDelayed({
            Handler().postDelayed({
                binding.selectLevel5.visibility = View.VISIBLE
            }, 0)
            Handler().postDelayed({
                binding.selectLevel5.visibility = View.GONE
                binding.selectLevel10.visibility = View.VISIBLE
            }, 500)
            Handler().postDelayed({
                binding.selectLevel10.visibility = View.GONE
                binding.selectLevel15.visibility = View.VISIBLE
            }, 1000)

        }, 5000)


    }


}