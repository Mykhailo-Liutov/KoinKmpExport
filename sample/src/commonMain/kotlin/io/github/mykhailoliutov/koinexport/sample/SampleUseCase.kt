package io.github.mykhailoliutov.koinexport.sample

import io.github.mykhailoliutov.koinexport.core.KoinKmmExport
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("NewNameUseCase")
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