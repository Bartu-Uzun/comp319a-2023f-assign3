package com.example.theboythemolethefoxandthehorse.presentation.detail

import androidx.compose.runtime.mutableStateOf
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

data class DetailUIState(
    val selectedQuote: Quote? = null,
    val comment: String = "",
    val isEdit: Boolean = false,
    val screenState: MutableStateFlow<DetailScreenState> = MutableStateFlow(DetailScreenState.Loading)
)


sealed class DetailScreenState{

    object Loading: DetailScreenState()
    object Success: DetailScreenState()

}
