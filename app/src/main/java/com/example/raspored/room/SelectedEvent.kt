package com.example.raspored.room

sealed interface SelectedEvent {
    object SaveSelected: SelectedEvent
}