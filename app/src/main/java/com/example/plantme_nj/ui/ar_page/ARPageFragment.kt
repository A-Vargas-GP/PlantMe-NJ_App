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

        val buttonARTesting = view.findViewById<Button>(R.id.PlantAR1)
        buttonARTesting.setOnClickListener {
            val intent = Intent(activity, ARActivity::class.java)
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