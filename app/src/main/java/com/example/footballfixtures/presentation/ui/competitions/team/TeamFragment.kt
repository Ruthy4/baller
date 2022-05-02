package com.example.footballfixtures.presentation.ui.competitions.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballfixtures.R
import com.example.footballfixtures.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {
    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }
}