package com.ovidiucristurean.kmpsportcomposables.di

import com.ovidiucristurean.kmpsportcomposables.FootballFieldScreenModel
import org.koin.dsl.module

val appModule = module {
    factory { parameters ->
        FootballFieldScreenModel(parameters.get())
    }
}
