package io.github.mykhailoliutov.koinexport.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.ksp.writeTo
import org.koin.core.Koin

class KoinExportVisitor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val packageName = classDeclaration.packageName.asString()
        val className = classDeclaration.simpleName.asString()
        val exportName = "${className}KoinKmmExport"

        logger.warn(className)
        val fileSpec = FileSpec.builder(packageName, exportName).apply {
            val extensionPropertyName = className.replaceFirstChar { it.lowercase() }

            val getterSpec = FunSpec.getterBuilder()
                .addStatement("return get()")
                .build()

            val propertySpec = PropertySpec.builder(extensionPropertyName, ClassName(packageName, className))
                .receiver(Koin::class)
                .getter(getterSpec)
                .build()

            addProperty(propertySpec)
        }.build()
        fileSpec.writeTo(codeGenerator, true)
    }
}