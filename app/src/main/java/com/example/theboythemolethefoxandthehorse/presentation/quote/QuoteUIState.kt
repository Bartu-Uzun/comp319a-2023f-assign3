package com.example.theboythemolethefoxandthehorse.presentation.quote

import com.example.theboythemolethefoxandthehorse.domain.model.Quote

data class QuoteUIState(
    val quoteList: List<Quote> = listOf(),
    val isDialogVisible: Boolean = false,
    val selectedQuote: Quote? = null
)