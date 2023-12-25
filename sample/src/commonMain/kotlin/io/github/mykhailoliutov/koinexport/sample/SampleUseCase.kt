package io.github.mykhailoliutov.koinexport.sample

import io.github.mykhailoliutov.koinexport.core.KoinKmmExport

@KoinKmmExport
class SampleUseCase {

    operator fun invoke(): String = "Hello!"

}