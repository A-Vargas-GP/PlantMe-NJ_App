package com.example.plantme_nj.ui.plant_suggestion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlantSuggestionViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is the season page"
    }
    val text: LiveData<String> = _text
}