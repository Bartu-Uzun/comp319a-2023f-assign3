package com.example.theboythemolethefoxandthehorse.presentation.quote

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theboythemolethefoxandthehorse.R
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val quoteRepository: QuoteRepository
): ViewModel() {

    val TAG = "QuoteViewModel"

    private val _state = mutableStateOf(QuoteUIState())
    val state: State<QuoteUIState> = _state

    private val _responseEventChannel = Channel<ResponseEvent>() // channel with only one observer
    val responseEvents = _responseEventChannel.receiveAsFlow() // receiveAsFlow returns an immutable flow, will be observed by the ui

    init {

        viewModelScope.launch(Dispatchers.IO){
            val quoteList = quoteRepository.getAllQuotes()

            _state.value = state.value.copy(
                quoteList = quoteList
            )
        }
    }
    fun onEvent(event: QuoteUIEvent) {

        when (event) {

            is QuoteUIEvent.GoToQuoteDetailScreen -> {

                _state.value = state.value.copy(
                    isDialogVisible = false
                )
                viewModelScope.launch{

                    _responseEventChannel.send(
                        ResponseEvent.GoToDetailScreen(
                            dayOfQuote = event.quote.dayOfQuote
                        )
                    )
                }
            }
            is QuoteUIEvent.DismissDialog -> {
                _state.value = state.value.copy(
                    isDialogVisible = false
                )
            }
            is QuoteUIEvent.ShowDialog -> {

                _state.value = state.value.copy(
                    isDialogVisible = true,
                    selectedQuote = event.selectedQuote
                )
            }
        }
    }


    sealed class ResponseEvent {

        data class GoToDetailScreen(val dayOfQuote: Int): ResponseEvent()


    }

}