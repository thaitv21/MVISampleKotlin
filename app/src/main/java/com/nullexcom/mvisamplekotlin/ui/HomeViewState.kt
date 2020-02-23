package com.nullexcom.mvisamplekotlin.ui

import com.nullexcom.mvisamplekotlin.models.Movie

sealed class HomeViewState {
    var keyword = ""
    object LoadingState : HomeViewState()

    object ErrorState : HomeViewState()


    object DataState : HomeViewState() {
        lateinit var movies: List<Movie>
    }
}