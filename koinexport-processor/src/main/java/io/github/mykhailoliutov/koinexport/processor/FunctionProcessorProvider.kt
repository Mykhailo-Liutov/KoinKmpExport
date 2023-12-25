package io.github.mykhailoliutov.koinexport.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class FunctionProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return FunctionProcessor(
            codeGenerator = environment.codeGenerator,
            logger = environment.logger
        )
    }
}