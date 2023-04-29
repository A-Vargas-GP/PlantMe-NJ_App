package com.example.plantme_nj.ui.plant_types

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.SQLiteHelper
import com.PlantModel
import com.example.plantme_nj.R
import com.example.plantme_nj.databinding.FragmentPlantTypesBinding
import com.example.plantme_nj.ui.plant_types_selected.PlantTypesSelectedActivity
import com.example.plantme_nj.ui.seasons.SeasonsSelectedActivity

class PlantTypesFragment : Fragment() {

    private var _binding: FragmentPlantTypesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonPlantClick = view.findViewById<Button>(R.id.plantTypeButton)
        buttonPlantClick.setOnClickListener {
            val intent = Intent(activity, PlantTypesSelectedActivity::class.java)
            startActivity(intent)
        }

        val buttonSeasonClick = view.findViewById<Button>(R.id.seasonTypeButton)
        buttonSeasonClick.setOnClickListener {
            val intent = Intent(activity, SeasonsSelectedActivity::class.java)
            startActivity(intent)
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_types, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}