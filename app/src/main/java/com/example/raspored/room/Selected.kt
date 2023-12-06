package com.example.raspored.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Selected(
    var selected: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)
