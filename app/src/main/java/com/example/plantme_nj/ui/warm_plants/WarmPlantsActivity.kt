package com.example.plantme_nj.ui.warm_plants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.plantme_nj.R
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity

class WarmPlantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warm_plants)

        val buttonClickBellPepper=findViewById<Button>(R.id.PlantWarm1)
        val buttonClickJalapeno=findViewById<Button>(R.id.PlantWarm2)
        val buttonClickTomato=findViewById<Button>(R.id.PlantWarm3)
        val buttonClickWatermelon=findViewById<Button>(R.id.PlantWarm4)

        buttonClickBellPepper.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Bell Pepper")
            startActivity(intent)
        }

        buttonClickJalapeno.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Jalapeño")
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