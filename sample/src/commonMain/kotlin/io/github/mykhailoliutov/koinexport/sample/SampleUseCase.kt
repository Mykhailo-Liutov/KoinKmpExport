package io.github.mykhailoliutov.koinexport.sample

import io.github.mykhailoliutov.koinexport.core.KoinKmmExport

@KoinKmmExport
class SampleUseCase {

    operator fun invoke(): String = "Hello!"

}

@KoinKmmExport
class SampleUseCase2 {

    operator fun invoke(): String = "Hello1!"

}

@KoinKmmExport
class SampleUseCas3 {

    operator fun invoke(): String = "Hello2!"

}