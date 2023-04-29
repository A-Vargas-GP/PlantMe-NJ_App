package com.example.plantme_nj.ui.annual_plants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.plantme_nj.R
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity

class AnnualPlantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annual_plants)

        val buttonClickLettuce=findViewById<Button>(R.id.PlantAnnual1)
        val buttonClickRadish=findViewById<Button>(R.id.PlantAnnual2)
        val buttonClickTomato=findViewById<Button>(R.id.PlantAnnual3)
        val buttonClickWatermelon=findViewById<Button>(R.id.PlantAnnual4)

        buttonClickLettuce.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Lettuce")
            startActivity(intent)
        }

        buttonClickRadish.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Radish")
            startActivity(intent)
        }

        buttonClickTomato.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Tomato")
            startActivity(intent)
        }

        buttonClickWatermelon.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Watermelon")
            startActivity(intent)
        }
    }
}