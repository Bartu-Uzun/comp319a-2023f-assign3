package com.example.theboythemolethefoxandthehorse.presentation.quote

import com.example.theboythemolethefoxandthehorse.domain.model.Quote

sealed class QuoteUIEvent {

    object DismissDialog: QuoteUIEvent()
    data class ShowDialog(val selectedQuote: Quote): QuoteUIEvent()
    data class GoToQuoteDetailScreen(val quote: Quote): QuoteUIEvent()
}
