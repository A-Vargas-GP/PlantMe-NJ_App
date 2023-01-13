package com.example.plant4u_microthesis

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ActivityButton : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val languages = resources.getStringArray(R.array.perennial_plants)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.txtComplete)
        autocompleteTV.setAdapter(arrayAdapter)

        val textView: TextView = findViewById<TextView>(R.id.textViewInfo)
        val textView2: TextView = findViewById<TextView>(R.id.textViewInfo2)
        val textView3: TextView = findViewById<TextView>(R.id.textViewInfo3)
        val textView4: TextView = findViewById<TextView>(R.id.textViewInfo4)
        val textView5: TextView = findViewById<TextView>(R.id.textViewInfo5)


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
                    textView5.text = "Planting Distance: \n -Trench of 12-18 inches wide and 6 to 8 inches deep."
                }
                else if (item == "Strawberries") {
                    textView.text =
                        "Strawberries: \n -A perennial fruit crop \n -Thrives in a wide range of soil types"
                    textView2.text =
                        "Ideal Environment: \n -Good air drainage: \n     -Gentle slope or top of a hill\n" + "-Avoid low-lying areas."
                    textView3.text = "Harvesting Time: \n -May to June "
                    textView4.text = "Planting Months: \n -March to April"
                    textView5.text = "Planting Distance: \n -24 to 36 inches apart in rows 4 feet apart"
                }
                else if (item == "Horseradish")
                {
                    textView.text =
                        "Horseradish: \n -A hardy perennial crop \n -Needs little to no attention to grow"
                    textView2.text =
                        "Ideal Environment: \n -Moist, fertile soil with well-draining abilities"
                    textView3.text = "Harvesting Time: \n -late October/November"
                    textView4.text = "Planting Time: \n -Early Spring"
                    textView5.text = "Planting Distance: \n -3 inches deep \n -Spaced about 18 inches apart"
                }
                else if (item == "Blueberries")
                {
                    textView.text =
                        "Blueberries: \n -A perennial fruit crop \n -Easy fruit crop to grow"
                    textView2.text =
                        "Ideal Environment: \n -Wide range of soil types \n -Needs to be well-drained with high organic matter"
                    textView3.text = "Harvesting Time: \n -July to early August"
                    textView4.text = "Planting Months: \n -Early Spring"
                    textView5.text = "Planting Distance: \n -5 to 7 feet apart \n -Rows 8 to 10 feet apart"
                }
                else
                {
                    textView.text = ""
                    textView2.text = ""
                    textView3.text = ""
                    textView4.text = ""
                    textView5.text = ""
                }
            }

        val buttonClick = findViewById<Button>(R.id.buttonBack)
        buttonClick.setOnClickListener {
            finish()
        }
    }
}