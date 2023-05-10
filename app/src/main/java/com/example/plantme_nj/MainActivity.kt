package com.example.plantme_nj

import android.Manifest
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
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.PersistableBundle
import android.provider.Contacts.SettingsColumns.KEY
import android.provider.Settings.NameValueTable.VALUE
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.plantme_nj.ui.home.HomeViewModel
import com.example.plantme_nj.ui.plant_individual.IndivPlantActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.json.JSONObject
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //weather url to get JSON
    var weather_url = ""

    //id for url
    var api_id = "83c4cdc851354aa0b97c251aa77a3c0f"

    lateinit var locationTextView: TextView
    lateinit var temperatureTextView: TextView
    lateinit var dateTimeTextView: TextView
    lateinit var conditionsTextView: TextView
    lateinit var sunriseTextView: TextView
    lateinit var sunsetTextView: TextView

    lateinit var location: String

    var fusedLocationClient: FusedLocationProviderClient?=null
    var currentLocation:Location?=null
    private lateinit var viewModel: HomeViewModel

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
                //Removed 'More' option in this current version
              //R.id.navigation_home, R.id.navigation_ar, R.id.navigation_plants, R.id.navigation_type_of_plant, R.id.navigation_more_options

                //Current Version:
                R.id.navigation_home, R.id.navigation_ar, R.id.navigation_plants, R.id.navigation_type_of_plant
            )
        )

        /*Commented out to remove upper nav bar*/
        //setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        locationTextView = findViewById<TextView>(R.id.location_text)
        temperatureTextView = findViewById<TextView>(R.id.temperature_text)
        conditionsTextView = findViewById<TextView>(R.id.condition_text)
        dateTimeTextView = findViewById<TextView>(R.id.dateTime_text)
        sunriseTextView = findViewById<TextView>(R.id.sunrise_text)
        sunsetTextView = findViewById<TextView>(R.id.sunset_text)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.e("lat", weather_url)
        obtainLocation()

        //DATABASE RELATED INFO BELOW:
        //Instantiate DB
        val dbHelper = SQLiteHelper(this)

        //Plant Variables:
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

        val plant6 = PlantModel(5, "Strawberry", "Cool", "Perennial", "Prefers well-drained and fairly rich soil", "Require as much sunlight exposure as possible", "Thrive in different containers like ground and raised beds and hanging baskets",
            "Tolerant to cold", "At least eight hours of sun", "Late February", "Late March", "12 to 18 inches apart",
            "1/4 inch deep", "1 to 2 inches of water", "Best to water in the morning", "60 to 90 days")

        val plant7 = PlantModel(6, "Tomato", "Warm", "Annual", "Sunny area", "Well drained soil, free from tree and shrub roots", "Sensitive to cold temperatures", "Increase nutrient content by adding crushed eggshells", "At least eight hours of direct sunlight",
            "Late February", "May to June", "1 inch deep", "1 inch deep", "1 inch of water a week", "Water once or twice daily depending on weather conditions", "70 days")

        val plant8 = PlantModel(7, "Watermelon", "Warm", "Annual", "Best in loamy and well-drained soil", "Require warm climate", "Need ample space for vines to spread and grow", "Must have consistent water supply",
            "At least eight hours of full sunlight", "Late March", "June", "24 to 36 inches apart", "1/2 to 1 inch deep", "1-2 inches per week", "Water daily during high temperatures", "70 to 100 days")

        //Insert the Plant variables into the database
        dbHelper.insertPlant(plant1)
        dbHelper.insertPlant(plant2)
        dbHelper.insertPlant(plant3)
        dbHelper.insertPlant(plant4)
        dbHelper.insertPlant(plant5)
        dbHelper.insertPlant(plant6)
        dbHelper.insertPlant(plant7)
        dbHelper.insertPlant(plant8)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        obtainLocation()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("location", location)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val mString = savedInstanceState.getString("location")
        Log.e("location", mString.toString())
        locationTextView.text = mString.toString()
    }

    //Meant to gather current location
    @SuppressLint("MissingPermission")
    fun obtainLocation()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1000)
        }

        val task = fusedLocationClient?.lastLocation
        task?.addOnSuccessListener {location ->
            if (location !=null)
            {
                this.currentLocation = location
            }
            weather_url = "https://api.weatherbit.io/v2.0/current?" + "lat=" + location?.latitude + "&lon=" + location?.longitude + "&key=" + api_id
            getTemp()
        }

//        fusedLocationClient?.lastLocation
//            ?.addOnSuccessListener { location: Location? ->
//                //get latitude and longitude
//                //create HTTP url
//                weather_url = "https://api.weatherbit.io/v2.0/current?" + "lat=" + location?.latitude + "&lon=" + location?.longitude + "&key=" + api_id
//                Log.e("lat", weather_url.toString())
//                getTemp()
//            }
    }

    //Receive the current temperature
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

                //Convert Celsius temp into Fahrenheit to two decimal places
                var currTemp = obj2.getDouble("temp")
                currTemp = (currTemp * (9.0/5.0)) + 32

                //Current Date and Time
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                val formatted = current.format(formatter)

                location = obj2.getString("city_name")
                // set the temperature and the city
                // name using getString() function

                locationTextView.text = obj2.getString("city_name")
                temperatureTextView.text = String.format("%.2f", currTemp) + " deg Fahrenheit"
                dateTimeTextView.text = formatted
                conditionsTextView.text = obj2.getJSONObject("weather").getString("description")

                //API is ahead by 4 hours
                val sunRise = obj2.getString("sunrise")
                var riseHour = sunRise.take(2).toInt()
                val riseMinutes = sunRise.takeLast(2)
                riseHour-=4
                if (riseHour < 0)
                    riseHour = 0
                sunriseTextView.text = "$riseHour:$riseMinutes am"

                //API is behind 8 hours
                val sunSet = obj2.getString("sunset")
                var setHour = sunSet.take(2).toInt()
                val setMinutes = sunSet.takeLast(2)
                setHour+=8
                sunsetTextView.text = "$setHour:$setMinutes pm"
            },
            // In case of any error
            { locationTextView.text = "That didn't work!" })
        queue.add(stringReq)
    }
}