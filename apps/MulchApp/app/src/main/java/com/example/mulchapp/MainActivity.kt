package com.example.mulchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val listDictionaries = mutableListOf<Map<String, Int>>()


    private lateinit var binding: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMulch()
        Log.d("dictionary","Dictionary is here ${listDictionaries}")

    }
    fun setMulch(){
        val dictionary1 = mapOf(
            "Premium Bark Mulch" to 56,
            "Special Blend" to 35,
            "Triple Ground" to 40,
            "Chocolate Dyed" to 38,
            "Red Dyed" to 38,
            "Black Dyed" to 38,
            "Play Mat" to 38,
            "Cedar" to 38
        )
        listDictionaries.add(dictionary1)
    }

}