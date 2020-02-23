package com.nullexcom.mvisamplekotlin.ui

class HomeViewIntent() {
    companion object {
        fun bind(homeFragment: HomeFragment, viewModel: HomeViewModel) {
            homeFragment.doOnStart().doOnNext { viewModel.loadPage() }.subscribe()
            homeFragment.doOnTextChanged().doOnNext { viewModel.onTextChanged(it) }.subscribe()
            homeFragment.doOnClickSearch().doOnNext { viewModel.search() }.subscribe()
        }
    }
}