package com.nullexcom.mvisamplekotlin.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nullexcom.mvisamplekotlin.di.DaggerUseCaseComponent
import com.nullexcom.mvisamplekotlin.usecase.RecommendUseCase
import com.nullexcom.mvisamplekotlin.usecase.SearchUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    val state = MutableLiveData<HomeViewState>()

    @Inject
    lateinit var searchUseCase: SearchUseCase

    @Inject
    lateinit var recommendUseCase: RecommendUseCase

    init {
        DaggerUseCaseComponent.create().inject(this);
    }

    fun loadPage() {
        recommendUseCase.enqueue()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = HomeViewState.LoadingState }
            .doOnNext { state.value = HomeViewState.DataState.apply { movies = it } }
            .subscribe()
    }

    fun onTextChanged(text: String) {
        state.value?.keyword = text
    }

    fun search() {
        state.value?.let {
            searchUseCase.enqueue(it.keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { state.value = HomeViewState.LoadingState }
                .doOnNext { state.value = HomeViewState.DataState.apply { movies = it } }
                .subscribe()
        }
    }
}