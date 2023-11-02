package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import com.bignerdranch.android.geoquiz.databinding.FragmentCheatBinding
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionBinding
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val ARG_CORRECT_ANSWER = "answer"

/**
 * A simple [Fragment] subclass.
 * Use the [CheatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheatFragment : Fragment() {
    private var _binding:FragmentCheatBinding? = null
    private var answer = false
    private val binding get() = _binding!!
    // TODO: Rename and change types of parameters
    private var correctAnswer:Boolean = false

    companion object {
        val CHEAT_RESULT_KEY = "cheatResultKey"
        val IS_CHEATER = "isCheater"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            correctAnswer = it.getBoolean(ARG_CORRECT_ANSWER)
            Log.d("Cheat Fragment", "${correctAnswer}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCheatBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showAnswerButton.setOnClickListener {
            val textVal = when {
                correctAnswer -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(textVal)
            setFragmentResult(CHEAT_RESULT_KEY, bundleOf(IS_CHEATER to true))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}