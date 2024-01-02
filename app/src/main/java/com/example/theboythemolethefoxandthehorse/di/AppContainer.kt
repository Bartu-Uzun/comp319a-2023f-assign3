package com.example.theboythemolethefoxandthehorse.di

import android.content.Context
import com.example.theboythemolethefoxandthehorse.data.data_source.QuoteDatabase
import com.example.theboythemolethefoxandthehorse.data.repository_implementation.QuoteRepositoryImpl
import com.example.theboythemolethefoxandthehorse.domain.repository.QuoteRepository

interface AppContainer {
    val quoteRepository: QuoteRepository
}

// provides instance of QuoteRepository
class AppDataContainer(private val context: Context): AppContainer {

    // implementation for quoteRepository
    override val quoteRepository: QuoteRepository by lazy {
        QuoteRepositoryImpl(QuoteDatabase.getDatabase(context).quoteDao())
    }
}