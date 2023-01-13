package com.example.plant4u_microthesis

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ActivityWarmButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warm_button)

        val languages = resources.getStringArray(R.array.warm_crops)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.txtCompletedW)
        autocompleteTV.setAdapter(arrayAdapter)

        val textView: TextView = findViewById<TextView>(R.id.textViewInfoW)
        val textView2: TextView = findViewById<TextView>(R.id.textViewInfoW2)
        val textView3: TextView = findViewById<TextView>(R.id.textViewInfoW3)
        val textView4: TextView = findViewById<TextView>(R.id.textViewInfoW4)
        val textView5: TextView = findViewById<TextView>(R.id.textViewInfoW5)


        autocompleteTV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent.getItemAtPosition(position).toString()
                if (item == "Tomato")
                {
                    textView.text =
                        "Tomato: \n -An annual fruit crop \n -Popular to grow in New Jersey"
                    textView2.text =
                        "Ideal Environment: \n -Requires 8 hours of direct sun \n -Well-drained, potting mix with good compost"
                    textView3.text = "Harvesting Time: \n -70 to 90 days"
                    textView4.text = "Planting Months: \n -Mid-May"
                    textView5.text = "Planting Distance: \n -24 to 36 inches apart"
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
                else
                {
                    textView.text = ""
                    textView2.text = ""
                    textView3.text = ""
                    textView4.text = ""
                    textView5.text = ""
                }
            }

        val buttonClick = findViewById<Button>(R.id.buttonBackW)
        buttonClick.setOnClickListener {
            finish()
        }
    }
}