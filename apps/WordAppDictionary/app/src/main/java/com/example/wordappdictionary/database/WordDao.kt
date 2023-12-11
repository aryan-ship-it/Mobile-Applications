package com.example.wordappdictionary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    //query for all words
    @Query("SELECT * FROM dictionary_table ORDER BY word")
    fun getAllWords(): Flow<List<Word>>

    //show words that are active
    @Query("SELECT * FROM dictionary_table WHERE status = 1 ORDER BY word")
    fun getActiveWords(): Flow<List<Word>>

    //show words that are inactive
    @Query("SELECT * FROM dictionary_table WHERE status = 0 ORDER BY word")
    fun getInactiveWords(): Flow<List<Word>>

    //query to insert Word
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(word: Word)

    //delete all Words
    @Query("DELETE FROM dictionary_table")
    fun deleteAllWords()

    @Query("UPDATE dictionary_table SET status = 1 WHERE word = :wordID")
    fun setWordActive(wordID: String)

    @Query("UPDATE dictionary_table SET status = 0 WHERE word = :wordID")
    fun setWordInactive(wordID: String)

    @Query("SELECT COUNT(*) FROM dictionary_table WHERE word = :wordSearched")
    fun checkWord(wordSearched: String) : Flow<Int>

}