package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_KEY = "com.bignerdranch.android.geoquiz.answer_key"
class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private var answer = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answer = this.intent.getBooleanExtra(EXTRA_ANSWER_KEY,false)
        binding.showAnswerButton.setOnClickListener{
            val textVal = when {
                answer -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(textVal)
        }
    }
}