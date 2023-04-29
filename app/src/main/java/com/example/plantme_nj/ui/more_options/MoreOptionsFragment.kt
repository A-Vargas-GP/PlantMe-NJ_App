package com.example.plantme_nj.ui.more_options

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plantme_nj.ActivityCoolButton
import com.example.plantme_nj.ActivityWarmButton
import com.example.plantme_nj.R

class MoreOptionsFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val buttonClick: TextView = view.findViewById<Button>(R.id.gallery)
//        buttonClick.setOnClickListener {
//            val intent = Intent(context, ActivityCoolButton::class.java)
//            startActivity(intent)
//        }
//
//        val buttonClicked: TextView = view.findViewById<Button>(R.id.last_viewed)
//        buttonClicked.setOnClickListener {
//            val intent = Intent(context, ActivityWarmButton::class.java)
//            startActivity(intent)
//        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more_options, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}