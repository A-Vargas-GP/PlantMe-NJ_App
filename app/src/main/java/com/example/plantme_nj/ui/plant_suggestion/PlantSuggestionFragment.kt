package com.example.plantme_nj.ui.plant_suggestion

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import com.example.plantme_nj.R
import com.example.plantme_nj.databinding.FragmentArPageBinding
import com.example.plantme_nj.databinding.FragmentPlantSuggestionBinding
import com.example.plantme_nj.ui.plant_types_selected.PlantTypesSelectedActivity
import com.example.plantme_nj.ui.seasons.SeasonsSelectedActivity

class PlantSuggestionFragment : Fragment() {

    private var _binding: FragmentPlantSuggestionBinding? = null

    companion object {
        fun newInstance() = PlantSuggestionFragment()
    }

    private lateinit var viewModel: PlantSuggestionViewModel
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myWebView = view.findViewById<WebView>(R.id.webView)
        myWebView.loadUrl("https://6ul0oke4mye.typeform.com/to/NLjFCzwX")
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.javaScriptEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_suggestion, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}