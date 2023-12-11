package com.example.wordappdictionary

import com.example.wordappdictionary.database.Word
import com.example.wordappdictionary.network.parseJsonStringToListOfWords
import com.example.wordappdictionary.network.parseJsonToWord
import org.junit.Assert
import org.junit.Test

class JsonParserTest {
    @Test
    fun parseJsonToArray(){
        val jsonString = javaClass.getResource("/string_array_response.json")?.readText()

        val wordList = parseJsonStringToListOfWords(jsonString!!)
        //send wordlist of size 4 and assert equals
        Assert.assertEquals(4, wordList.size)
    }


    @Test
    fun parseJsonToWord(){

        val jsonString = javaClass.getResource("/word_with_two_def_response.json")?.readText()

        val word = parseJsonToWord("bread", jsonString!!)
        //Assert
        Assert.assertEquals(
            Word("bread",
                "a usually baked and leavened food made of a mixture whose basic constituent is flour or meal;food, sustenance;livelihood",
                3,
                "", false)
            ,word)

    }

}