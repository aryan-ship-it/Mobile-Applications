package com.example.wordappdictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import com.example.wordappdictionary.DictionaryApplication
import com.example.wordappdictionary.databinding.FragmentWordDefinitionBinding
import com.example.wordappdictionary.viewmodel.DictionaryViewModelFactory
import com.example.wordappdictionary.viewmodel.ViewModelDict
import com.example.wordappdictionary.viewmodel.ViewModelDictionary



class WordDefinitionFragment : Fragment() {

    private var _binding: FragmentWordDefinitionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelDict by activityViewModels()

    private val wordViewModel: ViewModelDictionary by viewModels {
        DictionaryViewModelFactory((requireActivity().application as DictionaryApplication).repository)
    }


    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: ViewModelDict by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWordDefinitionBinding.inflate(inflater)
        //binding = fragmentBinding
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            wordDefinitionFragment = this@WordDefinitionFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.word.observe(viewLifecycleOwner) { word ->

            binding.word.text = word.id
            binding.wordDefinition.text = word.shortdefs
            binding.imageDefinition.load("${sharedViewModel.getURL()}${word.imageName}.gif")
        }

        binding.activationSwitch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                wordViewModel.activateWord(sharedViewModel.getWord().id)
            } else {
                wordViewModel.deactivateWord(sharedViewModel.getWord().id)

            }

        }

    }

    override fun onResume(){
        super.onResume()
        // Update the activation switch status when the fragment resumes
        sharedViewModel.word.observe(viewLifecycleOwner) {word ->
            if(word.active){
                binding.activationSwitch.text = "Active"
                binding.activationSwitch.isChecked = true
            }
            else{
                binding.activationSwitch.text = "Inactive"
                binding.activationSwitch.isChecked = false
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}