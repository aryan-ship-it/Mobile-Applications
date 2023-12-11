package com.example.wordappdictionary.network

import com.example.wordappdictionary.database.Word
import org.json.JSONArray


//convert the string value to a list value using loop
fun parseJsonStringToListOfWords(jsonStr: String): List<String> {
    val jsonArrayObj = JSONArray(jsonStr)
    val wordList = mutableListOf<String>()
    for(i in 0 until jsonArrayObj.length()) {
        wordList.add(i, jsonArrayObj.getString(i))
    }
    return wordList
}

//getWord from the jsonResponse
fun parseJsonToWord(wordId: String, jsonStr: String) : Word {
    val json = JSONArray(jsonStr)
    val entry = json.getJSONObject(0)
    val shortDefArr = entry.getJSONArray("shortdef")
    var image: String? = null
    var shortdefs = mutableListOf<String>()

    if (entry.has("art")) {
        image = entry.getJSONObject("art").getString("artid")
    }

    for (j in 0 until shortDefArr.length()){
        shortdefs.add(j,shortDefArr.getString(j))
    }

    val word = when(shortDefArr.length()){
        0 -> Word(wordId, "No definition available", 0, image)
        else -> Word(wordId, shortdefs.joinToString(separator = ";"), shortDefArr.length(), image)
    }

    return word
}

