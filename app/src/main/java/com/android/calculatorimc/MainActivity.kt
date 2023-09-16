package com.android.calculatorimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var cardViewMale: CardView
    private lateinit var cardViewFemale: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        cardViewMale = findViewById(R.id.cardViewMale)
        cardViewFemale = findViewById(R.id.cardViewFemale)
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