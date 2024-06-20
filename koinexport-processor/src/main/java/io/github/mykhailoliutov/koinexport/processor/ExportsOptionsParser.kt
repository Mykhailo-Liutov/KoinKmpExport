package io.github.mykhailoliutov.koinexport.processor

import com.google.devtools.ksp.processing.KSPLogger

internal class ExportsOptionsParser(private val logger: KSPLogger) {

    fun parse(options: Map<String, String>): ExportsOptions {
        return ExportsOptions(
            packageName = options[KEY_PACKAGE_NAME] ?: errorOnMissingOption(KEY_PACKAGE_NAME),
            exportFileName = options[KEY_EXPORTS_FILE] ?: errorOnMissingOption(KEY_EXPORTS_FILE),
            mode = parseMode(options[KEY_MODE])
        )
    }

    private fun errorOnMissingOption(option: String): Nothing {
        logger.error("Missing option $option for generating Koin KMM exports.")
        error("Required option $option is missing")
    }

    private fun parseMode(mode: String?): GenerationMode = when (mode) {
        "properties" -> GenerationMode.Properties
        "extensions" -> GenerationMode.Extensions
        else -> {
            logger.error("Unknown generation mode $mode for generating Koin KMM exports.")
            error("Unknown generation mode $mode")
        }
    }

    companion object {
        private const val KEY_PACKAGE_NAME = "packageName"
        private const val KEY_EXPORTS_FILE = "exportsFileName"
        private const val KEY_MODE = "mode"

    }
}