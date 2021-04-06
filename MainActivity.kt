package com.demo.demoaltp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.demo.demoaltp.databinding.ActivityFragmentBinding
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.startgame.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fragment)
        init()
    }

    private fun init() {
        val manager = supportFragmentManager
        var tran = manager.beginTransaction()
        tran.add(R.id.fragment, StartGame(), StartGame::class.java.name)
        tran.addToBackStack(null)
        tran.commit()
    }

    fun openStartGame() {


        val manager = supportFragmentManager
        var tran = manager.beginTransaction()
        tran.addToBackStack(null)
        tran.replace(R.id.fragment, LevelGame(), LevelGame::class.java.name)



        ///

        tran.commit()
    }


    fun openGame() {
        val manager = supportFragmentManager
        var tran = manager.beginTransaction()
        tran.add(R.id.fragment, InGame(), InGameIn::class.java.name)
        tran.addToBackStack(null)
        tran.commit()
    }

    val manager = supportFragmentManager
    fun openLevelRun(level: Int) {
        val f = LevelRun()
        var tran = manager.beginTransaction()
        var bundle= Bundle()
        bundle.putInt("LEVEL",  level)
        f.arguments = bundle
        tran.add(R.id.fragment, f, LevelRun::class.java.name)
        tran.addToBackStack(null)
        tran.commit()
    }
    val managera = supportFragmentManager
    fun openGameIn() {
        var f = InGameIn()
        var tran = managera.beginTransaction()
        tran.add(R.id.fragment, f, InGameIn::class.java.name)
        tran.addToBackStack("INGAME")
        tran.commit()
    }
    fun callBackGameIn(){
        managera.popBackStack("INGAME", 0)
    }



}