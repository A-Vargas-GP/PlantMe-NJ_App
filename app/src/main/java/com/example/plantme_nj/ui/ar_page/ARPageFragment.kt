package com.example.plantme_nj.ui.ar_page

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.plantme_nj.R
import com.example.plantme_nj.databinding.FragmentArPageBinding
import com.example.plantme_nj.databinding.FragmentHomeBinding
import com.example.plantme_nj.ui.ARView.ARActivity
import com.example.plantme_nj.ui.plant_types_selected.PlantTypesSelectedActivity
import com.example.plantme_nj.ui.seasons.SeasonsSelectedActivity

class ARPageFragment : Fragment() {

    private var _binding: FragmentArPageBinding? = null

    companion object {
        fun newInstance() = ARPageFragment()
    }

    private val binding get() = _binding!!
    private lateinit var viewModel: ArPageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Buttons
        val buttonAR1 = view.findViewById<Button>(R.id.PlantAR1)
        val buttonAR2 = view.findViewById<Button>(R.id.PlantAR2)
        val buttonAR3 = view.findViewById<Button>(R.id.PlantAR3)
        val buttonAR4 = view.findViewById<Button>(R.id.PlantAR4)
        val buttonAR5 = view.findViewById<Button>(R.id.PlantAR5)
        val buttonAR6 = view.findViewById<Button>(R.id.PlantAR6)
        val buttonAR7 = view.findViewById<Button>(R.id.PlantAR7)
        val buttonAR8 = view.findViewById<Button>(R.id.PlantAR8)

        buttonAR1.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Bell Pepper")
            startActivity(intent)
        }

        buttonAR2.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Chives")
            startActivity(intent)
        }

        buttonAR3.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Jalapeño")
            startActivity(intent)
        }

        buttonAR4.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Lettuce")
            startActivity(intent)
        }

        buttonAR5.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Radish")
            startActivity(intent)
        }

        buttonAR6.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Strawberry")
            startActivity(intent)
        }

        buttonAR7.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Tomato")
            startActivity(intent)
        }

        buttonAR8.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
            intent.putExtra("AR_Plant", "Watermelon")
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ar_page, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}