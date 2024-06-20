package io.github.mykhailoliutov.koinexport.processor

internal data class ExportsOptions(
    val packageName: String,
    val exportFileName: String,
    val mode: GenerationMode
)

internal enum class GenerationMode {
    Properties, Extensions
}