package com.example.footballfixtures.presentation.ui.competitions.fixtures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballfixtures.R
import com.example.footballfixtures.databinding.FragmentFixturesBinding
import com.example.footballfixtures.databinding.FragmentTableBinding

class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFixturesBinding.inflate(inflater, container, false)
        return binding.root
    }


}