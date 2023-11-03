package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionBinding
import java.lang.Exception
import kotlin.math.roundToInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TAG = "QuestionFragment"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"


/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private var previousIndex = 0
    private var currentIndex = 0
    private var isCheater = false
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_amazon,true),
        Question(R.string.question_greatWall,false),
        Question(R.string.question_antartica,true),
        Question(R.string.question_sahara,true),
        Question(R.string.question_mountEverest,true),
        Question(R.string.question_equator,true),
        Question(R.string.question_russia,true),
        Question(R.string.question_timezone,true),
        Question(R.string.question_pacific,true),
        Question(R.string.question_deadsea,true),
        Question(R.string.question_uk,false),
        Question(R.string.question_chile,false),
    )
    private var userScore:Int = 0



    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // a lambda function to handle the result
        if (result.resultCode == Activity.RESULT_OK) {
            isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(CheatFragment.CHEAT_RESULT_KEY){_,bundle ->
            isCheater = bundle.getBoolean(CheatFragment.IS_CHEATER)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState is set.")
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_KEY, 0)
        }


        binding.questionText.setText(questionBank[currentIndex].testResId)

        binding.questionText.setOnClickListener {
            if(!isEndOfList(currentIndex)){
                previousIndex = currentIndex
                currentIndex = (currentIndex + 1) % questionBank.size
                binding.questionText.setText(questionBank[currentIndex].testResId)
                displayAnswer(currentIndex)
            }


        }
        binding.prevButton.setOnClickListener {
            try{
                binding.questionText.setText(questionBank[previousIndex].testResId)
                currentIndex = (currentIndex-1) % questionBank.size
                previousIndex = currentIndex - 1
                displayAnswer(currentIndex)

            }catch (e:Exception){
                Log.d("Error On Previous Button", e.toString());
            }



        }

        binding.nextButton.setOnClickListener {
            if(!isEndOfList(currentIndex)){
                previousIndex = currentIndex
                currentIndex = (currentIndex + 1) % questionBank.size
                binding.questionText.setText(questionBank[currentIndex].testResId)
                displayAnswer(currentIndex)
            }

        }

        binding.trueButton.setOnClickListener {
            if(questionBank[currentIndex].answered == false){
                checkAnswer(true)
            }

        }

        binding.falseButton.setOnClickListener {
            if(questionBank[currentIndex].answered == false){
                checkAnswer(false)
            }
        }

        binding.cheatButton.setOnClickListener {
            // Start the cheat activity
            val answer:Boolean = questionBank[currentIndex].answer
            val action = QuestionFragmentDirections.actionQuestionFragmentToCheatFragment(answer)
            Log.d(TAG,"${answer}")
            findNavController().navigate(action)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(CURRENT_INDEX_KEY, this.currentIndex)
    }

    private fun isEndOfList(currentIndex: Int):Boolean{
        if(currentIndex == questionBank.size -1){
            val userMessage:String = "Your Scored:${userScore} out of ${questionBank.size} (${((userScore.toDouble()/questionBank.size.toDouble())*100).roundToInt()} %)"
            Toast.makeText(getContext(),userMessage, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
    private fun displayAnswer(currentIndex:Int){
        if(questionBank[currentIndex].answered == true){
            binding.displayAnswer.setText("Your answer was: ${questionBank[currentIndex].userAnswer}")
        }
        else{
            binding.displayAnswer.setText(" ")
        }
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        questionBank[currentIndex].answered = true
        questionBank[currentIndex].userAnswer = userAnswer.toString()
        val resId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        if(userAnswer == correctAnswer){
            userScore += 1
        }
        isCheater = false

        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }



}