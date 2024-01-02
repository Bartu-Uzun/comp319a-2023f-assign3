package com.example.theboythemolethefoxandthehorse.data.repository_implementation

import android.util.Log
import com.example.theboythemolethefoxandthehorse.data.data_source.QuoteDao
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class QuoteRepositoryImpl(
    private val dao: QuoteDao
): QuoteRepository {
    val TAG = "QuoteRepositoryImpl"
    override suspend fun upsertQuote(quote: Quote) {
        dao.upsertQuote(quote = quote)
    }

    override suspend fun deleteQuote(quote: Quote) {
        dao.deleteQuote(quote = quote)
    }

    override fun getQuote(dayOfQuote: Int): Quote? {
        return dao.getQuote(dayOfQuote = dayOfQuote)
    }

    override fun getAllQuotes(): List<Quote> {
        val quoteFlow = dao.getAllQuotes()
        Log.d(TAG, "get all quotes called")
        Log.d(TAG, "result is:$quoteFlow")
        return quoteFlow
    }


}