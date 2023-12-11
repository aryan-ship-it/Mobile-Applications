package com.example.wordappdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordappdictionary.DictionaryApplication
import com.example.wordappdictionary.R
import com.example.wordappdictionary.databinding.FragmentWordDictionaryHomeBinding
import com.example.wordappdictionary.viewmodel.DictionaryViewModelFactory
import com.example.wordappdictionary.viewmodel.ViewModelDict
import com.example.wordappdictionary.viewmodel.ViewModelDictionary


class WordDictionaryHome : Fragment(){
    // View binding for the fragment
    private var _binding: FragmentWordDictionaryHomeBinding? = null
    private val binding get() = _binding!!

    // Shared ViewModel to communicate between fragments
    private val sharedViewModel: ViewModelDict by activityViewModels()
    private val wordsViewModel: ViewModelDictionary by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWordDictionaryHomeBinding.inflate(inflater,container,false)


        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.dictionaryHomeFragment = this
        val adapter = WordHomeAdapter(DictionaryHomeListener { word ->
            sharedViewModel.setWord(word)
            //navigate to definition
            findNavController().navigate(R.id.action_wordDictionaryHome_to_wordDefinitionFragment)
        })
        binding.recyclerView.adapter = adapter


        wordsViewModel.getAllWords.observe(viewLifecycleOwner) { words ->
            // Update the cached copy of the words in the adapter.

            words.let { adapter.submitList(it) }
        }


        binding.activeButton.setOnClickListener {

            wordsViewModel.getWordsActive.observe(viewLifecycleOwner) { words ->
                // Update the cached copy of the words in the adapter.
                words.let { adapter.submitList(it) }

            }
        }

        binding.inactiveButton.setOnClickListener {

            wordsViewModel.getWordsinactive.observe(viewLifecycleOwner) { words ->
                // Update the cached copy of the words in the adapter.
                words.let { adapter.submitList(it) }
            }
        }

        binding.showAllButton.setOnClickListener {

            wordsViewModel.getAllWords.observe(viewLifecycleOwner) { words ->
                // Update the cached copy of the words in the adapter.
                words.let { adapter.submitList(it) }
            }
        }
    }

    fun goToSearchWord(){

        findNavController().navigate(R.id.action_wordDictionaryHome_to_wordSearchFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}