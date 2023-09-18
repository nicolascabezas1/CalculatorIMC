package com.android.calculatorimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.android.calculatorimc.MainActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {
    private lateinit var textViewDescriptionImc: TextView
    private lateinit var textViewImc: TextView
    private lateinit var textViewMessage: TextView
    private lateinit var buttonReCalculate: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val imc =intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponent()
        initListeners()
        initUi(imc)
    }
    private fun initComponent() {
        textViewImc = findViewById(R.id.textViewImc)
        textViewDescriptionImc = findViewById(R.id.textViewDescriptionImc)
        textViewMessage = findViewById(R.id.textViewMessage)
        buttonReCalculate = findViewById(R.id.buttonReCalculate)
    }
    private fun initListeners() {
        buttonReCalculate.setOnClickListener {
            onBackPressed()
        }
    }
    private fun initUi(result: Double) {
        textViewImc.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                textViewDescriptionImc.text = getString(R.string.description_low_weight)
                textViewMessage.text = getString(R.string.title_low_weight)
                textViewMessage.setTextColor(ContextCompat.getColor(this, R.color.category_peso_bajo))

            }
            in 18.51..24.99 -> {
                textViewDescriptionImc.text = getString((R.string.description_normal_weight))
                textViewMessage.text = getString(R.string.title_normal_weight)
                textViewMessage.setTextColor(ContextCompat.getColor(this, R.color.category_peso_normal))
            }
            in 25.00..29.99 -> {
                textViewDescriptionImc.text = getString(R.string.description_more_weight)
                textViewMessage.text = getString(R.string.title_more_weight)
                textViewMessage.setTextColor(ContextCompat.getColor(this, R.color.category_peso_sobrepeso))
            }
            in 30.00..99.00 -> {
                textViewDescriptionImc.text = getString(R.string.description_hig_weight)
                textViewMessage.text = getString(R.string.title_hig_weight)
                textViewMessage.setTextColor(ContextCompat.getColor(this, R.color.category_obesidad))
            }
            else -> {
                textViewMessage.text = getString(R.string.error)
                textViewDescriptionImc.text = getString(R.string.error)
            }
        }
    }
}