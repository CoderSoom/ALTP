package com.demo.demoaltp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.demo.demoaltp.databinding.DialogCallBinding

class DialogCall : DialogFragment{
    var truecases :Int
    constructor(truecase: Int){
        this.truecases = truecase
    }
    private lateinit var binding : DialogCallBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCallBinding.inflate(inflater, container, false)
        when(truecases){
            1->  binding.anwer.text = "A"
            2->  binding.anwer.text = "B"
            3->  binding.anwer.text = "C"
            4->  binding.anwer.text = "D"
        }
        binding.anwer.text = truecases.toString()
        return binding.root
    }
}