package com

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLiteHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "plants.db"
        private const val TBL_PLANT = "tb1_plant"
        private const val ID = "id"
        private const val NAME = "name"
        private const val SEASON_TYPE = "season_type"
        private const val PLANT_TYPE = "plant_type"
        private const val ENVIRONMENT_1 = "environment_detail_1"
        private const val ENVIRONMENT_2 = "environment_detail_2"
        private const val ENVIRONMENT_3 = "environment_detail_3"
        private const val ENVIRONMENT_4 = "environment_detail_4"
        private const val LIGHTING_TIME = "lighting_time"
        private const val INDOOR_TIME = "indoor_planting_time"
        private const val OUTDOOR_TIME = "outdoor_planting_time"
        private const val PLANT_DISTANCE = "plant_distance"
        private const val PLANT_DEPTH = "plant_distance_depth"
        private const val WATER_DETAIL_1 = "watering_time_detail_1"
        private const val WATER_DETAIL_2 = "watering_time_detail_2"
        private const val MATURITY = "maturity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTb1Plants = ("CREATE TABLE " + TBL_PLANT + "(" + ID
                + " INTEGER PRIMARY KEY," + NAME + " TEXT," + SEASON_TYPE + " TEXT,"
                + PLANT_TYPE + " TEXT," + ENVIRONMENT_1 + " TEXT," + ENVIRONMENT_2 + " TEXT,"
                + ENVIRONMENT_3 + " TEXT," + ENVIRONMENT_4 + " TEXT," + LIGHTING_TIME + " TEXT,"
                + INDOOR_TIME + " TEXT," + OUTDOOR_TIME + " TEXT," + PLANT_DISTANCE + " TEXT," +
                PLANT_DEPTH + " TEXT," + WATER_DETAIL_1 + " TEXT," + WATER_DETAIL_2 + " TEXT," +
                MATURITY + " TEXT" + ")")

        db?.execSQL(createTb1Plants)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_PLANT")
        onCreate(db)
    }

    fun insertPlant(std: PlantModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(SEASON_TYPE, std.season_type)
        contentValues.put(PLANT_TYPE, std.plant_type)
        contentValues.put(ENVIRONMENT_1, std.environment_detail_1)
        contentValues.put(ENVIRONMENT_2, std.environment_detail_2)
        contentValues.put(ENVIRONMENT_3, std.environment_detail_3)
        contentValues.put(ENVIRONMENT_4, std.environment_detail_4)
        contentValues.put(LIGHTING_TIME, std.lighting_time)
        contentValues.put(INDOOR_TIME, std.indoor_planting_time)
        contentValues.put(OUTDOOR_TIME, std.outdoor_planting_time)
        contentValues.put(PLANT_DISTANCE, std.plant_distance)
        contentValues.put(PLANT_DEPTH, std.plant_distance_depth)
        contentValues.put(WATER_DETAIL_1, std.watering_time_detail_1)
        contentValues.put(WATER_DETAIL_2, std.watering_time_detail_2)
        contentValues.put(MATURITY, std.maturity)

        val success = db.insert(TBL_PLANT, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllPlants(): ArrayList<PlantModel>
    {
        val plantList: ArrayList<PlantModel> = ArrayList()

        val selectQuery = "SELECT * FROM $TBL_PLANT"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var seasonType: String
        var plantType: String
        var environmentDetail1: String
        var environmentDetail2: String
        var environmentDetail3: String
        var environmentDetail4: String
        var lightingTime: String
        var indoorPlantingTime: String
        var outdoorPlantingTime: String
        var plantDistance: String
        var plantDistanceDepth: String
        var wateringTimeDetail1: String
        var wateringTimeDetail2: String
        var maturity: String

        if (cursor.moveToFirst())
        {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                seasonType = cursor.getString(cursor.getColumnIndex("season_type"))
                plantType = cursor.getString(cursor.getColumnIndex("plant_type"))
                environmentDetail1 = cursor.getString(cursor.getColumnIndex("environment_detail_1"))
                environmentDetail2 = cursor.getString(cursor.getColumnIndex("environment_detail_2"))
                environmentDetail3 = cursor.getString(cursor.getColumnIndex("environment_detail_3"))
                environmentDetail4 = cursor.getString(cursor.getColumnIndex("environment_detail_4"))
                lightingTime = cursor.getString(cursor.getColumnIndex("lighting_time"))
                indoorPlantingTime = cursor.getString(cursor.getColumnIndex("indoor_planting_time"))
                outdoorPlantingTime = cursor.getString(cursor.getColumnIndex("outdoor_planting_time"))
                plantDistance = cursor.getString(cursor.getColumnIndex("plant_distance"))
                plantDistanceDepth = cursor.getString(cursor.getColumnIndex("plant_distance_depth"))
                wateringTimeDetail1 = cursor.getString(cursor.getColumnIndex("watering_time_detail_1"))
                wateringTimeDetail2 = cursor.getString(cursor.getColumnIndex("watering_time_detail_2"))
                maturity = cursor.getString(cursor.getColumnIndex("maturity"))

                val std = PlantModel(id = id, name = name, season_type = seasonType, plant_type = plantType,
                    environment_detail_1 = environmentDetail1, environment_detail_2 = environmentDetail2, environment_detail_3 = environmentDetail3,
                    environment_detail_4 = environmentDetail4, lighting_time = lightingTime, indoor_planting_time = indoorPlantingTime, outdoor_planting_time = outdoorPlantingTime,
                    plant_distance = plantDistance, plant_distance_depth = plantDistanceDepth, watering_time_detail_1 = wateringTimeDetail1, watering_time_detail_2 = wateringTimeDetail2,
                    maturity = maturity)

                plantList.add(std)
            } while (cursor.moveToNext())
        }

        return plantList
    }

    @SuppressLint("Range")
    fun getCurrentPlantSeasonType(currPlant: String): String
    {
        var seasonType: String = ""

        val selectSeasonQuery = "SELECT $SEASON_TYPE FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectSeasonQuery, null)
        if (cursor.moveToFirst())
        {
            seasonType = cursor.getString(cursor.getColumnIndex("season_type"))
        }
        db.close()
        return seasonType
    }

    @SuppressLint("Range")
    fun getCurrentPlantPlantType(currPlant: String): String
    {
        var plantType: String = ""

        val selectPlantTypeQuery = "SELECT $PLANT_TYPE FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectPlantTypeQuery, null)
        if (cursor.moveToFirst())
        {
            plantType = cursor.getString(cursor.getColumnIndex("plant_type"))
        }
        db.close()
        return plantType
    }

    @SuppressLint("Range")
    fun getCurrentPlantEnvironment1(currPlant: String): String
    {
        var environment1: String = ""

        val selectEnvironment1Query = "SELECT $ENVIRONMENT_1 FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectEnvironment1Query, null)
        if (cursor.moveToFirst())
        {
            environment1 = cursor.getString(cursor.getColumnIndex("environment_detail_1"))
        }
        db.close()
        return environment1
    }

    @SuppressLint("Range")
    fun getCurrentPlantEnvironment2(currPlant: String): String
    {
        var environment2: String = ""

        val selectEnvironment2Query = "SELECT $ENVIRONMENT_2 FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectEnvironment2Query, null)
        if (cursor.moveToFirst())
        {
            environment2 = cursor.getString(cursor.getColumnIndex("environment_detail_2"))
        }
        db.close()
        return environment2
    }

    @SuppressLint("Range")
    fun getCurrentPlantEnvironment3(currPlant: String): String
    {
        var environment3: String = ""

        val selectEnvironment3Query = "SELECT $ENVIRONMENT_3 FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectEnvironment3Query, null)
        if (cursor.moveToFirst())
        {
            environment3 = cursor.getString(cursor.getColumnIndex("environment_detail_3"))
        }
        db.close()
        return environment3
    }

    @SuppressLint("Range")
    fun getCurrentPlantEnvironment4(currPlant: String): String
    {
        var environment4: String = ""

        val selectEnvironment4Query = "SELECT $ENVIRONMENT_4 FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectEnvironment4Query, null)
        if (cursor.moveToFirst())
        {
            environment4 = cursor.getString(cursor.getColumnIndex("environment_detail_4"))
        }
        db.close()
        return environment4
    }

    @SuppressLint("Range")
    fun getCurrentPlantLightingTime(currPlant: String): String
    {
        var lightingTime: String = ""

        val selectLightingQuery = "SELECT $LIGHTING_TIME FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectLightingQuery, null)
        if (cursor.moveToFirst())
        {
            lightingTime = cursor.getString(cursor.getColumnIndex("lighting_time"))
        }
        db.close()
        return lightingTime
    }

    @SuppressLint("Range")
    fun getCurrentPlantIndoorTime(currPlant: String): String
    {
        var indoorTime: String = ""

        val selectIndoorQuery = "SELECT $INDOOR_TIME FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectIndoorQuery, null)
        if (cursor.moveToFirst())
        {
            indoorTime = cursor.getString(cursor.getColumnIndex("indoor_planting_time"))
        }
        db.close()
        return indoorTime
    }

    @SuppressLint("Range")
    fun getCurrentPlantOutdoorTime(currPlant: String): String
    {
        var outdoorTime: String = ""

        val selectOutdoorQuery = "SELECT $OUTDOOR_TIME FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectOutdoorQuery, null)
        if (cursor.moveToFirst())
        {
            outdoorTime = cursor.getString(cursor.getColumnIndex("outdoor_planting_time"))
        }
        db.close()
        return outdoorTime
    }

    @SuppressLint("Range")
    fun getCurrentPlantDistance(currPlant: String): String
    {
        var distance: String = ""

        val selectDistanceQuery = "SELECT $PLANT_DISTANCE FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectDistanceQuery, null)
        if (cursor.moveToFirst())
        {
            distance = cursor.getString(cursor.getColumnIndex("plant_distance"))
        }
        db.close()
        return distance
    }

    @SuppressLint("Range")
    fun getCurrentPlantDistanceDepth(currPlant: String): String
    {
        var distanceDepth: String = ""

        val selectDistanceDepthQuery = "SELECT $PLANT_DEPTH FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectDistanceDepthQuery, null)
        if (cursor.moveToFirst())
        {
            distanceDepth = cursor.getString(cursor.getColumnIndex("plant_distance_depth"))
        }
        db.close()
        return distanceDepth
    }

    @SuppressLint("Range")
    fun getCurrentPlantWater1(currPlant: String): String
    {
        var water1: String = ""

        val selectWater1Query = "SELECT $WATER_DETAIL_1 FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectWater1Query, null)
        if (cursor.moveToFirst())
        {
            water1 = cursor.getString(cursor.getColumnIndex("watering_time_detail_1"))
        }
        db.close()
        return water1
    }

    @SuppressLint("Range")
    fun getCurrentPlantWater2(currPlant: String): String
    {
        var water2: String = ""

        val selectWater2Query = "SELECT $WATER_DETAIL_2 FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectWater2Query, null)
        if (cursor.moveToFirst())
        {
            water2 = cursor.getString(cursor.getColumnIndex("watering_time_detail_2"))
        }
        db.close()
        return water2
    }

    @SuppressLint("Range")
    fun getCurrentPlantMaturity(currPlant: String): String
    {
        var maturity: String = ""

        val selectMaturityQuery = "SELECT $MATURITY FROM $TBL_PLANT WHERE $NAME = '$currPlant'"
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(selectMaturityQuery, null)
        if (cursor.moveToFirst())
        {
            maturity = cursor.getString(cursor.getColumnIndex("maturity"))
        }
        db.close()
        return maturity
    }
}