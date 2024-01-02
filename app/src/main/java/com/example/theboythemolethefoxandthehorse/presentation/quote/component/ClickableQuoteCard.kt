package com.example.theboythemolethefoxandthehorse.presentation.quote.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.theboythemolethefoxandthehorse.domain.model.Quote

@Composable
fun ClickableQuoteCard(
    onClick: (Quote) -> Unit,
    quote: Quote,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable(
                onClick = { onClick(quote) }
            )
    ) {
        Column {
            Image(
                painter = painterResource(quote.imageResourceId),
                contentDescription = stringResource(quote.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
