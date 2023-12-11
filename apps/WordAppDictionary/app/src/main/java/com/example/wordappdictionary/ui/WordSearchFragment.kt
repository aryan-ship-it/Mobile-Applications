package com.example.wordappdictionary.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordappdictionary.R
import com.example.wordappdictionary.databinding.FragmentWordSearchBinding
import com.example.wordappdictionary.viewmodel.ViewModelDict



class WordSearchFragment : Fragment() {
    private var _binding: FragmentWordSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelDict by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        _binding = FragmentWordSearchBinding.inflate(inflater, container, false)

        Log.d("WordSearch","after inflate")
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            wordSearchFragment = this@WordSearchFragment
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.wordSearchFragment = this
        val adapter = SearchWordAdapter(WordListener { word ->
            viewModel.onWordClicked(word)
            // Navigate to the next destination to add a word
            findNavController().navigate(R.id.action_wordSearchFragment_to_wordAddFragment)

        })
        binding.recyclerView.adapter = adapter

        binding.searchWordButton.setOnClickListener{
            showResult()
        }

        viewModel.word.observe(viewLifecycleOwner) { word ->
            //observer for word exact match



        }

        viewModel.searchWords.observe(viewLifecycleOwner){ words ->
            words.let { adapter.submitList(it) }
            binding.recyclerView.visibility = View.VISIBLE

        }




    }

    override fun onResume() {
        super.onResume()
        viewModel.resetWordList()
    }

    fun showResult() {
        val typedText = binding.searchEditText.text.toString()
        viewModel.getWordResponse(typedText)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
