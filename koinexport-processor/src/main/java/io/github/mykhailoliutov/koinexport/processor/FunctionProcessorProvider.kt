package io.github.mykhailoliutov.koinexport.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class FunctionProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val optionsParser = ExportsOptionsParser(environment.logger)
        val options = optionsParser.parse(environment.options)

        return FunctionProcessor(
            codeGenerator = environment.codeGenerator,
            options = options
        )
    }
}