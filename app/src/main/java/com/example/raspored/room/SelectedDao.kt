package com.example.raspored.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedDao {
    @Upsert
    suspend fun upsertSelected(selected: Selected)

    @Query("SELECT * FROM Selected")
    fun getSelected(): Flow<Selected>
}