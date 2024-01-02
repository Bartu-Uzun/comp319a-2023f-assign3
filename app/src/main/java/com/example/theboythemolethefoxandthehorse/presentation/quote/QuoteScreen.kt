package com.example.theboythemolethefoxandthehorse.presentation.quote

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.presentation.quote.component.QuoteDialog
import com.example.theboythemolethefoxandthehorse.presentation.quote.component.QuoteList
import kotlinx.coroutines.flow.Flow


@Composable
fun QuoteScreen(
    responseEvents: Flow<QuoteViewModel.ResponseEvent>,
    modifier: Modifier = Modifier,
    isDialogVisible: Boolean,
    quoteList: List<Quote>,
    selectedQuote: Quote?,
    onQuoteClick: (Quote) -> Unit,
    onDismissDialog: () -> Unit,
    onDetailClick: (Quote) -> Unit,
    onNavigateToDetailScreen: (Int) -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        responseEvents.collect {event ->

            when(event) {
                is QuoteViewModel.ResponseEvent.GoToDetailScreen -> {
                    onNavigateToDetailScreen(event.dayOfQuote)
                }
            }
        }

    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        QuoteList(
            modifier = Modifier
                .alpha(
                    if (isDialogVisible) {
                        0.5f
                    } else {
                        1f
                    }
                ),
            quoteList = quoteList,
            onClick = onQuoteClick
        )

        if (isDialogVisible) {
            QuoteDialog(
                quote = selectedQuote!!,
                onDismiss = onDismissDialog,
                onDetailClick = onDetailClick
            )
        }

    }


}
