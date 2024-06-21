package io.github.mykhailoliutov.koinexport.processor

import io.github.mykhailoliutov.koinexport.core.KoinKmmExport
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ksp.writeTo

internal class FunctionProcessor(
    private val codeGenerator: CodeGenerator,
    private val options: ExportsOptions
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation(KoinKmmExport::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()

        if (!symbols.iterator().hasNext()) return emptyList()

        val exportsBuilder = FileSpecBuilder(
            packageName = options.packageName,
            exportsFileName = options.exportFileName,
            mode = options.mode
        )

        val visitor = KoinExportVisitor(
            addExportProperty = {
                exportsBuilder.addProperty(it)
            }
        )
        symbols.forEach { it.accept(visitor, Unit) }

        exportsBuilder.build().writeTo(codeGenerator, true)
        return emptyList()
    }
}
