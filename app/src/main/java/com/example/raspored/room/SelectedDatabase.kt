package com.example.raspored.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Selected::class],
    version = 1
)
abstract class SelectedDatabase: RoomDatabase() {
    abstract val dao: SelectedDao
}
