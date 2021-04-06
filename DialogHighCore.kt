package com.demo.demoaltp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.demo.demoaltp.databinding.DialogSaveHighCoreBinding
import kotlinx.android.synthetic.main.in_game.*

class DialogHighCore : DialogFragment(), View.OnClickListener {
    private lateinit var database: DataManager
    private lateinit var inGame : InGameIn
    private lateinit var binding: DialogSaveHighCoreBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = DataManager(context!!)
        inGame = InGameIn()
        binding = DialogSaveHighCoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onClick(v: View?) {

        database.createInsertTableHight()

        val name = binding.inputName.text.toString()
        val level = inGame.level
        val money = "10"
        database.insertHightSore(name, level, money)
        database.getAllHightScore()
    }

}