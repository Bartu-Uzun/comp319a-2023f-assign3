package com.example.theboythemolethefoxandthehorse.data.repository_implementation

import android.util.Log
import com.example.theboythemolethefoxandthehorse.R
import com.example.theboythemolethefoxandthehorse.data.data_source.QuoteDao
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.domain.repository.QuoteRepository

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

    override suspend fun getAllQuotes(): List<Quote> {
        var quoteFlow = dao.getAllQuotes()
        Log.d(TAG, "get all quotes called")
        Log.d(TAG, "result is:$quoteFlow")
        if (quoteFlow.isEmpty()) {
            quoteFlow = populateDatabase()
        }
        return quoteFlow
    }

    override suspend fun populateDatabase(): List<Quote> {
        quoteList.forEach {quote: Quote ->
            dao.upsertQuote(quote)
        }

        return quoteList
    }


    companion object {
        val quoteList: List<Quote> = listOf(
            Quote(
                stringResourceId = R.string.quote1,
                imageResourceId = R.drawable.image1,
                dayOfQuote = 1,
            ),
            Quote(
                stringResourceId = R.string.quote2,
                imageResourceId = R.drawable.image2,
                dayOfQuote = 2
            ),
            Quote(
                stringResourceId = R.string.quote3,
                imageResourceId = R.drawable.image3,
                dayOfQuote = 3
            ),
            Quote(
                stringResourceId = R.string.quote4,
                imageResourceId = R.drawable.image4,
                dayOfQuote = 4
            ),
            Quote(
                stringResourceId = R.string.quote5,
                imageResourceId = R.drawable.image5,
                dayOfQuote = 5
            ),
            Quote(
                stringResourceId = R.string.quote6,
                imageResourceId = R.drawable.image6,
                dayOfQuote = 6
            ),
            Quote(
                stringResourceId = R.string.quote7,
                imageResourceId = R.drawable.image7,
                dayOfQuote = 7
            ),
            Quote(
                stringResourceId = R.string.quote8,
                imageResourceId = R.drawable.image8,
                dayOfQuote = 8
            ),
            Quote(
                stringResourceId = R.string.quote9,
                imageResourceId = R.drawable.image9,
                dayOfQuote = 9
            ),
            Quote(
                stringResourceId = R.string.quote10,
                imageResourceId = R.drawable.image10,
                dayOfQuote = 10
            ),
        )
    }
}