package com.example.footballfixtures.presentation.ui.competitions.table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballfixtures.R
import com.example.footballfixtures.databinding.FragmentTableBinding
import com.example.footballfixtures.databinding.FragmentTeamBinding

class TableFragment : Fragment() {

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

}