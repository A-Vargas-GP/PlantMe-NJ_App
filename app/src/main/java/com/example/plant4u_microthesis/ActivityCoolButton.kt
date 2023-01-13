package com.example.plant4u_microthesis

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ActivityCoolButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cool_button)

        val languages = resources.getStringArray(R.array.cool_crops)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.txtCompletedC)
        autocompleteTV.setAdapter(arrayAdapter)

        val textView: TextView = findViewById<TextView>(R.id.textViewInfoC)
        val textView2: TextView = findViewById<TextView>(R.id.textViewInfoC2)
        val textView3: TextView = findViewById<TextView>(R.id.textViewInfoC3)
        val textView4: TextView = findViewById<TextView>(R.id.textViewInfoC4)
        val textView5: TextView = findViewById<TextView>(R.id.textViewInfoC5)


        autocompleteTV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent.getItemAtPosition(position).toString()
                if (item == "Asparagus") {
                    textView.text =
                        "Asparagus: \n -A perennial row crop \n -Produces ideal products 5 to 10 years after planting."
                    textView2.text =
                        "Ideal Environment: \n -Well-drained and heavier soil."
                    textView3.text = "Harvesting Time: \n -2 to 3 years"
                    textView4.text = "Planting Months: \n -April to May/early June"
                    textView5.text =
                        "Planting Distance: \n -Trench of 12-18 inches wide and 6 to 8 inches deep."
                }
                else if (item == "Blueberries") {
                    textView.text =
                        "Blueberries: \n -A perennial fruit crop \n -Easy fruit crop to grow"
                    textView2.text =
                        "Ideal Environment: \n -Wide range of soil types \n -Needs to be well-drained with high organic matter"
                    textView3.text = "Harvesting Time: \n -July to early August"
                    textView4.text = "Planting Months: \n -Early Spring"
                    textView5.text =
                        "Planting Distance: \n -5 to 7 feet apart \n -Rows 8 to 10 feet apart"
                }
                else if (item == "Broccoli") {
                    textView.text =
                        "Broccoli: \n -An annual vegetable crop \n -Sensitive to temperature."
                    textView2.text =
                        "Ideal Environment: \n -Fertile, well-drained, moist soil"
                    textView3.text = "Harvesting Time: \n -3 to 4 months"
                    textView4.text = "Planting Months: \n -Mid April"
                    textView5.text = "Planting Distance: \n -18 inches apart from each other"
                }
                else if (item == "Cabbage") {
                    textView.text =
                        "Cabbage: \n -An annual vegetable crop \n -Requires lots of nutrients and water"
                    textView2.text =
                        "Ideal Environment: \n Well-draining soil mixed with manure/compost"
                    textView3.text = "Harvesting Time: \n -60 to 70 days"
                    textView4.text = "Planting Months: \n -Early April"
                    textView5.text = "Planting Distance: \n -12 to 24 inches apart in rows"
                }
                else if (item == "Corn") {
                    textView.text =
                        "Corn: \n -A complex annual crop \n -Best to grow directly outside"
                    textView2.text =
                        "Ideal Environment: \n -Lots of water \n -Moist, well-draining soil"
                    textView3.text = "Harvesting Time: \n -65 to 85 days"
                    textView4.text = "Planting Time: \n -late April to early June"
                    textView5.text = "Planting Distance: \n -2 inches deep \n -2 to 4 inches apart"
                }
                else if (item == "Strawberries") {
                    textView.text =
                        "Strawberries: \n -A perennial fruit crop \n -Thrives in a wide range of soil types"
                    textView2.text =
                        "Ideal Environment: \n -Good air drainage: \n     -Gentle slope or top of a hill\n" + "-Avoid low-lying areas."
                    textView3.text = "Harvesting Time: \n -May to June "
                    textView4.text = "Planting Months: \n -March to April"
                    textView5.text =
                        "Planting Distance: \n -24 to 36 inches apart in rows 4 feet apart"
                }
                else {
                    textView.text = ""
                    textView2.text = ""
                    textView3.text = ""
                    textView4.text = ""
                    textView5.text = ""
                }
            }
        val buttonClick = findViewById<Button>(R.id.buttonBackC)
        buttonClick.setOnClickListener {
            finish()
        }
    }
}