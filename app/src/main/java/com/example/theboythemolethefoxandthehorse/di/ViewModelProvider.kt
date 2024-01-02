package com.example.theboythemolethefoxandthehorse.di


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.theboythemolethefoxandthehorse.presentation.detail.DetailViewModel
import com.example.theboythemolethefoxandthehorse.presentation.quote.QuoteViewModel

object ViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            QuoteViewModel(
                quoteApplication().container.quoteRepository
            )
        }

        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                quoteApplication().container.quoteRepository

            )
        }


    }
}
/**
 * Extension function to queries for Application object and returns an instance of
 * [QuoteApplication].
 */
fun CreationExtras.quoteApplication(): QuoteApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuoteApplication)
