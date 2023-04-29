package com
import java.util.*

data class PlantModel(
    var id: Int,
    var name: String,
    var season_type: String,
    var plant_type: String,
    var environment_detail_1: String,
    var environment_detail_2: String,
    var environment_detail_3: String,
    var environment_detail_4: String,
    var lighting_time: String,
    var indoor_planting_time: String,
    var outdoor_planting_time: String,
    var plant_distance: String,
    var plant_distance_depth: String,
    var watering_time_detail_1: String,
    var watering_time_detail_2: String,
    var maturity: String
    )
{
    companion object{
        //if needed, but not really
        fun getAutoId():Int {
            val random = Random()
            return random.nextInt(30)
        }

        fun getName(plant: PlantModel):String {
            return plant.name
        }
    }
}