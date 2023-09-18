package com.android.calculatorimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var currentHeight: Int = 120
    private var currentWeight: Int = 60
    private var currentAge: Int = 20
    private val decimalFormat = DecimalFormat("#.##")

    private lateinit var cardViewMale: CardView
    private lateinit var cardViewFemale: CardView
    private lateinit var textViewHeight: TextView
    private lateinit var rangeSliderHeight: RangeSlider
    private lateinit var textViewWeight: TextView
    private lateinit var buttonRemoveWeight: FloatingActionButton
    private lateinit var buttonAddWeight: FloatingActionButton
    private lateinit var textViewAge: TextView
    private lateinit var buttonRemoveAge: FloatingActionButton
    private lateinit var buttonAddAge: FloatingActionButton
    private lateinit var buttonCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

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
        textViewWeight = findViewById(R.id.textViewWeight)
        buttonAddWeight = findViewById(R.id.buttonAddWeight)
        buttonRemoveWeight = findViewById(R.id.buttonRemoveWeight)
        textViewAge = findViewById(R.id.textViewAge)
        buttonRemoveAge = findViewById(R.id.buttonRemoveAge)
        buttonAddAge = findViewById(R.id.buttonAddAge)
        buttonCalculate = findViewById(R.id.buttonCalculate)
    }

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
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            textViewHeight.text = "$currentHeight cm"
        }
        buttonRemoveWeight.setOnClickListener {
            currentWeight--
            showText(textViewWeight, currentWeight, "kg")
        }
        buttonAddWeight.setOnClickListener {
            currentWeight++
            showText(textViewWeight, currentWeight, "kg")
        }
        buttonAddAge.setOnClickListener {
            currentAge++
            showText(textViewAge, currentAge, "")
        }
        buttonRemoveAge.setOnClickListener {
            currentAge--
            showText(textViewAge, currentAge, "")
        }
        buttonCalculate.setOnClickListener {
            val imc: Double = calculateIMC()
            navigateToResult(imc)
        }
    }

    private fun setCardViewSelected(cardView: CardView) {
        cardView.setCardBackgroundColor(
            ContextCompat.getColor(
                this, R.color.imc_color_component_selected
            )
        )
    }

    private fun setCardViewUnselected(cardView: CardView) {
        cardView.setCardBackgroundColor(
            ContextCompat.getColor(
                this, R.color.imc_color_component
            )
        )
    }

    private fun showText(textView: TextView, value: Int, units: String) {
        val textToShow = "$value $units"
        textView.text = textToShow
    }

    private fun calculateIMC(): Double {
        return try {
            val heightInMeters = currentHeight.toDouble() / 100
            val imc: Double = currentWeight / (heightInMeters * heightInMeters)
            decimalFormat.format(imc).toDouble()
        } catch (e: ArithmeticException) {
            0.0
        } catch (e: NumberFormatException) {
            0.0
        }
    }

    private fun navigateToResult(imc: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("IMC_RESULT", imc)
        startActivity(intent)
    }
}