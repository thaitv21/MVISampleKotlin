package com.nullexcom.mvisamplekotlin.di

import com.nullexcom.mvisamplekotlin.ui.HomeViewModel
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface UseCaseComponent {
    fun inject(viewModel: HomeViewModel)
}