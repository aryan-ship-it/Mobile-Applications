package com.bignerdranch.android.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
private const val TAG = "MainActivity"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState is set.")
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_KEY, 0)
        }
         //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        trueButton = findViewById(R.id.true_button)
//        falseButton = findViewById(R.id.false_button)

        //display the initial question
        binding.questionText.setText(questionBank[currentIndex].testResId)

        //write the next button so that it advances to the next question
        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            binding.questionText.setText(questionBank[currentIndex].testResId)
        }

        // Lambda function SAM
//        trueButton.setOnClickListener { view: View ->
//            Toast.makeText(
//                this,
//                R.string.correct_toast,
//                Toast.LENGTH_SHORT)
//                .show()
//        }

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.cheatButton.setOnClickListener {
            // Start the cheat activity
            val intent = Intent(this, CheatActivity::class.java)
            val answer = questionBank[currentIndex].answer
            intent.putExtra(EXTRA_ANSWER_KEY, answer)
            startActivity(intent)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        var resId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(CURRENT_INDEX_KEY, this.currentIndex)
    }
}
