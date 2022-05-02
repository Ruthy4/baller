package com.example.footballfixtures.presentation.ui.competitions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.footballfixtures.R
import com.example.footballfixtures.data.remote.dto.Competition
import com.example.footballfixtures.databinding.FragmentCompetitionsBinding
import com.example.footballfixtures.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsFragment : Fragment() {
    private var _binding: FragmentCompetitionsBinding? = null
    val binding get() = _binding!!
    private val viewModel: CompetitionsViewModel by viewModels()
    lateinit var competitionsRVAdapter: CompetitionsRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCompetitions()

        competitionsRVAdapter = CompetitionsRVAdapter { competition ->
            val action = CompetitionsFragmentDirections.actionCompetitionsFragmentToCompetitionDetailsFragment(competition)
            findNavController().navigate(action)
        }


        viewModel.competitions.observe(viewLifecycleOwner) {

            when(it) {
                is Resource.Success -> {
                    val competitionsList: List<Competition>? = it.value.competitions
                    val competitionRv = binding.competitionsRv
                    competitionRv.adapter = competitionsRVAdapter
                    competitionsRVAdapter.submitList(competitionsList)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "An Error occured", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Im INNNNN", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}