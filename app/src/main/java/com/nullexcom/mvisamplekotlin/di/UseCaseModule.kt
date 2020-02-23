package com.nullexcom.mvisamplekotlin.di

import com.nullexcom.mvisamplekotlin.usecase.RecommendUseCase
import com.nullexcom.mvisamplekotlin.usecase.SearchUseCase
import dagger.Module
import dagger.Provides

@Module
internal class UseCaseModule {
    @Provides
    fun searchUseCase(): SearchUseCase {
        return SearchUseCase()
    }

    @Provides
    fun recommendUseCase(): RecommendUseCase {
        return RecommendUseCase()
    }
}