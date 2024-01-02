package com.example.theboythemolethefoxandthehorse.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theboythemolethefoxandthehorse.R
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.ui.theme.TheBoyTheMoleTheFoxAndTheHorseTheme
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DetailScreen(
    screenState: MutableStateFlow<DetailScreenState>,
    comment: String,
    quote: Quote?,
    isEdit: Boolean,
    onClickEditButton: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickSubmitCommentButton: () -> Unit,
    onCommentChange: (String) -> Unit
) {

    when (screenState.collectAsState().value) {
        is DetailScreenState.Loading -> {
            LoadingScreen()
        }
        is DetailScreenState.Success -> {
            SuccessScreen(
                quote = quote,
                onClickBackButton = onClickBackButton,
                onClickSubmitCommentButton = onClickSubmitCommentButton,
                onCommentChange = onCommentChange,
                comment = comment,
                isEdit = isEdit,
                onClickEditButton = onClickEditButton
            )
        }
    }

}

@Composable
fun LoadingScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(150.dp),
            )
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    comment: String,
    quote: Quote?,
    isEdit: Boolean,
    onClickEditButton: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickSubmitCommentButton: () -> Unit,
    onCommentChange: (String) -> Unit
) {

    val today: LocalDate = LocalDate.now()
    val quoteDay: LocalDate = LocalDate.of(today.year, today.month, quote!!.dayOfQuote)

    Scaffold (
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onClickBackButton
                    )  {
                        Icon(
                            modifier = Modifier
                                .size(64.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back"
                        )

                    }
                                 },
                title = {
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Quote of ${
                                quoteDay.format(
                                    DateTimeFormatter.ofPattern(
                                        " MMM d, yyyy",
                                        Locale.ENGLISH
                                    )
                                )
                            }",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            )
        }
    ){

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {

            Column (
                modifier = Modifier
                    .padding(12.dp)
            ){

                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ){

                    Image(
                        painter = painterResource(quote.imageResourceId),
                        contentDescription = stringResource(quote.stringResourceId),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(32.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = LocalContext.current.getString(quote.stringResourceId),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(8.dp))



                    if (quote.comment.isEmpty()) {

                        Text(
                            text = "Add your comment here",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    else {

                        Text(
                            text = "Your Comment",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    Row (
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (comment.isEmpty() || isEdit){
                            OutlinedTextField(
                                modifier = Modifier.width(300.dp),

                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                                    cursorColor = MaterialTheme.colorScheme.primary,
                                ),
                                value = comment,
                                onValueChange = { comment: String ->
                                    onCommentChange(comment)
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            )

                            IconButton(
                                onClick = onClickSubmitCommentButton
                            ) {

                                Icon(
                                    modifier = Modifier
                                        .size(32.dp),
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Submit comment"
                                )

                            }
                        }
                        else {

                            Column (
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.secondaryContainer,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clip(RoundedCornerShape(8.dp))
                                    .width(300.dp)
                            ) {

                                Text(
                                    text = comment,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontStyle = FontStyle.Italic
                                )

                            }


                            IconButton(
                                onClick = onClickEditButton
                            ) {

                                Icon(
                                    modifier = Modifier
                                        .size(32.dp),
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit comment"
                                )

                            }
                        }
                    }




                    /*
                    OutlinedTextField(
                        modifier = Modifier
                            .onKeyEvent { event: KeyEvent ->
                                if (event.key == Key.Enter) {
                                    /* do nothing */
                                    true
                                }
                                false
                            },
                        value = usageDescription,
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            cursorColor = MaterialTheme.colorScheme.primary,
                        ),
                        onValueChange = { usageDescription: String ->
                            onUsageDescriptionChange(usageDescription)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )
                    */



                }
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun SuccessPreview() {
    TheBoyTheMoleTheFoxAndTheHorseTheme(dynamicColor = false) {


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            DetailScreen(
                quote = Quote(
                    stringResourceId = R.string.quote1,
                    imageResourceId = R.drawable.image1,
                    dayOfQuote = 1,
                    comment = "Henloooo"
                ),
                onClickBackButton = {},
                onCommentChange = {str ->},
                onClickSubmitCommentButton = {},
                screenState = MutableStateFlow(DetailScreenState.Success),
                comment = "Henloooo",
                isEdit = false,
                onClickEditButton = {}
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    TheBoyTheMoleTheFoxAndTheHorseTheme(dynamicColor = false) {


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            DetailScreen(
                quote = Quote(
                    stringResourceId = R.string.quote1,
                    imageResourceId = R.drawable.image1,
                    dayOfQuote = 1,
                    comment = "Henloooo"
                ),
                onClickBackButton = {},
                onCommentChange = {str ->},
                onClickSubmitCommentButton = {},
                screenState = MutableStateFlow(DetailScreenState.Loading),
                comment = "Henloooo",
                isEdit = false,
                onClickEditButton = {}
            )

        }
    }
}

