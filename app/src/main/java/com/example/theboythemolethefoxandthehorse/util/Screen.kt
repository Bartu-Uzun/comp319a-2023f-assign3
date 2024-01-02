package com.example.theboythemolethefoxandthehorse.util

sealed class Screen(val route: String) {

    object QuoteScreen : Screen(route = "quote_screen")
    object DetailScreen : Screen(route = "detail_screen")
}