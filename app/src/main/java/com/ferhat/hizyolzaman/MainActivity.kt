package com.ferhat.hizyolzaman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // CONSTRAINTS
        val maxHiz : Int = 25
        val maxYol : Int = 20

        // LINK UI
        var sbHiz : SeekBar = findViewById(R.id.sbHiz)
        var sbYol : SeekBar = findViewById(R.id.sbYol)

        var txtHiz : TextView = findViewById(R.id.txtHiz)
        var txtYol : TextView = findViewById(R.id.txtYol)
        var txtZaman : TextView = findViewById(R.id.txtZaman)

        var btnMaxHiz : Button = findViewById(R.id.btnMaxHiz)
        var btnMaxYol : Button = findViewById(R.id.btnMaxYol)
        var etxtMaxHiz : EditText = findViewById(R.id.etxtMaxHiz)
        var etxtMaxYol : EditText = findViewById(R.id.etxtMaxYol)

        // Default texts/values
        txtHiz.setText("Hiz: ${sbHiz.progress} km/h")
        txtYol.setText("Yol: ${sbYol.progress} km")
        etxtMaxHiz.setHint(maxHiz.toString())
        etxtMaxYol.setHint(maxYol.toString())
        sbHiz.max = maxHiz
        sbYol.max = maxYol

        sbHiz.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtHiz.text = "Hiz: $progress km/h"
                val hiz : Double = progress.toDouble()
                val yol : Double = sbYol.progress.toDouble()

                val zaman = (yol / hiz) * 60
                txtZaman.setText("Zaman: ${String.format("%.2f", zaman)} dakika")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbYol.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtYol.setText("Yol: $progress km")
                val yol : Double = progress.toDouble()
                val hiz : Double = sbHiz.progress.toDouble()

                val zaman : Double = (yol / hiz) * 60
                txtZaman.setText("Zaman: ${String.format("%.2f", zaman)} dakika")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        // Max deger degistir
        btnMaxHiz.setOnClickListener(View.OnClickListener {
            val theText = etxtMaxHiz.text.toString()
            val maxVal : Int = if (theText.length > 0) theText.toInt() else maxHiz
            sbHiz.max = maxVal
        })

        btnMaxYol.setOnClickListener(View.OnClickListener {
            val theText : String = etxtMaxYol.text.toString()
            val maxVal : Int = if (theText.length > 0) theText.toInt() else maxYol
            sbYol.max = maxVal
        })
    }

}