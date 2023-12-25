package io.github.mykhailoliutov.koinexport.sample

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::SampleUseCase)
}