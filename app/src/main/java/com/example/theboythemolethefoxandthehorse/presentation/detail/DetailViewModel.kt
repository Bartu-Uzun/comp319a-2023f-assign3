package com.example.theboythemolethefoxandthehorse.presentation.detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theboythemolethefoxandthehorse.data.data_source.QuoteDao
import com.example.theboythemolethefoxandthehorse.data.repository_implementation.QuoteRepositoryImpl
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel (
    private val savedStateHandle: SavedStateHandle,
    private val quoteRepository: QuoteRepository
): ViewModel() {

    val TAG = "DetailViewModel"

    private val _state = mutableStateOf(DetailUIState())
    val state: State<DetailUIState> = _state

    private val _responseEventChannel = Channel<ResponseEvent>() // channel with only one observer
    val responseEvents = _responseEventChannel.receiveAsFlow() // receiveAsFlow returns an immutable flow, will be observed by the ui

    init {
        val dayOfQuote = savedStateHandle.get<Int>("dayOfQuote")!!

        Log.d(TAG, "day of quote: $dayOfQuote")


        getQuoteOfDay(dayOfQuote)
    }

    private fun getQuoteOfDay(dayOfQuote: Int) {

        var selectedQuote: Quote? = null
        viewModelScope.launch(Dispatchers.IO){
            selectedQuote = quoteRepository.getQuote(dayOfQuote)

            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(
                    selectedQuote = selectedQuote,
                    screenState = MutableStateFlow(DetailScreenState.Success),
                    comment = selectedQuote?.comment ?: ""
                )
            }


        }


    }

    fun onEvent(event: DetailUIEvent) {

        when (event) {
            is DetailUIEvent.SetComment -> {

                _state.value = state.value.copy(
                    comment = event.comment,
                    isEdit = true
                )
            }

            DetailUIEvent.SubmitComment -> {

                val selectedQuote: Quote = state.value.selectedQuote!!

                viewModelScope.launch {
                    quoteRepository.upsertQuote(
                        quote = Quote(
                            dayOfQuote = selectedQuote.dayOfQuote,
                            imageResourceId = selectedQuote.imageResourceId,
                            stringResourceId = selectedQuote.stringResourceId,
                            comment = state.value.comment
                        )
                    )
                }

                _state.value = state.value.copy(
                    isEdit = false
                )

            }

            DetailUIEvent.EditComment -> {
                _state.value = state.value.copy(
                    isEdit = true
                )
            }
        }
    }

    sealed class ResponseEvent {

    }
}