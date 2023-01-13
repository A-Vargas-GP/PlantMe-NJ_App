package com.example.plant4u_microthesis.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plant4u_microthesis.ActivityCoolButton
import com.example.plant4u_microthesis.ActivityWarmButton
import com.example.plant4u_microthesis.R
import com.example.plant4u_microthesis.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonClick: TextView = view.findViewById<Button>(R.id.button_click_cool)
        buttonClick.setOnClickListener {
            val intent = Intent(context, ActivityCoolButton::class.java)
            startActivity(intent)
        }

        val buttonClicked: TextView = view.findViewById<Button>(R.id.button_click_warm)
        buttonClicked.setOnClickListener {
            val intent = Intent(context, ActivityWarmButton::class.java)
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
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}