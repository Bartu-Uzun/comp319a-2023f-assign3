package com.example.theboythemolethefoxandthehorse.presentation.detail

sealed class DetailUIEvent {

    data class SetComment(val comment: String): DetailUIEvent()


    object SubmitComment: DetailUIEvent()
    object EditComment: DetailUIEvent()

}
