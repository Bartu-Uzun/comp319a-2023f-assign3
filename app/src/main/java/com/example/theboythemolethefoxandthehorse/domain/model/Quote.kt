package com.example.theboythemolethefoxandthehorse.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Quote(
    @PrimaryKey
    val dayOfQuote: Int, // (e.g. if dayOfQuote == 1, first day of this year, this month)
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val comment: String = ""
)
