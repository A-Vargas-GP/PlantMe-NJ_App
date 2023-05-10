package com.example.plantme_nj.ui.perennial_plants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.SQLiteHelper
import com.example.plantme_nj.R
import com.example.plantme_nj.ui.annual_plants.AnnualPlantsActivity
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity

class PerennialPlantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perennial_plants)

        val buttonClickBellPepper=findViewById<Button>(R.id.PlantPerennial1)
        val buttonClickChives=findViewById<Button>(R.id.PlantPerennial2)
        val buttonClickJalapeno=findViewById<Button>(R.id.PlantPerennial3)
        val buttonClickStrawberry=findViewById<Button>(R.id.PlantPerennial4)

        buttonClickBellPepper.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Bell Pepper")
            startActivity(intent)
        }

        buttonClickChives.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Chives")
            startActivity(intent)
        }

        buttonClickJalapeno.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Jalapeño")
            startActivity(intent)
        }

        buttonClickStrawberry.setOnClickListener {
            val intent = Intent(this, IndivPlantActivity::class.java)
            intent.putExtra("Plant", "Strawberry")
            startActivity(intent)
        }

        //Back Button
        val buttonClick = findViewById<ImageButton>(R.id.returnButton)
        buttonClick.setOnClickListener {
            finish()
        }

    }
}