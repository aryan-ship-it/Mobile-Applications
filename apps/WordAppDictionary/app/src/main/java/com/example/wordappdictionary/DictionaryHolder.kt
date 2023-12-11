package com.example.wordappdictionary

import androidx.annotation.WorkerThread
import com.example.wordappdictionary.database.Word
import com.example.wordappdictionary.database.WordDao
import kotlinx.coroutines.flow.Flow

class DictionaryHolder (private val wordDao: WordDao){
    val allWords: Flow<List<Word>> = wordDao.getAllWords()

    val activeWords: Flow<List<Word>> = wordDao.getActiveWords()

    val inactiveWords: Flow<List<Word>> = wordDao.getInactiveWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insertWord(word)
    }



    fun activate(word: String){
        wordDao.setWordActive(word)
    }

    fun deactivate(word: String){
        wordDao.setWordInactive(word)
    }

    fun checkWordCount(word: String): Flow<Int> {
        return wordDao.checkWord(word)
    }


}