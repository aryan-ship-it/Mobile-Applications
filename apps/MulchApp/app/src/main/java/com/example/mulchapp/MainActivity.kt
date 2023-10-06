package com.example.mulchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.app.NotificationCompatExtras
import com.example.mulchapp.databinding.ActivityMainBinding
const val EXTRA_TYPE_KEY = "com.example.mulchapp.MainActivity.typekey"

class MainActivity : AppCompatActivity() {


    private lateinit var mulchType: String
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener{group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            mulchType = selectedRadioButton.text.toString()
            Log.d("radioGroup", "Checked radioGroup ${mulchType}")


        }

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, OrderMulchActivity::class.java)
            val checkedID = binding.radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedID)
            val selectedOptionText = selectedRadioButton.text.toString()


            intent.putExtra(EXTRA_TYPE_KEY, selectedOptionText)

            startActivity(intent)
        }










    }


}