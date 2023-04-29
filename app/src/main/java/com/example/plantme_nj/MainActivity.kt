package com.example.plantme_nj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.PlantModel
import com.SQLiteHelper
import com.example.plantme_nj.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //weather url to get JSON
    var weather_url = ""

    //id for url
    var api_id = "83c4cdc851354aa0b97c251aa77a3c0f"

    private lateinit var textView: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_ar, R.id.navigation_plants, R.id.navigation_type_of_plant, R.id.navigation_more_options
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //VIEW RELATED CONTENT BELOW:
        textView = findViewById<TextView>(R.id.location_text)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.e("lat", weather_url)
        obtainLocation()

        //DATABASE RELATED INFO BELOW:
        //Instantiate DB
        val dbHelper = SQLiteHelper(this)

        val plant1 = PlantModel(0, "Bell Pepper", "Warm", "Perennial", "Sensitive to temperature",
        "Grows best at 70-80 degrees fahrenheit", "Require well-drained loamy soils", "Best in high-light conditions",
            "At least eight hours of sunlight", "Late February", "Mid-May to early June", "12-18 inches between in rows 3 feet apart",
            "1/2 to 1 inch deep", "Once/Twice per week during normal temperature days", "Every two or three days during heat waves", "70 to 80 days"
        )

        val plant2 = PlantModel(1, "Chives", "Cool", "Perennial", "Full sun and does best in moist conditions", "Fertile and well drained soil",
            "Requires more frequent watering but cannot tolerate standing water", "Tolerant of cold weather", "At least six to eight hours of sun",
        "Late February to early March", "Mid to Late March", "4 to 6 inches between each other", "1/4 inch deep", "Every day", "",
            "60 days"
        )

        val plant3 = PlantModel(2, "Jalapeño", "Warm", "Perennial", "Requires full sun", "Grows in temperatures from 70 to 90 degrees fahrenheit",
            "Should be sheltered from the wind", "Should not be over watered", "At least six hours of direct sunlight", "Late February", "Mid May",
            "12-18 inches between in rows 2 feet apart", "1/4 inch deep", "Twice a week", "May require more watering during heated days", "80 days"
        )

        val plant4 = PlantModel(3, "Lettuce", "Cool", "Annual", "Partially shaded", "Well fertilized, loose, and moist soil", "Will turn bitter in hot weather",
        "Only sown in the spring and fall", "4-5 hours", "Mid-March", "Mid April/May or late August/September", "8 inches apart in rows 12 inches apart",
            "1/8 to 1/4 inch deep", "Every other day", "", "50-70 days")

        val plant5 = PlantModel(4, "Radish", "Cool", "Annual", "Tolerant to cold weather", "Prefer full sun but grows well in shade", "Requires moist soil",
            "Matures quickly and is only sown in the spring and fall", "At least six hours of sun","Mid March", "Early April/May or late July/August", "1 inch apart in rows eight to twelve inches apart",
            "1/2 inch deep", "1 inch per week", "Light watering 2-3 times per week", "30 to 45 days")

        //val plant6 = PlantModel(5, "Strawberry", "Cool", "Perennial")

        //val plant7 = PlantModel(6, "Tomato", "Warm", "Annual", )

        //val plant8 = PlantModel(7, "Watermelon", "Warm", "Annual")
//
        dbHelper.insertPlant(plant1)
        dbHelper.insertPlant(plant2)
        dbHelper.insertPlant(plant3)
        dbHelper.insertPlant(plant4)
        dbHelper.insertPlant(plant5)
        //dbHelper.insertPlant(plant6)
        //dbHelper.insertPlant(plant7)
        //dbHelper.insertPlant(plant8)
    }

    @SuppressLint("MissingPermission")
    private fun obtainLocation()
    {
        Log.e("lat", "function")
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                //get latitude and longitude
                //create HTTP url
                weather_url = "https://api.weatherbit.io/v2.0/current?" + "lat=" + location?.latitude + "&lon=" + location?.longitude + "&key=" + api_id
                Log.e("lat", weather_url.toString())
                getTemp()
            }
    }

    @SuppressLint("SetTextI18n")
    fun getTemp()
    {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = weather_url
        Log.e("lat", url)

        // Request a string response
        // from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                Log.e("lat", response.toString())

                // get the JSON object
                val obj = JSONObject(response)

                // get the Array from obj of name - "data"
                val arr = obj.getJSONArray("data")
                Log.e("lat obj1", arr.toString())

                // get the JSON object from the
                // array at index position 0
                val obj2 = arr.getJSONObject(0)
                Log.e("lat obj2", obj2.toString())

                // set the temperature and the city
                // name using getString() function
                textView.text = obj2.getString("temp") + " deg Celsius in " + obj2.getString("city_name")
            },
            // In case of any error
            { textView.text = "That didn't work!" })
        queue.add(stringReq)
    }
}