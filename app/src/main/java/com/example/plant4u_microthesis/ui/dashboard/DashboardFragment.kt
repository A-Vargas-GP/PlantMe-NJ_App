package com.example.plant4u_microthesis.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plant4u_microthesis.ActivityButton
import com.example.plant4u_microthesis.AnnualButton
import com.example.plant4u_microthesis.R
import com.example.plant4u_microthesis.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonClick: TextView = view.findViewById<Button>(R.id.button_click)
        buttonClick.setOnClickListener {
            val intent = Intent(context, ActivityButton::class.java)
            startActivity(intent)
        }

        val buttonClicked: TextView = view.findViewById<Button>(R.id.button2_click)
        buttonClicked.setOnClickListener {
            val intent = Intent(context, AnnualButton::class.java)
            startActivity(intent)
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlant
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}