package com.example.wordappdictionary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 2)
abstract class DictionaryDatabase : RoomDatabase() {
    //get Data Access Object
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DictionaryDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }

        }
        private class DictionaryDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }
        //setUp initial data in the database

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAllWords()

            var word = Word("brain",
                "brain short definition",
                3, "", false)
            wordDao.insertWord(word)
        }
    }

}