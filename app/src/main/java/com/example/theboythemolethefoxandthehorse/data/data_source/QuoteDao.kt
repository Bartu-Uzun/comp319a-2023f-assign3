package com.example.theboythemolethefoxandthehorse.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Upsert
    suspend fun upsertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)

    // : is used to reference arguments of the function
    @Query("SELECT * FROM quote WHERE dayOfQuote = :dayOfQuote")
    fun getQuote(dayOfQuote: Int): Quote?

    @Query("SELECT * FROM quote")
    fun getAllQuotes(): List<Quote>
}