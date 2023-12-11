package com.example.wordappdictionary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordappdictionary.database.Word
import com.example.wordappdictionary.network.DictionaryApi
import com.example.wordappdictionary.network.parseJsonStringToListOfWords
import com.example.wordappdictionary.network.parseJsonToWord
import kotlinx.coroutines.launch

class ViewModelDict : ViewModel() {

    // LiveData to represent the API status
    private val _apiStatus = MutableLiveData<DictionaryApiStatus>()
    val apiStatus: LiveData<DictionaryApiStatus> = _apiStatus

    // LiveData for the list of search words
    private val _searchWords = MutableLiveData<List<String>>()
    val searchWords: LiveData<List<String>> = _searchWords

    // LiveData for the selected word
    private val _word = MutableLiveData<Word>()
    val word: LiveData<Word> = _word

    // LiveData for a status indicator
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    init {
        resetSelectedWord()
        resetWordList()
    }
    // Function to make an API call to get word information
    fun getWordResponse(searchWord: String) {

        viewModelScope.launch {
            _apiStatus.value = DictionaryApiStatus.LOADING
            try {
                val response = DictionaryApi.retrofitService.getWord(searchWord)
                val jsonString = response.body().toString()

                if (jsonString.startsWith("[{")) {

                    _word.value = parseJsonToWord(searchWord, jsonString)
                    _searchWords.value = listOf(word.value?.id.toString())


                }
                //Parse and set the list of words if the response contains multiple word
                else if (jsonString.startsWith("[")){
                    Log.d("ViewViewModel", jsonString.toString())
                    var parsedWords = parseJsonStringToListOfWords(jsonString)
                    if (parsedWords.isEmpty()) {
                        parsedWords = parsedWords.toMutableList()
                        parsedWords.add("No matches")
                    } else if (jsonString.startsWith("[]")) {

                        parsedWords = listOf("No matches!")
                    }
                    _searchWords.value = parsedWords


                }
                _apiStatus.value = DictionaryApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = DictionaryApiStatus.ERROR
                _searchWords.value = listOf()
            }

        }
    }

    fun resetWordList(){
        _searchWords.value = emptyList()
    }

    fun resetSelectedWord(){
        _word.value = Word("", "",0,"", false)
    }
    // Function to get the selected word
    fun setStatus(status: Boolean){
        _status.value = status
    }

    fun getWord() : Word {
        return word.value!!
    }
    // Function to set the selected word
    fun setWord(word: Word){
        _word.value = word
    }
    // Function to handle a word click event
    fun onWordClicked(word: String){
        getWordResponse(word)
    }
    // Function to get the base image URL
    fun getURL(): String{
        return BASE_IMAGE_URL
    }

}
enum class DictionaryApiStatus {LOADING, ERROR, DONE}
private const val BASE_IMAGE_URL = "https://www.merriam-webster.com/assets/mw/static/art/dict/"