package com.example.plantme_nj.ui.cool_plants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.plantme_nj.R
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity

class CoolPlantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cool_plants)

        val buttonClickChives=findViewById<Button>(R.id.PlantCool1)
        val buttonClickLettuce=findViewById<Button>(R.id.PlantCool2)
        val buttonClickRadish=findViewById<Button>(R.id.PlantCool3)
        val buttonClickStrawberry=findViewById<Button>(R.id.PlantCool4)

        buttonClickChives.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Chives")
            startActivity(intent)
        }

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

        buttonClickStrawberry.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Strawberry")
            startActivity(intent)
        }
    }
}