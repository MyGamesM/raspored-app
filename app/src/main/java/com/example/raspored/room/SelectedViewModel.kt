package com.example.raspored.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SelectedViewModel(
    dao: SelectedDao
): ViewModel() {
    private val _selected = dao.getSelected()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Selected("0"))

    private val _state = MutableStateFlow(SelectedState())
    val state = combine(_state, _selected) { state, selected ->
        state.copy(
            selected = selected
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SelectedState())

    fun setSelectedValue(value: String) {
        val intValue = value.toIntOrNull() ?: return
        _state.update { it.copy(selected = Selected(value)) }
    }
}