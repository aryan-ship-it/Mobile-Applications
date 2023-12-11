package com.example.wordappdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.wordappdictionary.DictionaryApplication
import com.example.wordappdictionary.R
import com.example.wordappdictionary.databinding.FragmentWordAddBinding
import com.example.wordappdictionary.viewmodel.DictionaryViewModelFactory
import com.example.wordappdictionary.viewmodel.ViewModelDict
import com.example.wordappdictionary.viewmodel.ViewModelDictionary


class WordAddFragment : Fragment() {

    private var _binding: FragmentWordAddBinding? = null
    private val binding get() = _binding!!
    //sharedViewModel based on current fragment
    private val sharedviewModel: ViewModelDict by activityViewModels()


    private val wordViewModel: ViewModelDictionary by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //inflate WordAddBinding
        _binding = FragmentWordAddBinding.inflate(inflater, container, false)
        //set the lifecycle and add listener button
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedviewModel
            addWordFragment = this@WordAddFragment
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observe changes in the sharedViewModel
        sharedviewModel.word.observe(viewLifecycleOwner) { word ->
            //observer for word exact match
            binding.foundWordTextView.text = word.id
            binding.wordDefinitionAdd.text = word.shortdefs
            binding.imageAdd.load("${sharedviewModel.getURL()}${word.imageName}.gif")
        }

        binding.addButton.setOnClickListener{
            addWordToDatabase()
        }
    }

    fun addWordToDatabase() {
        wordViewModel.insert(sharedviewModel.getWord())
        // Navigate back to the [StartFragment] to start over
        findNavController().navigate(R.id.action_wordAddFragment_to_wordDictionaryHome)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}