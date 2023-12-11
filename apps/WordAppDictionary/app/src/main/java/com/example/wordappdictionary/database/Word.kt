package com.example.wordappdictionary.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_table")
data class Word(
    @PrimaryKey @ColumnInfo(name = "word") val id: String,
    @NonNull @ColumnInfo(name = "shortdefs") val shortdefs: String = "",
    @NonNull @ColumnInfo(name = "shortdef_count") val shortDefCount: Int = 0,
    @ColumnInfo(name = "image_file_name") val imageName: String? = "",
    @ColumnInfo(name = "status") @NonNull val active: Boolean = true
)