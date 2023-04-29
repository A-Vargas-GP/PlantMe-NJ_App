package com.example.plantme_nj.ui.plant_types_selected

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.plantme_nj.R
import com.example.plantme_nj.ui.annual_plants.AnnualPlantsActivity
import com.example.plantme_nj.ui.cool_plants.CoolPlantsActivity
import com.example.plantme_nj.ui.perennial_plants.PerennialPlantsActivity
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity
import com.example.plantme_nj.ui.warm_plants.WarmPlantsActivity

class PlantTypesSelectedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_types_selected)

        val buttonClickPerennial=findViewById<Button>(R.id.perennial_type)
        val buttonClickAnnual=findViewById<Button>(R.id.annual_type)

        val buttonClickBellPepper=findViewById<Button>(R.id.PerennialPlant1)
        val buttonClickChives=findViewById<Button>(R.id.PerennialPlant2)
        val buttonClickJalapeno=findViewById<Button>(R.id.PerennialPlant3)
        val buttonClickStrawberry=findViewById<Button>(R.id.PerennialPlant4)
        val buttonClickLettuce=findViewById<Button>(R.id.AnnualPlant1)
        val buttonClickRadish=findViewById<Button>(R.id.AnnualPlant2)
        val buttonClickTomato=findViewById<Button>(R.id.AnnualPlant3)
        val buttonClickWatermelon=findViewById<Button>(R.id.AnnualPlant4)

        //Meant for Cool or Warm Page containing list of individual plants
        buttonClickPerennial.setOnClickListener {
            val intent = Intent(this, PerennialPlantsActivity::class.java)
            startActivity(intent)
        }

        buttonClickAnnual.setOnClickListener {
            val intent = Intent(this, AnnualPlantsActivity::class.java)
            startActivity(intent)
        }

        //Meant for Individual Plants
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