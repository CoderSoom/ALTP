package com.demo.demoaltp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demo.demoaltp.databinding.InGameBinding
import kotlinx.android.synthetic.main.in_game.*
import kotlin.system.exitProcess


class InGameIn : Fragment(), View.OnClickListener {
    private var animChose: Animation? = null
    private lateinit var mPlay: MediaPlayer
    private var mPlayLevel5: MediaPlayer? = null
    private var mPlayLevel10: MediaPlayer? = null
    private var mPlayLevel15: MediaPlayer? = null
    private var mPlayAnwer: MediaPlayer? = null
    private var mPlayBackgroundOne: MediaPlayer? = null
    private var mPlayBackgroundTwo: MediaPlayer? = null
    private var mPlayBackgroundThree: MediaPlayer? = null
    private lateinit var database: DataManager
    lateinit var couttime: CountDownTime
    private lateinit var binding: InGameBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InGameBinding.inflate(inflater, container, false)
        createQuestionLevel("1")
        binding.questionsLevel.text = "Câu 1"
        binding.bonues.text = "200.000"
        addEvent()
        help()
        couttime = CountDownTime()
        couttime.execute()
        musicPlayGame(1)
        return binding.root
    }

    private fun musicPlayGame(levelMusic: Int) {

        when {
            levelMusic <= 5 -> {
                mPlayBackgroundOne = MediaPlayer.create(context, R.raw.background_music)
                mPlayBackgroundOne?.start()
                mPlayBackgroundOne?.isLooping = true
            }
            levelMusic <= 10 -> {
                if (mPlayBackgroundOne != null) {
                    mPlayBackgroundOne?.stop()
                    mPlayBackgroundOne?.release()
                }
                mPlayBackgroundTwo = MediaPlayer.create(context, R.raw.background_music_b)
                mPlayBackgroundTwo?.start()
                mPlayBackgroundTwo?.isLooping = true
            }
            levelMusic <= 15 -> {
                if (mPlayBackgroundTwo != null) {
                    mPlayBackgroundTwo?.stop()
                    mPlayBackgroundOne?.release()
                }
                mPlayBackgroundTwo?.stop()
                mPlayBackgroundThree = MediaPlayer.create(context, R.raw.background_music_c)
                mPlayBackgroundThree?.start()
                mPlayBackgroundThree?.isLooping = true
            }
        }
    }

    private fun addEvent() {
        binding.SugA.setOnClickListener(this)
        binding.SugB.setOnClickListener(this)
        binding.SugC.setOnClickListener(this)
        binding.SugD.setOnClickListener(this)
    }

