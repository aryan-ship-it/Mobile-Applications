package com.example.wordappdictionary

import android.app.Application
import com.example.wordappdictionary.database.DictionaryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DictionaryApplication : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { DictionaryDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DictionaryHolder(database.wordDao()) }
}