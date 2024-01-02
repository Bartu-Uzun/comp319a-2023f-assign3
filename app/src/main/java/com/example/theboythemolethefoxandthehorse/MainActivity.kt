package com.example.theboythemolethefoxandthehorse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.theboythemolethefoxandthehorse.data.data_source.QuoteDatabase
import com.example.theboythemolethefoxandthehorse.data.repository_implementation.QuoteRepositoryImpl
import com.example.theboythemolethefoxandthehorse.di.ViewModelProvider
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import com.example.theboythemolethefoxandthehorse.domain.repository.QuoteRepository
import com.example.theboythemolethefoxandthehorse.presentation.detail.DetailScreen
import com.example.theboythemolethefoxandthehorse.presentation.detail.DetailUIEvent
import com.example.theboythemolethefoxandthehorse.presentation.detail.DetailViewModel
import com.example.theboythemolethefoxandthehorse.presentation.quote.QuoteScreen
import com.example.theboythemolethefoxandthehorse.presentation.quote.QuoteUIEvent
import com.example.theboythemolethefoxandthehorse.presentation.quote.QuoteViewModel
import com.example.theboythemolethefoxandthehorse.ui.theme.TheBoyTheMoleTheFoxAndTheHorseTheme
import com.example.theboythemolethefoxandthehorse.util.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheBoyTheMoleTheFoxAndTheHorseTheme(dynamicColor = false) {

                val navController = rememberNavController()




                NavHost(
                    navController = navController,
                    startDestination = Screen.QuoteScreen.route,
                    ) {

                    composable(route = Screen.QuoteScreen.route) {

                        val TAG = "QuoteScreenNavHost"

                        val quoteViewModel: QuoteViewModel = viewModel(factory = ViewModelProvider.Factory)
                        val quoteState = quoteViewModel.state.value
                        val quoteResponseEvents = quoteViewModel.responseEvents

                        QuoteScreen(
                            responseEvents = quoteResponseEvents,
                            isDialogVisible = quoteState.isDialogVisible,
                            quoteList = quoteState.quoteList,
                            selectedQuote = quoteState.selectedQuote,
                            onQuoteClick = { quote: Quote ->

                                quoteViewModel.onEvent(
                                    QuoteUIEvent.ShowDialog(
                                        selectedQuote = quote
                                    )
                                )
                                           },
                            onDismissDialog = {

                                quoteViewModel.onEvent(QuoteUIEvent.DismissDialog)
                                              },
                            onDetailClick = { quote: Quote ->

                                quoteViewModel.onEvent(QuoteUIEvent.GoToQuoteDetailScreen(quote))
                            },
                            onNavigateToDetailScreen = {dayOfQuote: Int ->

                                Log.d(TAG, "day of quote: $dayOfQuote")
                                navController.navigate(Screen.DetailScreen.route + "/${dayOfQuote}")
                            }
                        )
                    }

                    composable(
                        route = Screen.DetailScreen.route + "/{dayOfQuote}",
                        arguments = listOf(
                            navArgument(name = "dayOfQuote") {
                                type = NavType.IntType
                                nullable = false
                            },
                        )
                        ) {


                        val detailViewModel: DetailViewModel = viewModel(factory = ViewModelProvider.Factory)
                        val detailState = detailViewModel.state.value
                        val detailResponseEvent = detailViewModel.responseEvents

                        DetailScreen(
                            screenState = detailState.screenState,
                            quote = detailState.selectedQuote,
                            comment = detailState.comment,
                            isEdit = detailState.isEdit,
                            onClickBackButton = {
                                navController.popBackStack()
                            },
                            onCommentChange = { comment: String ->

                                detailViewModel.onEvent(DetailUIEvent.SetComment(comment = comment))

                            },
                            onClickSubmitCommentButton = {
                                detailViewModel.onEvent(DetailUIEvent.SubmitComment)
                            },
                            onClickEditButton = {
                                detailViewModel.onEvent(DetailUIEvent.EditComment)
                            }
                        )



                    }

                }
            }
        }
    }
}