//    inner class coutTime() {
//        var time = @SuppressLint("StaticFieldLeak")
//        object : AsyncTask<String, String, String>() {
//            override fun doInBackground(vararg p0: String?): String? {
//                for (i in 30 downTo 0) {
//                    SystemClock.sleep(1000)
//                    if (isCancelled) break
//                    publishProgress(i.toString())
//                }
//                return "0"
//            }
//
//            override fun onProgressUpdate(vararg values: String?) {
//                binding.couttime.text = values[0]
//                super.onProgressUpdate()
//
//            }
//
//        }
//
//    }

    inner class CountDownTime : AsyncTask<Void, Int, Int>() {
        override fun doInBackground(vararg params: Void?): Int? {
            if (!isCancelled) {
                for (i in 30 downTo 0) {
                    SystemClock.sleep(1000)
                    if (isCancelled) break
                    publishProgress(i)
                }
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            if (!isCancelled) {
                binding.couttime.text = values[0].toString()
            }
            super.onProgressUpdate(*values)
        }
    }


    private fun createQuestionLevel(level: String) {

        val sqlLevel =
            "select * from (select * from Question order by random()) GROUP by level order by level LIMIT $level"
        database = DataManager(activity as MainActivity)
        database.get15Question(sqlLevel)
        binding.questions.text = database.question
        binding.caseA.text = database.caseA
        binding.caseB.text = database.caseB
        binding.caseC.text = database.caseC
        binding.caseD.text = database.caseD

    }

    @SuppressLint("SetTextI18n")
    fun resetQuestion(level: String) {
        couttime = CountDownTime()
        couttime.execute()
        Handler().postDelayed({
            binding.questionsLevel.text = "Câu " + level
            when (level) {
                "2" -> {
                    binding.bonues.text = "400.000"
                }
                "3" -> {
                    binding.bonues.text = "600.000"
                }
                "4" -> {
                    binding.bonues.text = "800.000"
                }
                "5" -> {
                    binding.bonues.text = "1.000.000"
                }
            }
        }, 2000)
        createQuestionLevel(level)
        clearAnimations()

    }

    fun cancel() {
        couttime.cancel(true)
    }

    fun clearAnimations() {

        binding.choseA.clearAnimation()
        binding.choseB.clearAnimation()
        binding.choseC.clearAnimation()
        binding.choseD.clearAnimation()
        binding.chosetrueA.clearAnimation()
        binding.chosetrueB.clearAnimation()
        binding.chosetrueC.clearAnimation()
        binding.chosetrueD.clearAnimation()
        binding.chosefailA.clearAnimation()
        binding.chosefailB.clearAnimation()
        binding.chosefailC.clearAnimation()
        binding.chosefailD.clearAnimation()
        binding.choseA.visibility = View.GONE
        binding.choseB.visibility = View.GONE
        binding.choseC.visibility = View.GONE
        binding.choseD.visibility = View.GONE
        binding.chosefailA.visibility = View.GONE
        binding.chosefailB.visibility = View.GONE
        binding.chosefailC.visibility = View.GONE
        binding.chosefailD.visibility = View.GONE
        binding.chosetrueA.visibility = View.GONE
        binding.chosetrueB.visibility = View.GONE
        binding.chosetrueC.visibility = View.GONE
        binding.chosetrueD.visibility = View.GONE

    }

    var level = 1
    override fun onClick(v: View?) {
        level += 1
        var answer = database.truecase
        when (v!!.id) {
            R.id.SugA -> {
                couttime.cancel(true)
                binding.choseA.visibility = View.VISIBLE
                animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                choseA.startAnimation(animChose)
                checkEnable(false)
                mPlayAnwer = MediaPlayer.create(context, R.raw.ans_a)
                mPlayAnwer?.start()
                if (1 == answer) {
                    Handler().postDelayed({
                        if (mPlayAnwer != null) {
                            mPlayAnwer!!.stop()
                            mPlayAnwer!!.release()
                        }

                        binding.chosetrueA.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosetrueA.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.true_a)
                        mPlay.start()
                        mPlay.setOnCompletionListener {
                            checkLevelNext()
                        }

                    }, 5000)
                }
                if (2 == answer) {
                    Handler().postDelayed({
                        binding.chosefailB.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailB.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_b)
                        mPlay.start()
                        saveHighCore()
                    }, 5000)

                }
                if (3 == answer) {
                    Handler().postDelayed({
                        binding.chosefailC.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailC.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_c)
                        mPlay.start()
                        saveHighCore()
                    }, 5000)

                }


                if (4 == answer) {
                    Handler().postDelayed({
                        binding.chosefailD.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailD.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_d)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
            }

            R.id.SugB -> {
                couttime.cancel(true)
                binding.choseB.visibility = View.VISIBLE
                animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                choseB.startAnimation(animChose)
                checkEnable(false)
                mPlayAnwer = MediaPlayer.create(context, R.raw.ans_b)
                mPlayAnwer?.start()
                if (2 == answer) {

                    Handler().postDelayed({
                        if (mPlayAnwer != null) {
                            mPlayAnwer!!.stop()
                            mPlayAnwer!!.release()
                        }
                        binding.chosetrueB.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosetrueB.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.true_b)
                        mPlay?.start()
                        mPlay.setOnCompletionListener {
                            checkLevelNext()

                        }
                    }, 5000)
                }
                if (1 == answer) {

                    Handler().postDelayed({
                        binding.chosefailA.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailA.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_a)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
                if (3 == answer) {
                    Handler().postDelayed({
                        binding.chosefailC.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailC.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_c)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
                if (4 == answer) {
                    Handler().postDelayed({
                        binding.chosefailD.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailD.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_d)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
            }
            R.id.SugC -> {
                couttime.cancel(true)
                binding.choseC.visibility = View.VISIBLE
                animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                choseC.startAnimation(animChose)
                checkEnable(false)
                mPlayAnwer = MediaPlayer.create(context, R.raw.ans_c)
                mPlayAnwer?.start()

                if (3 == answer) {
                    Handler().postDelayed({
                        if (mPlayAnwer != null) {
                            mPlayAnwer!!.stop()
                            mPlayAnwer!!.release()
                        }
                        binding.chosetrueC.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosetrueC.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.true_c)
                        mPlay?.start()
                        mPlay.setOnCompletionListener {
                            checkLevelNext()

                        }
                    }, 5000)
                }
                if (1 == answer) {

                    Handler().postDelayed({
                        binding.chosefailA.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailA.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_a)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
                if (2 == answer) {

                    if (answer == 2) {
                        Handler().postDelayed({
                            binding.chosefailB.visibility = View.VISIBLE
                            clearAnimations()
                            animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                            chosefailB.startAnimation(animChose)
                            mPlay = MediaPlayer.create(context, R.raw.lose_b)
                            mPlay?.start()
                            saveHighCore()
                        }, 5000)

                    }
                }
                if (4 == answer) {

                    Handler().postDelayed({
                        binding.chosefailD.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailD.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_d)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
            }
            R.id.SugD -> {
                couttime.cancel(true)
                binding.choseD.visibility = View.VISIBLE
                animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                choseD.startAnimation(animChose)
                checkEnable(false)
                mPlayAnwer = MediaPlayer.create(context, R.raw.ans_d)
                mPlayAnwer?.start()
                if (4 == answer) {
                    Handler().postDelayed({
                        if (mPlayAnwer != null) {
                            mPlayAnwer!!.stop()
                            mPlayAnwer!!.release()
                        }
                        clearAnimations()
                        binding.chosetrueD.visibility = View.VISIBLE
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosetrueD.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.true_d2)
                        mPlay?.start()
                        mPlay.setOnCompletionListener {
                            checkLevelNext()


                        }
                    }, 5000)
                }
                if (1 == answer) {

                    Handler().postDelayed({
                        binding.chosefailA.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailA.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_a)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
                if (3 == answer) {

                    Handler().postDelayed({
                        binding.chosefailC.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailC.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_c)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }
                if (2 == answer) {
                    Handler().postDelayed({
                        binding.chosefailB.visibility = View.VISIBLE
                        clearAnimations()
                        animChose = AnimationUtils.loadAnimation(context, R.anim.bg_chose)
                        chosefailB.startAnimation(animChose)
                        mPlay = MediaPlayer.create(context, R.raw.lose_b)
                        mPlay?.start()
                        saveHighCore()
                    }, 5000)

                }

            }
        }
    }

    private fun checkEnable(isEnable: Boolean) {
        binding.SugA.isEnabled = isEnable
        binding.SugB.isEnabled = isEnable
        binding.SugC.isEnabled = isEnable
        binding.SugD.isEnabled = isEnable
    }

    private fun checkLevelNext() {
        var checkLevel = level - 1
        if (checkLevel == 5) {
            mPlayLevel5 = MediaPlayer.create(context, R.raw.chuc_mung_vuot_moc_01_0)
            mPlayLevel5?.start()
            mPlayLevel5?.setOnCompletionListener {
                (activity as MainActivity).openLevelRun(level)


            }
        } else if (checkLevel == 10) {
            mPlayLevel10 = MediaPlayer.create(context, R.raw.chuc_mung_vuot_moc_02_0)
            mPlayLevel10?.start()
            mPlayLevel10?.setOnCompletionListener {
                (activity as MainActivity).openLevelRun(level)
                musicPlayGame(level)
                resetQuestion(level.toString())
                checkEnable(true)
            }
        } else if (checkLevel == 15) {
            mPlayLevel15 = MediaPlayer.create(context, R.raw.best_player)
            mPlayLevel15?.start()
            mPlayLevel15?.setOnCompletionListener {
            }
        } else {
            (activity as MainActivity).openLevelRun(level)
            resetQuestion(level.toString())
            checkEnable(true)

        }

    }

    fun help() {
        var rd = (1..4).random()
        var rd2 = (1..4).random()
        while (rd == database.truecase || rd == rd2 || rd2 == database.truecase) {
            rd = (1..4).random()
            rd2 = (1..4).random()
        }
        binding.del5050.setOnClickListener {
            couttime.cancel(true)
            binding.del5050x.visibility = View.VISIBLE
            binding.del5050.isEnabled = false
            var mPlay5050: MediaPlayer? = null
            mPlay5050 = MediaPlayer.create(context, R.raw.sound5050)
            mPlay5050?.start()
            database.truecase.toString()
            Handler().postDelayed({
                when (rd) {
                    1 -> if (1 != database.truecase) {
                        binding.caseA.text = ""
                    }
                    2 -> if (2 != database.truecase) {
                        binding.caseB.text = ""
                    }
                    3 -> if (3 != database.truecase) {
                        binding.caseC.text = ""
                    }
                    4 -> if (4 != database.truecase) {
                        binding.caseD.text = ""
                    }
                }
                when (rd2) {
                    1 -> if (1 != database.truecase) {
                        binding.caseA.text = ""
                    }
                    2 -> if (2 != database.truecase) {
                        binding.caseB.text = ""
                    }
                    3 -> if (3 != database.truecase) {
                        binding.caseC.text = ""
                    }
                    4 -> if (4 != database.truecase) {
                        binding.caseD.text = ""
                    }
                }
            }, 2500)


        }
        binding.ask.setOnClickListener {
            binding.askx.visibility = View.VISIBLE
            binding.ask.isEnabled = false
            var dialog = DialogAsk(database.truecase!!, level)
            dialog.show(fragmentManager!!, null)
        }
        binding.call.setOnClickListener {
            binding.callx.visibility = View.VISIBLE
            binding.call.isEnabled = false
            var dialog = DialogCall(database.truecase!!)
            dialog.show(fragmentManager!!, null)
        }
        binding.next.setOnClickListener {
            binding.nextx.visibility = View.VISIBLE
            binding.next.isEnabled = false
            createQuestionLevel(level.toString())
        }


    }

    fun saveHighCore() {
        var dialog = DialogHighCore()
        dialog.show(fragmentManager!!, null)
    }

    override fun onPause() {
        mPlayBackgroundOne?.pause()
        mPlayBackgroundTwo?.pause()
        mPlayBackgroundThree?.pause()
        super.onPause()
    }

    override fun onResume() {
        mPlayBackgroundOne?.start()
        mPlayBackgroundTwo?.start()
        mPlayBackgroundThree?.start()
        super.onResume()
    }

}