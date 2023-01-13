package com.example.plant4u_microthesis

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AnnualButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annual_button)

        val languages = resources.getStringArray(R.array.annual_plants)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.txtCompleted)
        autocompleteTV.setAdapter(arrayAdapter)

        val textView: TextView = findViewById<TextView>(R.id.textViewInfoA)
        val textView2: TextView = findViewById<TextView>(R.id.textViewInfoA2)
        val textView3: TextView = findViewById<TextView>(R.id.textViewInfoA3)
        val textView4: TextView = findViewById<TextView>(R.id.textViewInfoA4)
        val textView5: TextView = findViewById<TextView>(R.id.textViewInfoA5)


        autocompleteTV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent.getItemAtPosition(position).toString()
                if (item == "Broccoli") {
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
                else if (item == "Corn")
                {
                    textView.text =
                        "Corn: \n -A complex annual crop \n -Best to grow directly outside"
                    textView2.text =
                        "Ideal Environment: \n -Lots of water \n -Moist, well-draining soil"
                    textView3.text = "Harvesting Time: \n -65 to 85 days"
                    textView4.text = "Planting Time: \n -late April to early June"
                    textView5.text = "Planting Distance: \n -2 inches deep \n -2 to 4 inches apart"
                }
                else if (item == "Tomato")
                {
                    textView.text =
                        "Tomato: \n -An annual fruit crop \n -Popular to grow in New Jersey"
                    textView2.text =
                        "Ideal Environment: \n -Requires 8 hours of direct sun \n -Well-drained, potting mix with good compost"
                    textView3.text = "Harvesting Time: \n -70 to 90 days"
                    textView4.text = "Planting Months: \n -Mid-May"
                    textView5.text = "Planting Distance: \n -24 to 36 inches apart"
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

        val buttonClick = findViewById<Button>(R.id.buttonBackAnnual)
        buttonClick.setOnClickListener {
            finish()
        }
    }
}