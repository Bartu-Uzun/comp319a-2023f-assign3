package com.example.theboythemolethefoxandthehorse.presentation.quote.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theboythemolethefoxandthehorse.domain.model.Quote


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteList(
    modifier: Modifier = Modifier,
    quoteList: List<Quote>,
    onClick: (Quote) -> Unit,
) {

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "The Boy, The Mole, The Fox, and The Horse")
                }
            )
        }
    ){

        Surface(
            modifier = modifier
                .padding(it)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp)
            ) {

                items(quoteList) { quote: Quote ->

                    ClickableQuoteCard(
                        quote = quote,
                        onClick = onClick
                    )

                }

            }

        }

    }


}
