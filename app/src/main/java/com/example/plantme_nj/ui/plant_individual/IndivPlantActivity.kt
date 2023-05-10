package com.example.plantme_nj.ui.plant_individual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.SQLiteHelper
import com.example.plantme_nj.R

class IndivPlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indiv_plant)

//         Instantiate DB
        val dbHelper = SQLiteHelper(this)
//
        val profileName = intent.getStringExtra("Plant").toString()

        val plantName: TextView = findViewById<TextView>(R.id.plant_individual_title)
        val plantImage: ImageView = findViewById<ImageView>(R.id.plantImage)
        val textSeasonDetail: TextView = findViewById<TextView>(R.id.plant_individual_season_detail)
        val textPlantTypeDetail: TextView = findViewById<TextView>(R.id.plant_individual_plant_type_detail)
        val textEnvironment1Detail: TextView = findViewById<TextView>(R.id.plant_individual_environment_1_detail)
        val textEnvironment2Detail: TextView = findViewById<TextView>(R.id.plant_individual_environment_2_detail)
        val textEnvironment3Detail: TextView = findViewById<TextView>(R.id.plant_individual_environment_3_detail)
        val textEnvironment4Detail: TextView = findViewById<TextView>(R.id.plant_individual_environment_4_detail)
        val textLightingDetail: TextView = findViewById<TextView>(R.id.plant_individual_lighting_detail)
        val textIndoorDetail: TextView = findViewById<TextView>(R.id.plant_individual_indoor_detail)
        val textOutdoorDetail: TextView = findViewById<TextView>(R.id.plant_individual_outdoor_detail)
        val textDistanceDetail: TextView = findViewById<TextView>(R.id.plant_individual_planting_distance_detail)
        val textDepthDetail: TextView = findViewById<TextView>(R.id.plant_individual_planting_distance_depth_detail)
        val textWatering1Detail: TextView = findViewById<TextView>(R.id.plant_individual_watering_time_1_detail)
        val textWatering2Detail: TextView = findViewById<TextView>(R.id.plant_individual_watering_time_2_detail)
        val textMaturityDetail: TextView = findViewById<TextView>(R.id.plant_individual_maturity_detail)

        plantName.text = profileName

        when (profileName) {
            "Bell Pepper" -> plantImage.setImageResource(R.drawable.bell_peppers_individual_page)
            "Chives" -> plantImage.setImageResource(R.drawable.chives_individual)
            "Jalapeño" -> plantImage.setImageResource(R.drawable.jalapenos_individual)
            "Lettuce" -> plantImage.setImageResource(R.drawable.lettuce_individual)
            "Radish" -> plantImage.setImageResource(R.drawable.radish_individual)
            "Strawberry" -> plantImage.setImageResource(R.drawable.strawberries_individual)
            "Tomato" -> plantImage.setImageResource(R.drawable.tomatoes_individual)
            "Watermelon" -> plantImage.setImageResource(R.drawable.watermelon_individual)
            else -> {
                plantImage.setImageResource(R.drawable.plantme_colored_final_inverted_export)
            }
        }

        textSeasonDetail.text = dbHelper.getCurrentPlantSeasonType(profileName)
        textPlantTypeDetail.text = dbHelper.getCurrentPlantPlantType(profileName)
        textEnvironment1Detail.text = dbHelper.getCurrentPlantEnvironment1(profileName)
        textEnvironment2Detail.text = dbHelper.getCurrentPlantEnvironment2(profileName)
        textEnvironment3Detail.text = dbHelper.getCurrentPlantEnvironment3(profileName)
        textEnvironment4Detail.text = dbHelper.getCurrentPlantEnvironment4(profileName)
        textLightingDetail.text = dbHelper.getCurrentPlantLightingTime(profileName)
        textIndoorDetail.text = dbHelper.getCurrentPlantIndoorTime(profileName)
        textOutdoorDetail.text = dbHelper.getCurrentPlantOutdoorTime(profileName)
        textDistanceDetail.text = dbHelper.getCurrentPlantDistance(profileName)
        textDepthDetail.text = dbHelper.getCurrentPlantDistanceDepth(profileName)
        textWatering1Detail.text = dbHelper.getCurrentPlantWater1(profileName)
        textWatering2Detail.text = dbHelper.getCurrentPlantWater2(profileName)
        textMaturityDetail.text = dbHelper.getCurrentPlantMaturity(profileName)

        val buttonClick = findViewById<ImageButton>(R.id.returnButton)
        buttonClick.setOnClickListener {
            finish()
        }
    }
}