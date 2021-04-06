package com.demo.demoaltp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.demo.demoaltp.databinding.DialogAskBinding

import kotlin.random.Random
import kotlin.random.nextInt

class DialogAsk : DialogFragment{
    private var truecases: Int
    private var levels : Int
    constructor(truecase : Int, level: Int){
        this.truecases = truecase
        this.levels = level
    }




    private lateinit var  binding : DialogAskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAskBinding.inflate(inflater, container, false)

        var rdTruelv5 = (70..100).random()
        var rdTruelv10 = (50..80).random()
        var rdTruelv15 = (40..70).random()
        var listRdFalseLv5  = IntArray(3) { Random.nextInt(0..50) }.asList()
        var listRdFalseLv10  = IntArray(3) { Random.nextInt(30..50) }.asList()
        var listRdFalseLv15  = IntArray(3) { Random.nextInt(30..60) }.asList()

        var arrylist = ArrayList<BarEntry>()
        if (levels <=5){
            if (truecases==1) {
                arrylist.add(BarEntry(1f, rdTruelv5.toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv5[0].toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv5[1].toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv5[2].toFloat()))
            }
            if (truecases==2) {
                arrylist.add(BarEntry(1f, listRdFalseLv5[0].toFloat()))
                arrylist.add(BarEntry(2f, rdTruelv5.toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv5[1].toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv5[2].toFloat()))
            }
            if (truecases==3) {
                arrylist.add(BarEntry(1f, listRdFalseLv5[0].toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv5[1].toFloat()))
                arrylist.add(BarEntry(3f, rdTruelv5.toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv5[2].toFloat()))
            }
            if (truecases==4) {
                arrylist.add(BarEntry(1f, listRdFalseLv5[0].toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv5[1].toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv5[2].toFloat()))
                arrylist.add(BarEntry(4f, rdTruelv5.toFloat()))
            }
        }
        if (levels in 6..10){
            if (truecases==1) {
                arrylist.add(BarEntry(1f, rdTruelv10.toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv10[0].toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv10[1].toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv10[2].toFloat()))
            }
            if (truecases==2) {
                arrylist.add(BarEntry(1f, listRdFalseLv10[0].toFloat()))
                arrylist.add(BarEntry(2f, rdTruelv10.toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv10[1].toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv10[2].toFloat()))
            }
            if (truecases==3) {
                arrylist.add(BarEntry(1f, listRdFalseLv10[0].toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv10[1].toFloat()))
                arrylist.add(BarEntry(3f, rdTruelv10.toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv10[2].toFloat()))
            }
            if (truecases==4) {
                arrylist.add(BarEntry(1f, listRdFalseLv10[0].toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv10[1].toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv10[2].toFloat()))
                arrylist.add(BarEntry(4f, rdTruelv10.toFloat()))
            }
        }
        if (levels >10){
            if (truecases==1) {
                arrylist.add(BarEntry(1f, rdTruelv15.toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv15[0].toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv15[1].toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv15[2].toFloat()))
            }
            if (truecases==2) {
                arrylist.add(BarEntry(1f, listRdFalseLv15[0].toFloat()))
                arrylist.add(BarEntry(2f, rdTruelv15.toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv15[1].toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv15[2].toFloat()))
            }
            if (truecases==3) {
                arrylist.add(BarEntry(1f, listRdFalseLv15[0].toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv15[1].toFloat()))
                arrylist.add(BarEntry(3f, rdTruelv15.toFloat()))
                arrylist.add(BarEntry(4f, listRdFalseLv15[2].toFloat()))
            }
            if (truecases==4) {
                arrylist.add(BarEntry(1f, listRdFalseLv15[0].toFloat()))
                arrylist.add(BarEntry(2f, listRdFalseLv15[1].toFloat()))
                arrylist.add(BarEntry(3f, listRdFalseLv15[2].toFloat()))
                arrylist.add(BarEntry(4f, rdTruelv15.toFloat()))
            }
        }



        var barSet = BarDataSet(arrylist, "Người bình chọn")
        barSet.color = Color.BLUE
        barSet.valueTextColor = Color.BLACK
        barSet.valueTextSize = 16f
        var bardata = BarData(barSet)
        binding.barchart.apply {
            setFitBars(true)
            data = bardata
            description.text = "Hỏi ý kiến khán giả"
            animateY(100)

        }
        return binding.root
    }




}