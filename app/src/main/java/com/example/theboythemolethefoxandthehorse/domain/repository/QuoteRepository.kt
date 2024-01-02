package com.example.theboythemolethefoxandthehorse.domain.repository


import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun upsertQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)

    fun getQuote(dayOfQuote: Int): Quote?

    fun getAllQuotes(): List<Quote>
}