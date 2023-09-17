package com.android.calculatorimc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var currentHeight: Int = 120
    private lateinit var cardViewMale: CardView
    private lateinit var cardViewFemale: CardView
    private lateinit var textViewHeight: TextView
    private lateinit var rangeSliderHeight: RangeSlider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        cardViewMale = findViewById(R.id.cardViewMale)
        cardViewFemale = findViewById(R.id.cardViewFemale)
        textViewHeight = findViewById(R.id.textViewHeight)
        rangeSliderHeight = findViewById(R.id.rangeSliderHeight)
    }

    @SuppressLint("SetTextI18n")
    private fun initListeners() {
        cardViewMale.setOnClickListener {
            setCardViewSelected(cardViewMale)
            setCardViewUnselected(cardViewFemale)
        }
        cardViewFemale.setOnClickListener {
            setCardViewSelected(cardViewFemale)
            setCardViewUnselected(cardViewMale)
        }
        rangeSliderHeight.addOnChangeListener { _, value, _ ->
            val decimalFormat = DecimalFormat("#.##")
            val currentHeight = decimalFormat.format(value).toInt()
            textViewHeight.text ="$currentHeight cm"
        }
    }

    private fun setCardViewSelected(cardView: CardView) {
        cardView.setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.imc_color_component_selected
            )
        )
    }

    private fun setCardViewUnselected(cardView: CardView) {
        cardView.setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.imc_color_component
            )
        )
    }
}