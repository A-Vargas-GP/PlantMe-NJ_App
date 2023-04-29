package com.example.plantme_nj.ui.seasons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.plantme_nj.R
import com.example.plantme_nj.ui.annual_plants.AnnualPlantsActivity
import com.example.plantme_nj.ui.cool_plants.CoolPlantsActivity
import com.example.plantme_nj.ui.perennial_plants.PerennialPlantsActivity
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity
import com.example.plantme_nj.ui.warm_plants.WarmPlantsActivity

class SeasonsSelectedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seasons_selected)

        val buttonClickCool=findViewById<Button>(R.id.cool_season)
        val buttonClickWarm=findViewById<Button>(R.id.warm_season)

        val buttonClickChives=findViewById<Button>(R.id.CoolPlant1)
        val buttonClickLettuce=findViewById<Button>(R.id.CoolPlant2)
        val buttonClickRadish=findViewById<Button>(R.id.CoolPlant3)
        val buttonClickStrawberry=findViewById<Button>(R.id.CoolPlant4)
        val buttonClickBellPepper=findViewById<Button>(R.id.WarmPlant1)
        val buttonClickJalapeno=findViewById<Button>(R.id.WarmPlant2)
        val buttonClickTomato=findViewById<Button>(R.id.WarmPlant3)
        val buttonClickWatermelon=findViewById<Button>(R.id.WarmPlant4)

        //Meant for Cool or Warm Page containing list of individual plants
        buttonClickCool.setOnClickListener {
            val intent = Intent(this, CoolPlantsActivity::class.java)
            startActivity(intent)
        }

        buttonClickWarm.setOnClickListener {
            val intent = Intent(this, WarmPlantsActivity::class.java)
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