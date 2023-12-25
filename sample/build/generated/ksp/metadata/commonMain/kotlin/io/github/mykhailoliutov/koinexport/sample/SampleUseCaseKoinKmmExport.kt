package io.github.mykhailoliutov.koinexport.sample

import org.koin.core.Koin

public val Koin.sampleUseCase: SampleUseCase
  get() = get()
