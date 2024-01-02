package com.example.theboythemolethefoxandthehorse.presentation.quote.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.theboythemolethefoxandthehorse.R
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.ui.theme.TheBoyTheMoleTheFoxAndTheHorseTheme

@Composable
fun QuoteDialog(
    quote: Quote,
    onDismiss: () -> Unit,
    onDetailClick: (Quote) -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.5f)


        Card{
            Column {
                Image(
                    painter = painterResource(quote.imageResourceId),
                    contentDescription = stringResource(quote.stringResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = LocalContext.current.getString(quote.stringResourceId),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    fontStyle = FontStyle.Italic
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp)
                        .padding(end = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.End
                )
                {

                    IconButton(
                        modifier = Modifier
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(8.dp),
                        onClick = { onDetailClick(quote) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(54.dp),
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = stringResource(id = R.string.show_details)
                        )
                    }

                }
            }
        }

    }

}



@Preview(showBackground = true)
@Composable
fun GDialogPreview() {
    TheBoyTheMoleTheFoxAndTheHorseTheme(dynamicColor = false) {


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            QuoteDialog(
                quote = Quote(
                    stringResourceId = R.string.quote1,
                    imageResourceId = R.drawable.image1,
                    dayOfQuote = 1,
                    comment = "",
                ),
                onDetailClick = {q->},
                onDismiss = {}
            )

        }
    }
}

