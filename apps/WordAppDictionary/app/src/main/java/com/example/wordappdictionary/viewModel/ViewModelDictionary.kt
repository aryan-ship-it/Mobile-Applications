package com.example.wordappdictionary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.wordappdictionary.DictionaryHolder
import com.example.wordappdictionary.database.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelDictionary (private val holder: DictionaryHolder) : ViewModel() {

    val getAllWords: LiveData<List<Word>> = holder.allWords.asLiveData()

    val getWordsActive: LiveData<List<Word>> = holder.activeWords.asLiveData()

    val getWordsinactive: LiveData<List<Word>> = holder.inactiveWords.asLiveData()


    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        holder.insert(word)
    }

    fun activateWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        holder.activate(word)
    }

    fun deactivateWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        holder.deactivate(word)
    }

    fun checkWordExist(word: String) : LiveData<Int> {
        Log.d("check exist","${holder.checkWordCount(word).asLiveData().value}")
        return holder.checkWordCount(word).asLiveData()
    }



}

class DictionaryViewModelFactory(private val holder: DictionaryHolder) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDictionary::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelDictionary(holder) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}